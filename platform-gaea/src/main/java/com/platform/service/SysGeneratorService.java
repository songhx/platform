package com.platform.service;

import com.platform.entity.ColumnEntity;
import com.platform.entity.TableEntity;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年12月19日 下午3:33:38
 */
public interface SysGeneratorService {

    List<TableEntity>queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    TableEntity queryTable(String tableName);

    List<ColumnEntity> queryColumns(String tableName);

    /**
     * 生成代码
     */
    byte[] generatorCode(String[] tableNames);
}
