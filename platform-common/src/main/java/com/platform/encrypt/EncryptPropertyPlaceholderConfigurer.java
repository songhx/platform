package com.platform.encrypt;

import com.platform.utils.DESUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author bjsonghongxu
 * @create 2018-07-27 17:14
 **/
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    Logger logger = LoggerFactory.getLogger(EncryptPropertyPlaceholderConfigurer.class);
    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue){
        //如果在加密属性名单中发现该属性
        if (isEncryptProp(propertyName)){
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            return decryptValue;
        }else {
            return propertyValue;
        }

    }

    private boolean isEncryptProp(String propertyName){
        for (String encryptName : encryptPropNames)
        {
            if (encryptName.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }
}

