package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.CarpoolUser;
import com.platform.entity.UserVo;
import com.platform.service.ApiCarpoolUserService;
import com.platform.util.ApiBaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 拼车用户控制器
 *
 * @author bjsonghongxu
 * @create 2018-02-12 18:02
 **/
@RestController
@RequestMapping("/api/car/user")
public class ApiCarpoolUserController  extends ApiBaseAction {

    @Autowired
    private ApiCarpoolUserService apiCarpoolUserService;

    @IgnoreAuth
    @RequestMapping("queryUser")
    public Object queryUser(CarpoolUser user ) {
        user.setId(1);
        CarpoolUser carpoolUser = apiCarpoolUserService.selectOne(user);
        return toResponsSuccess(carpoolUser);
    }

}
