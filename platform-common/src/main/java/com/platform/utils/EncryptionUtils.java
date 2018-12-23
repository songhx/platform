/**
 * Project Name:omp-web
 * File Name:EncryptionUtils.java
 * Package Name:com.netease.ad.omp.common.utils
 * Date:2016年10月12日下午6:18:20
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.platform.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密解密工具类
 * ClassName:EncryptionUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年10月12日 下午6:18:20 <br/>
 * @author   bjzhangzhenzhen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class EncryptionUtils {
	public static final String BASE64 = "BASE64"; 
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";
	public static final String KEY_MAC = "HmacMD5";
	
	private  static Logger logger = LoggerFactory.getLogger(EncryptionUtils.class);
	
	/**
	 * 
	* @Title: getVersionCode 
	* @Description:  获取MD5
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getVersionCode(){
		return MD5(Long.toString(System.currentTimeMillis()));
	}
	
	/**
	 * base64 加密开始
	 * @param code
	 * @throws Exception
	 */
	public static String base64Encryption(String code){
		if(org.apache.commons.lang.StringUtils.isEmpty(code)){
			return code;
		}
		byte[] codebyte = code.getBytes();
		String codebase64 = encryptBASE64(codebyte);
		return codebase64;
	}
	
	/**
	 * base64 解密
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static  String base64Decrypt(String code){
		if(org.apache.commons.lang.StringUtils.isEmpty(code)){
    		return null;
    	}
		byte[] output = decryptBASE64(code);
		String outputStr = new String(output);
		return outputStr;
	}

    /**
     * 将二进制数据编码为BASE64字符串
     * @param binaryData
     * @return
     */
    public static String encryptBASE64(byte[] binaryData) {
        try {
            return new String(Base64.encodeBase64(binaryData), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        	logger.error(e.getMessage(),e);
            return null;
        }
    }
     
    /**
     * 将BASE64字符串恢复为二进制数据
     * @param base64String
     * @return
     */
    public static byte[] decryptBASE64(String base64String) {  	
        try {
            return Base64.decodeBase64(base64String.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
        	logger.error(e.getMessage(),e);
            return null;
        }
    }
    
    /**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data){

		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance(KEY_MD5);
		} catch (NoSuchAlgorithmException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		md5.update(data);

		return md5.digest();

	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);

		return sha.digest();

	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);

	}

	public final static String MD5(String pwd) {
		//用于加密的字符
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			//使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
			byte[] btInput = pwd.getBytes();

			//信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
			MessageDigest mdInst = MessageDigest.getInstance("MD5");

			//MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
			mdInst.update(btInput);

			// 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
			byte[] md = mdInst.digest();

			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {   //  i = 0
				byte byte0 = md[i];  //95
				str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
				str[k++] = md5String[byte0 & 0xf];   //   F
			}

			//返回经过加密后的字符串
			return new String(str);

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串MD5加密
	 * @param str
	 * @return
	 */
	public static String encryptByMd5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance(KEY_MD5);
		} catch (Exception e) {}

		byte[] bs = md5.digest(str.getBytes());
		StringBuilder sb = new StringBuilder(40);
		for(byte x:bs) {
			if((x & 0xff)>>4 == 0) {
				sb.append("0").append(Integer.toHexString(x & 0xff));
			} else {
				sb.append(Integer.toHexString(x & 0xff));
			}
		}
		return sb.toString();
	}

	public static void	main(String [] args){
		System.out.println(EncryptionUtils.MD5("admin"));
	}
}

