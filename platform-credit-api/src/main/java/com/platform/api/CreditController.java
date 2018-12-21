package com.platform.api;

import com.platform.entity.vo.CreditQueryVo;
import com.platform.service.ICreditInfoService;
import com.platform.util.ApiBaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 征信查询控制器
 *
 * @author bjsonghongxu
 * @create 2018-12-21 16:47
 **/
@RestController
@RequestMapping("/api/credit/")
public class CreditController  extends ApiBaseAction {

    private static Logger logger = LoggerFactory.getLogger(CreditController.class);

    @Autowired
    private ICreditInfoService iCreditInfoService;

    /**
     * 查询征信申请
     * @param queryVo 查询参数
     * @return  成功返回申请id
     */
    @RequestMapping("query")
    public Object query(CreditQueryVo queryVo) {
        logger.info("query vo --" + queryVo.toString() );
        return iCreditInfoService.query(queryVo);
    }

    /**
     * 查询个人征信信息
     * @param queryVo
     * @return
     */
    @RequestMapping("info")
    public Object info(CreditQueryVo queryVo) {
        logger.info("query vo --" + queryVo.toString() );
        return iCreditInfoService.info(queryVo);
    }
}
