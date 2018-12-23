package com.platform.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);


    public static String createSign() {
        String sign = null;

        return  sign;
    }

    /**
     * 返回结果
     * @param requestCode
     * @param msg
     * @param data
     * @return
     */
    public static Map<String, Object> toResponsObject(int requestCode, String msg, Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", requestCode);
        obj.put("message", msg);
        if (data != null)
            obj.put("data", data);
        return obj;
    }



}
