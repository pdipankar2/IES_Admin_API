package com.tcs.properties;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="admin")
public class AppProperties {
	
	private HashMap<String, String>msg=new HashMap<>();

	public HashMap<String, String> getMsg() {
		return msg;
	}

	public void setMsg(HashMap<String, String> msg) {
		this.msg = msg;
	}

}
