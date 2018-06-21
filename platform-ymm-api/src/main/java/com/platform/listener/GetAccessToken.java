package com.platform.listener;


import com.platform.thread.TokenThread;
import com.platform.utils.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GetAccessToken {
	private static final Logger logger = LoggerFactory.getLogger(GetAccessToken.class);

	private static boolean isStart = false;

	@PostConstruct
	public void listener() throws Exception {
		if (!isStart) {//这个可以解决项目启动加载两次的问题
			TokenThread.appid = ResourceUtil.getConfigByName("wx.appId");
			TokenThread.appsecret =ResourceUtil.getConfigByName("wx.secret");

			if("0".equals(ResourceUtil.getConfigByName("wx.accesstoken.open"))){
				new Thread(new TokenThread()).start();
			}
			isStart= true;
		}
	}
}
