package com.platform.service.sc;


import com.platform.entity.sc.ScInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 圈子信息表Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-21 23:29:44
 */
public interface ScInfoService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    ScInfoEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<ScInfoEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param scInfo 实体
     * @return 保存条数
     */
    int save(ScInfoEntity scInfo);

    /**
     * 根据主键更新实体
     *
     * @param scInfo 实体
     * @return 更新条数
     */
    int update(ScInfoEntity scInfo);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
}
