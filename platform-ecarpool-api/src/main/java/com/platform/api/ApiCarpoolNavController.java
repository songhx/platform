package com.platform.api;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.constants.CommonConstant;
import com.platform.dto.NavVo;
import com.platform.entity.CarpoolNav;
import com.platform.service.CarpoolNavService;
import com.platform.util.ApiBaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bjsonghongxu
 * @create 2019-04-24 13:53
 **/
@RestController
@RequestMapping("/api/nav")
public class ApiCarpoolNavController extends ApiBaseAction {

    @Autowired
    private CarpoolNavService carpoolNavService;
    /**
     * 分页查询
     * @param navVo
     * @return
     */
    @RequestMapping("list")
    public Object list(NavVo navVo) {
        PageHelper.startPage(navVo.getStart(), navVo.getLimit(), true, false); //设置分页
        Example example = new Example(CarpoolNav.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataStatus", CommonConstant.USEABLE_STATUS);
        List<CarpoolNav> list = carpoolNavService.selectByExample(example);

        PageInfo<CarpoolNav> pageInfo = new PageInfo<>(list);

        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        returnMap.put("list", list);
        returnMap.put("totalPage",pageInfo.getPages());

        return toResponsSuccess(returnMap);
    }
}
