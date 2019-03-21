package com.platform.service;

import com.platform.dto.CarpoolPublishVo;
import com.platform.entity.CarpoolUser;

import java.util.Map;


/**
 * 拼车用户业务接口
 *
 * @author bjsonghongxu
 * @create 2018-02-12 17:59
 **/

public interface ApiCarpoolUserService extends IBasicSetMapper<CarpoolUser> {

    /**
     * 统计拼车次数
     * @param carpoolPublish
     * @return
     */
    Map<String,Object> statCarpoolTimes(CarpoolPublishVo carpoolPublish);
}
