package com.platform.service.impl;

import com.platform.core.generator.Generator;
import com.platform.dao.SysGeneratorDao;
import com.platform.entity.ColumnEntity;
import com.platform.entity.TableEntity;
import com.platform.service.SysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service("sysGeneratorService")
public class SysGeneratorServiceImpl implements SysGeneratorService {
    @Autowired
    private SysGeneratorDao sysGeneratorDao;


    @Override
    public List<TableEntity> queryList(Map<String, Object> map) {

        return sysGeneratorDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {

        return sysGeneratorDao.queryTotal(map);
    }

    @Override
    public TableEntity queryTable(String tableName) {
        return sysGeneratorDao.queryTable(tableName);
    }

    @Override
    public List<ColumnEntity> queryColumns(String tableName) {

        return sysGeneratorDao.queryColumns(tableName);
    }

    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            TableEntity table = queryTable(tableName);
            //查询列信息
            List<ColumnEntity> columns = queryColumns(tableName);
            //生成代码
            Generator.generteCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

}
