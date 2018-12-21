package com.platform.util;

import com.platform.utils.CharUtil;
import com.platform.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 生成订单的编号order_sn
     */
    public static String generateOrderNumber() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String timeStr = DateUtils.format(cal.getTime(), DateUtils.DATE_TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS);
        return timeStr + CharUtil.getRandomNum(6);
    }

    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public static Map<String, Object> toResponsObject(int requestCode, String msg, Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", requestCode);
        obj.put("message", msg);
        if (data != null)
            obj.put("data", data);
        return obj;
    }
}
