package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.constants.CarpoolConstant;
import com.platform.constants.CommonConstant;
import com.platform.dto.CarpoolUserVo;
import com.platform.entity.CarpoolCar;
import com.platform.entity.CarpoolUser;
import com.platform.entity.UserVo;
import com.platform.service.ApiCarpoolUserService;
import com.platform.service.ApiCartService;
import com.platform.service.CarpoolCarService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.Constant;
import com.platform.utils.DateUtils;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CarpoolCarService carpoolCarService;

    /**
     * 查找拼车用户信息
     * @return
     */
    @RequestMapping("queryUser")
    public Object queryUser(CarpoolUser user ) {
        user.setDataStatus(CommonConstant.USEABLE_STATUS);
        CarpoolUser carpoolUser = apiCarpoolUserService.selectOne(user);
        List<CarpoolCar> carpoolCarList = null;
        if(null != carpoolUser){
            CarpoolCar carpoolCar = new CarpoolCar();
            carpoolCar.setUserId(carpoolUser.getId());
            carpoolCarList = carpoolCarService.select(carpoolCar);
        }
        Map<String, Object> resultObj = new HashMap();
        resultObj.put("userInfo",carpoolUser);
        resultObj.put("carpoolCar",(carpoolCarList != null && carpoolCarList.size() > 0) ? carpoolCarList.get(0) : null);
        return toResponsSuccess(resultObj);
    }


    /**
     * 完善用户信息
     * @param carpoolUser
     * @return
     */
    @RequestMapping("completUser")
    public Object completUser(@RequestBody CarpoolUser carpoolUser) {

        if (null == carpoolUser.getId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        carpoolUser.setUpdateTime(new Date());
        apiCarpoolUserService.updateByPrimaryKeySelective(carpoolUser);
        return toResponsSuccess("保存成功");
    }

    @RequestMapping("completUserCar")
    public Object completUserCar(@RequestBody CarpoolCar carpoolCar) {

        if(StringUtils.isNotBlank(carpoolCar.getExpirationTimeStr())){
            Date  expirationTime = DateUtils.strToDate(carpoolCar.getExpirationTimeStr());
            carpoolCar.setExpirationTime(expirationTime);
        }
        if (null == carpoolCar.getId()){
            carpoolCarService.insertSelective(carpoolCar);
        }else {
            if (carpoolCar.getIsCarowner() != null && carpoolCar.getIsCarowner().intValue() == 1){ //是车主
                carpoolCarService.updateByPrimaryKeySelective(carpoolCar);
            }else {//不是删除以前的信息
                carpoolCarService.delete(carpoolCar);
            }

        }
        return toResponsSuccess("保存成功");
    }


}
