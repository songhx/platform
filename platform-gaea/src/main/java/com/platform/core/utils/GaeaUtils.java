package com.platform.core.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 工具类
 *
 * @author bjsonghongxu
 * @create 2018-08-02 18:04
 **/
public class GaeaUtils {

    private GaeaUtils(){}


    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取模板名称列表
     * @param path
     * @return
     */
    public static List<String> loadTemplateNameList(String path) {
        if (StringUtils.isBlank(path)){return null;}
        List<String> templates = new ArrayList<>();
        File file = new File(ClassLoader.getSystemResource("").getPath() + path);
        File[] files = file.listFiles();
        if (null != files){
            for (File f : files) {
                if (f.isFile()){
                    templates.add(f.getName());
                }
            }
        }
        return templates;
    }

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/Entity.java.vm");
        templates.add("template/Dao.java.vm");
        templates.add("template/Dao.xml.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/Controller.java.vm");
        return templates;
    }

    /**
     * 获取表头业务前缀
     * @param tableName
     * @return
     */
    public static String getTablePrefix(String tableName){
        return (StringUtils.isBlank(tableName)) ? "" :  tableName.substring(0,tableName.indexOf("_"));
    }


    /**
     * 列名转换成Java属性名
     * @param columnName 列名称
     * @return java 属性名
     */
    public static String column2Java(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     * @param tableName 表名
     * @param tablePrefix 业务前缀
     * @return java 类名
     */
    public static String table2Java(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return column2Java(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RRException("获取配置文件失败，", e);
        }
    }

    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName,String tablePrefix) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if (template.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + tablePrefix + File.separator + className + ".java";
        }

        if (template.contains("Dao.java.vm")) {
            return packagePath + "dao" + File.separator + tablePrefix + File.separator + "mapper" + File.separator + className + "Mapper.java";
        }

        if (template.contains("Dao.xml.vm")) {
            return packagePath + "dao" + File.separator + tablePrefix + File.separator + "mapping" + File.separator + className + "Mapper.xml";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + tablePrefix + File.separator + "I" + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + tablePrefix + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "web.controller" + File.separator + tablePrefix + File.separator + className + "Controller.java";
        }

        if (template.contains("list.html.vm")) {
            return "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "page"
                    + File.separator + "shop" + File.separator + className.toLowerCase() + ".html";
        }

        if (template.contains("list.js.vm")) {
            return "main" + File.separator + "webapp" + File.separator + "js" + File.separator + "shop" + File.separator + className.toLowerCase() + ".js";
        }

        if (template.contains("menu.sql.vm")) {
            return className.toLowerCase() + "_menu.sql";
        }

        return null;
    }
    public static void main(String[] args) {
        //System.out.println(GaeaUtils.table2Java("sys_user_rel_role","sys"));
        System.out.println(GaeaUtils.getTablePrefix("sys_user_rel_role"));
        //List<String> list = GaeaUtils.loadTemplateNameList("template/");

    }
}
