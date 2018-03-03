package com.platform.service;

import com.platform.dto.CarpoolOrderVo;
import com.platform.dto.CarpoolUserOrderVo;
import com.platform.entity.CarpoolOrder;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.TreeMap;

/**
 * Created by zuimeideshiguang on 18/2/12.
 *
 * 拼车预约单业务接口
 */
public interface ApiCarpoolOrderService extends IBasicSetMapper<CarpoolOrder>  {

    /**
     * 预定
     * @param carpoolOrder
     */
    void order(CarpoolOrderVo carpoolOrder);

    /**
     * 确认或拒绝
     * @param carpoolOrder
     */
    void confirmOrRefuseOrder(CarpoolOrderVo carpoolOrder);

    /**
     *  取消
     * @param carpoolOrder
     */
    void  cancelOrder(CarpoolOrderVo carpoolOrder);

    /**
     * 发送模板消息
     * @param userId
     * @param tmplId
     * @param page
     * @param params
     */
    void sendTemplateMsg(Integer userId , String tmplId , String page,  TreeMap<String,TreeMap<String,String>> params);

    /**
     * 查询用户预约单
     * @param id
     * @return
     */
    CarpoolUserOrderVo queryCarpoolUserOrder(Integer id);
}
