package com.platform.service.distributor;


import com.platform.entity.distributor.DistributorRateEntity;

import java.util.List;
import java.util.Map;

/**
 * 分销商分配率配置表Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-08-09 22:37:03
 */
public interface DistributorRateService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    DistributorRateEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<DistributorRateEntity> queryList(Map<String, Object> map);

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
     * @param distributorRate 实体
     * @return 保存条数
     */
    int save(DistributorRateEntity distributorRate);

    /**
     * 根据主键更新实体
     *
     * @param distributorRate 实体
     * @return 更新条数
     */
    int update(DistributorRateEntity distributorRate);

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
