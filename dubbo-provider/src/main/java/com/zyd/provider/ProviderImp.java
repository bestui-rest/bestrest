package com.zyd.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.zyd.dubbo.inter.ProService;
@Service
public class ProviderImp implements ProService {

	@Override
	public String sayHell(String name) {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String sayVoid(String name) {
		// TODO Auto-generated method stub
		return name;
	}

}
