package com.platform.core.generator;

import com.platform.core.enums.BusinessEnum;
import com.platform.core.utils.GaeaUtils;
import com.platform.core.utils.RRException;
import com.platform.entity.ColumnEntity;
import com.platform.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 生成器
 *
 * @author bjsonghongxu
 * @create 2018-08-03 11:29
 **/
public class Generator {

    private static  final Logger logger = LoggerFactory.getLogger(Generator.class);

    private static Configuration config = GaeaUtils.getConfig();


    public static void generteCode(TableEntity tableEntity, List<ColumnEntity> columns, ZipOutputStream zip){
        //转化表信息
        table2Entity(tableEntity,columns);

        ///构造模板参数
        Map<String, Object> map = buildTemplateMap(tableEntity);

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = GaeaUtils.getTemplates();//GaeaUtils.loadTemplateNameList("template/");
        if (null != templates){
            for (String template : templates) {
                //渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                tpl.merge(context, sw);

                try {
                    //添加到zip
                    zip.putNextEntry(new ZipEntry(GaeaUtils.getFileName(template, tableEntity.getClassName(), config.getString("package"),tableEntity.getTablePrefix())));
                    IOUtils.write(sw.toString(), zip, "UTF-8");

                } catch (IOException e) {
                    throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
                }finally {
                    IOUtils.closeQuietly(sw);
                    try {
                        zip.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    //转化表信息
    private static void table2Entity(TableEntity tableEntity, List<ColumnEntity> columns){

        //设置表-实体信息
       String tbName = tableEntity.getTableName(); // 表名
       String prefix = GaeaUtils.getTablePrefix(tbName); //业务前缀，可作为包的最近一层的包名
       String className = GaeaUtils.table2Java(tbName,prefix); // 类名称
       String aliasName = StringUtils.uncapitalize(className); // 别名
       tableEntity.setTablePrefix(prefix);
       tableEntity.setClassName(className);
       tableEntity.setAliasName(aliasName);

       logger.debug("tableEntity infos  : "+ tableEntity.toString());

       //设置字段-实体信息
        column2Entity(tableEntity,columns);
        tableEntity.setColumnEntityList(columns);

    }
    //转化列属性
    private static void column2Entity(TableEntity tableEntity,List<ColumnEntity> columns){
        if (null == columns){return;}
        for (ColumnEntity column : columns) {
            column.setAttrName(GaeaUtils.column2Java(column.getColumnName()));
            column.setAttrname(StringUtils.uncapitalize(column.getAttrName()));
            column.setAttrType(config.getString(column.getDataType(),"String"));
            if (BusinessEnum.DBPKEnum.MYSQLPK.getKey().equalsIgnoreCase(column.getColumnKey())
                    || BusinessEnum.DBPKEnum.ORACELPK.getKey().equalsIgnoreCase(column.getColumnKey())){
                tableEntity.setPkColumn(column);
            }
        }
    }


    ///构造模板参数
    private static Map<String, Object> buildTemplateMap(TableEntity tableEntity){
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPkColumn());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getAliasName());
        map.put("pathName", tableEntity.getAliasName().toLowerCase());
        map.put("columns", tableEntity.getColumnEntityList());
        map.put("package", config.getString("package"));
        map.put("tablePrefix",tableEntity.getTablePrefix());
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", GaeaUtils.format(new Date(), GaeaUtils.DATE_TIME_PATTERN));


        return map;
    }
}
