package com.platform.service.member;


import com.platform.entity.member.ImsEweiShopMemberEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-26 21:55:37
 */
public interface ImsEweiShopMemberService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    ImsEweiShopMemberEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<ImsEweiShopMemberEntity> queryList(Map<String, Object> map);

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
     * @param imsEweiShopMember 实体
     * @return 保存条数
     */
    int save(ImsEweiShopMemberEntity imsEweiShopMember);

    /**
     * 根据主键更新实体
     *
     * @param imsEweiShopMember 实体
     * @return 更新条数
     */
    int update(ImsEweiShopMemberEntity imsEweiShopMember);

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
