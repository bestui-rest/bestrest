package com.ist.t47.common;

import java.io.BufferedReader;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSonUtil {

	public static Logger log=Logger.getLogger(JSonUtil.class);
	public JSonUtil(){		
	}
	public static ArrayList<RequestObject> readJson(String s,HashMap finMap ){
	      String key=(String)finMap.get("jiemi");
	      String readLocation=(String)finMap.get("INNERFILEPATH");
	      String writeLocation =(String)finMap.get("OUTRFILEPATH");
	      System.out.println(key+"\t"+readLocation+"\t"+writeLocation);
		File in = new File(readLocation+s);
		ArrayList<RequestObject> obj=new ArrayList<RequestObject>();
        String line="[";
        String msg;
	    InputStream is;
	      try {
	          is = new FileInputStream(in);
	          InputStreamReader isr = new InputStreamReader(is, "utf-8");
	          BufferedReader br = new BufferedReader(isr);
	          
	          while((msg = br.readLine()) != null){
	        	  try {
	        		  //解密
	        		  msg = DESUtil.decrypt(msg, key);
		              line=line+msg+",";
				} catch (Exception e) {
					log.info("解密出错了");
					throw new RuntimeException(e);
					//return null;
				}
	          }
	          br.close();
	          line=line.substring(0,line.length()-1)+"]";
	          log.info("获取到的一行数据:"+line);
	          line=line.replaceAll("people_id", "\"people_id\"");
	          line=line.replaceAll("xm", "\"xm\"");
	          line=line.replaceAll("sfzh", "\"sfzh\"");
	          line=line.replaceAll("hz\"sfzh\"", "\"hzsfzh\"");
	          log.info("REPLACEAll后的一行数据:"+line);          
	      } catch (FileNotFoundException e) {
	          e.printStackTrace();
	          throw new RuntimeException(e);
	      } catch (IOException e) {
	          e.printStackTrace();
	          throw new RuntimeException(e);
	      }
		ObjectMapper mapper=new ObjectMapper();
		try {
			obj=mapper.readValue(line, new TypeReference<List<RequestObject>>(){});
			log.info("JSNO对象一行数据："+obj);
			return obj;
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}
    public static void writeJson(List<ReturnObject> obj,String s,String s2,HashMap finMap){
          String key=(String)finMap.get("jiami");
	      String writeLocation =(String)finMap.get("OUTRFILEPATH");
	      String writeLocationM =(String)finMap.get("MWTRFILEPATH");
	      String writeLocationMT =(String)finMap.get("TEMPPUT");
    	ObjectMapper mapper=new ObjectMapper();
    	File f=new File(writeLocation+s);
    	File f1=new File(writeLocationM+s2);
    	File f2=new File(writeLocationMT+s);
    	try {
			if(f.exists())
				f.delete();
			if(f1.exists())
				f1.delete();
			if(f2.exists())
				f2.delete();
			f.createNewFile();
			f1.createNewFile();
			f2.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
    	if(obj!=null&&obj.size()>0){
    	try {
			FileOutputStream in=new FileOutputStream(f);
			OutputStreamWriter out=new OutputStreamWriter(in,"utf-8");
			PrintWriter writer=new PrintWriter(out);
			FileOutputStream in1=new FileOutputStream(f1);
			OutputStreamWriter out1=new OutputStreamWriter(in1,"utf-8");
			PrintWriter writer1=new PrintWriter(out1);
			FileOutputStream in2=new FileOutputStream(f2);
			OutputStreamWriter out2=new OutputStreamWriter(in2,"utf-8");
			PrintWriter writer2=new PrintWriter(out2);
			String st=null;
			for (int i=0;i<obj.size();i++){
//				String st=mapper.writeValueAsString(obj.get(i));
				System.out.println("st的数据前"+obj.get(i));
				st=mapper.writeValueAsString(obj.get(i));
				//加入空格
				st=st.replaceAll("\"people_id\":", "\"people_id\": ");
				st=st.replaceAll("\"xm\":", "\"xm\": ");
				st=st.replaceAll("\"kh_id\":", "\"kh_id\": ");
				st=st.replaceAll("\"sfzh\":", "\"sfzh\": ");
				st=st.replaceAll("\"zje\":", "\"zje\": ");
				st=st.replaceAll("\"lccpzjz\":", "\"lccpzjz\": ");
				st=st.replaceAll("\"zhxx\":", "\"zhxx\": ");
				st=st.replaceAll("\"jyjl\":", "\"jyjl\": ");
				st=st.replaceAll("\"lccp\":", "\"lccp\": ");
				//将默认字符串替换空格
				st=st.replaceAll("\"KSTRING\"", "\"\"");
				st=st.replaceAll("\"ZSTRING\"", "\"\"");
				st=st.replaceAll("\"JSTRING\"", "\"\"");
				st=st.replaceAll("\"LCSTRING\"", "\"\"");
				writer.println(DESUtil.encrypt(st, key));
				writer2.println(DESUtil.encrypt(st, key));
				writer1.println(st);
				System.out.println("st的数据"+st);
			}
			String result=mapper.writeValueAsString(obj);
			System.out.println("查询结果："+result);
			writer.flush();
			writer.close();
			writer1.flush();
			writer1.close();
			writer2.flush();
			writer2.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	}//end main if
	}
}
