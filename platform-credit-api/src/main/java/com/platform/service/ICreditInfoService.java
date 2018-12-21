package com.platform.service;

import com.platform.entity.CreditInfo;
import com.platform.entity.vo.CreditQueryVo;

import java.util.Map;

/**
 * 征信信息服务接口
 *
 * @author bjsonghongxu
 * @create 2018-12-21 16:39
 **/
public interface ICreditInfoService extends IBasicSetMapper<CreditInfo> {

    /**
     * 查询征信申请
     * @param vo
     * @return
     */
    Map<String, Object> query(CreditQueryVo vo);


    /**
     * 查询征信结果
     * @param vo
     * @return
     */
    Map<String, Object> info(CreditQueryVo vo);
}
