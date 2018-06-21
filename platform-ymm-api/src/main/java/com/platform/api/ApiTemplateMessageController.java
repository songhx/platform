package com.platform.api;

import com.platform.entity.UserFormid;
import com.platform.service.IUserFormidService;
import com.platform.util.ApiBaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 模板消息处理类
 *
 * @author bjsonghongxu
 * @create 2018-02-28 11:20
 **/
@RestController
@RequestMapping("/api/templateMessage")
public class ApiTemplateMessageController extends ApiBaseAction {


    @Autowired
    private IUserFormidService iUserFormidService;

    /**
     * 收集form_id
     * @param carpoolUserFormid
     * @return
     */
    @RequestMapping("collect")
    public Object collectCarpoolFormId(@RequestBody UserFormid carpoolUserFormid) {

        if (null == carpoolUserFormid.getUserId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        carpoolUserFormid.setCreateTime(new Date());
        iUserFormidService.insertSelective(carpoolUserFormid);
        return toResponsSuccess("保存成功");
    }


    @RequestMapping("send")
    public Object send() {


        return toResponsSuccess("保存成功");
    }



}
