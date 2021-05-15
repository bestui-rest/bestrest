package com.jsonutil;

import java.io.IOException;

import com.bean.UserBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

public class JsonUtils {
public static void main(String[] args) {

	UserBean  d=new UserBean();
	UserBean t=new UserBean();
	d.setCreate_dt("2019-12-11");
	d.setName("admin");
	d.setParty_class_cd("C");
	ObjectMapper mapper=new ObjectMapper();
	try {
		String json=mapper.writeValueAsString(d);
		System.out.println(json);
		try {
			t=mapper.readValue(json, t.getClass());
			System.out.println(t.getCreate_dt());
		     json=mapper.writeValueAsString(t);
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
}
}
