package com.ist.t47.common;

import java.io.File;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CopyBean {
	public static ArrayList<InnerDao> copyIn(ArrayList<RequestObject> request,String s){
		//日期
		String dt=s.substring(0, s.indexOf("_"));
		String date=dt.substring(0,4)+"-"+dt.substring(4,6)+"-"+dt.substring(6);
		//核查批次号
		String ver=s.substring(s.indexOf("_")+1,s.lastIndexOf("_"));
		//序号
		String seq=s.substring(s.lastIndexOf("_")+1,s.lastIndexOf("."));
		ArrayList<InnerDao> inner=new ArrayList<InnerDao>();		
		for(int i=0;i<request.size();i++){
			InnerDao in=new InnerDao();
			in.setPeople_id(request.get(i).getPeople_id());
			in.setHzsfzh(request.get(i).getHzsfzh());
			in.setSfzh(request.get(i).getSfzh());
			in.setXm(request.get(i).getXm());
			in.setVerification_dt(date);
			in.setVerification_batch(ver);
			in.setVerification_seq(seq);
			inner.add(in);
		}
		return inner;
	}
    public static List<ReturnObject> copyOut(List<OuterDao> outer){
		ArrayList<ReturnObject> obj=new ArrayList<ReturnObject>();
		//DecimalFormat df = new DecimalFormat("#.00");
       if(outer!=null&&outer.size()>0)
		for(int i=0;i<outer.size();i++){
		    ReturnObject re=new ReturnObject();
		    re.setPeople_id(outer.get(i).getPeople_id());
		    re.setXm(outer.get(i).getXm());
		    re.setSfzh(outer.get(i).getSfzh());
		    re.setKh_id(outer.get(i).getKh_id());	  
		    re.setZje( outer.get(i).getZje().setScale(2, RoundingMode.HALF_UP).toString());
		    re.setLccpzjz(outer.get(i).getLccpzjz().setScale(2, RoundingMode.HALF_UP).toString());	
		    re.setZhxx(outer.get(i).getZhxx());
		    re.setJyjl(outer.get(i).getJyjl());
		    re.setLccp(outer.get(i).getLccp());
		    obj.add(re);		    		    
		}
		return obj;
	}
}
