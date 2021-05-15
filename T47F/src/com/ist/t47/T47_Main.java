package com.ist.t47;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ist.t47.common.CopyBean;
import com.ist.t47.common.InnerDao;
import com.ist.t47.common.JSonUtil;
import com.ist.t47.common.OuterDao;
import com.ist.t47.common.RequestObject;
import com.ist.t47.common.ReturnObject;
import com.ist.t47.common.T47Flog;
import com.ist.t47.common.T47MainDao;
import com.ist.t47.util.MyBatisUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class T47_Main{
public	static	String	MAIN_SERVER_LOG_FILE="log4j-audit.properties";
public	static	String	TEPFILEPATH;
public	static	String	INNERFILEPATH;
public	static	String	OUTRFILEPATH;
public	static	String	MWTRFILEPATH;
public	static	String	SHSFTP;
private static  String PUTTOFTP;
private static  String TEMPPUT;
private static  Connection con;
private static  String bankcode;
private static  String jiemi;
private static  String jiami;
private static  String prefix;
private static  HashMap hashMap;
private static  String sysString;
private static  SqlSession sqlSession;
private static  Logger logger;
private static  String windowR="t47_win.properties";
private static  String LinuxR="t47.properties";
private static  String PPR;
public  void init(){
	//配置日志信息
	PropertyConfigurator.configure(this.getClass().getResource(MAIN_SERVER_LOG_FILE));
	logger = Logger.getLogger(T47_Main.class);
	//加载系统信息
	Properties pps=System.getProperties();
	sysString=pps.getProperty("os.name");
	pps=null;
	//判断系统信息
	if(sysString.indexOf("Linux")!=-1)
		PPR=this.LinuxR;
	else
		PPR=this.windowR;	
	//加载配置
	InputStream fi=this.getClass().getResourceAsStream(PPR);
	Properties pp=new Properties();
	try {
	pp.load(fi);
	bankcode=pp.getProperty("bankcode");
	jiemi=pp.getProperty("jiemi");
	jiami=pp.getProperty("jiami");
	prefix=pp.getProperty("prefix");
	TEPFILEPATH=pp.getProperty("TEPFILEPATH");
	INNERFILEPATH=pp.getProperty("INNERFILEPATH");
	OUTRFILEPATH=pp.getProperty("OUTRFILEPATH");
	MWTRFILEPATH=pp.getProperty("MWTRFILEPATH");
	SHSFTP=pp.getProperty("SHSFTP");
	PUTTOFTP=pp.getProperty("PUTTOFTP");
	TEMPPUT=pp.getProperty("TEMPPUT");
	pp=null;
	} catch (IOException e) {
		e.printStackTrace();
	}	
	//获取sqlsession
	sqlSession= MyBatisUtil.getSession();
	//初始化hashmap
	hashMap=new HashMap();
	hashMap.put("TEPFILEPATH", TEPFILEPATH);
	hashMap.put("INNERFILEPATH", INNERFILEPATH);
	hashMap.put("OUTRFILEPATH", OUTRFILEPATH);
	hashMap.put("MWTRFILEPATH", MWTRFILEPATH);
	hashMap.put("TEMPPUT", TEMPPUT);
	hashMap.put("bankcode", bankcode);
	hashMap.put("jiemi", jiemi);
	hashMap.put("jiami", jiami);
	hashMap.put("prefix", prefix);
	//清理tempput
	File fgs=new File(TEMPPUT);
	File []fils=fgs.listFiles();
	if(fils!=null&&fils.length>0){
		for(int z=0;z<fils.length;z++){
			File tm=fils[z];
			tm.delete();
		}
	}
	
}
public static void main(String[] args){
	T47_Main t47main=new T47_Main();
	ArrayList<String> tempFilString=null;
	ArrayList<String> dbFilString=null;
	ArrayList<String> dbdealFilString=null;
	t47main.init();
	logger.info("数据库连接获取正常");
	logger.info("操作系统"+sysString);	
	//Linux系统
	if(sysString.indexOf("Linux")!=-1){
		logger.info("开始执行SFTP获取文件脚本");
		Process pr=null;
		int sfpf=1;
		try {
		   pr=Runtime.getRuntime().exec(SHSFTP);
		   sfpf=pr.waitFor();
	       if(sfpf==0)
			  logger.info("执行SFTP获取文件脚本成功");
		   } catch (Exception e) {
		    logger.info("执行SFTP脚本异常");
		   }
 //脚本执行成功
	if(sfpf!=1){
		//01 获取路径下的所有文件  民政局 所有文件
		tempFilString=t47main.getFileString(TEPFILEPATH);
		//02  获取所有所有数据库中文件
		T47Flog t47_log=new T47Flog();
		dbFilString=t47main.getFileStringDB(t47_log,sqlSession,"数据库T47_FINLOG文件数目");
		t47_log=null;
		try{
		//03   数据库中文件名称与民政局所有文件进行比对    将未处理文件  放入INNER  并且 入库 T47_FINLOG表
		    t47main.fileToDB(tempFilString,dbFilString,sqlSession);
		}catch(Exception e){
	        logger.info("插入入库T47_FINLOG表异常");
		}
		logger.info("获取本次处理文件列表:T47_FINLOG表状态为1的文件数");
		T47Flog t47_log2=new T47Flog();
		t47_log2.setMiStatus("1");
		dbdealFilString=t47main.getFileStringDB(t47_log2,sqlSession,"本次需匹配处理文件个数");
		logger.info("执行主要加密解密程序入库T47_FINSEARCH 取数并生成加密反馈文件");
		t47main.writeTo(dbdealFilString);
		t47_log2=null;
		t47main.dealResouce();
		t47main.putToFTP();
		}
	}
	// Windows 测试系统
	else if(sysString.indexOf("Windows")!=-1){
		//01 获取路径下的所有文件  民政局 所有文件
		tempFilString=t47main.getFileString(TEPFILEPATH);
		//02  获取所有所有数据库中文件
		T47Flog t47_log=new T47Flog();
		dbFilString=t47main.getFileStringDB(t47_log,sqlSession,"数据库T47_FINLOG文件数目");
		t47_log=null;
		try{
		//03   数据库中文件名称与民政局所有文件进行比对    将未处理文件  放入INNER  并且 入库 T47_FINLOG表
		    t47main.fileToDB(tempFilString,dbFilString,sqlSession);
		}catch(Exception e){
	        logger.info("插入入库T47_FINLOG表异常");
		}
		logger.info("获取本次处理文件列表:T47_FINLOG表状态为1的文件数");
		T47Flog t47_log2=new T47Flog();
		t47_log2.setMiStatus("1");
		dbdealFilString=t47main.getFileStringDB(t47_log2,sqlSession,"本次需匹配处理文件个数");
		logger.info("执行主要加密解密程序入库T47_FINSEARCH 取数并生成加密反馈文件");
		t47main.writeTo(dbdealFilString);
		t47_log2=null;
		t47main.dealResouce();
	}
}
public void putToFTP(){
	try {
		File fsss=new File(TEMPPUT);
		File[] fvb=fsss.listFiles();
		if(fvb!=null&&fvb.length>0){
			logger.info("推送"+fvb.length+"个加密文件");
			Process p=Runtime.getRuntime().exec(PUTTOFTP);
			int i=p.waitFor();
			if(i==0)
			 logger.info("执行推送民政局文件脚本PUTTOFTP成功");
		}else{
			logger.info("推送0个加密文件");
		}
	} catch (Exception e) {
	    logger.info("执行putToFTP脚本异常"+e);
	}
}
public void getCon(){
	try{
		InputStream fi=this.getClass().getResourceAsStream(PPR);
		Properties pp=new Properties();
		pp.load(fi);
		String url=pp.getProperty("url");
		String pwd=pp.getProperty("pwd");
		String username=pp.getProperty("username");
		String driver=pp.getProperty("driver");
		Class.forName(driver);
		con=DriverManager.getConnection(url, username, pwd);
	}catch(Exception e){
		e.printStackTrace();	
	}
}
public void dealResouce(){
	sqlSession.close();
	logger.info("任务完成 资源关闭成功");
}

public void writeTo(ArrayList<String> dbdealFilString ){
	T47MainDao t47mainDao=new T47MainDao();
	boolean fa=true;
	if(dbdealFilString!=null &&dbdealFilString.size()>=1){
		logger.info("解密取数程序处理文件个数"+dbdealFilString.size()+"个");
		for(int l=0;l<dbdealFilString.size();l++){
			fa=true;
			T47Flog ts4=new T47Flog();
			ts4.setMiStatus("4");
			ts4.setMingwStatus("2");
			String tsstr=dbdealFilString.get(l);
			logger.debug("匹配程序正在处理的文件"+tsstr);
			ts4.setXqFileName(tsstr);
			ArrayList<RequestObject> ro = null;
			try {
				//解析数据
				ro=JSonUtil.readJson(tsstr,hashMap);
			} catch (Exception e) {			
				ts4.setErrmsg("解析失败");
				ts4.setMiStatus("5");
				ts4.setMingwStatus("5");
				fa=false;
			}
			if(fa){
			try {
			ArrayList<InnerDao> in=CopyBean.copyIn(ro,tsstr);
			String dt=tsstr.substring(0, tsstr.indexOf("_"));
			String date=dt.substring(0,4)+"-"+dt.substring(4,6)+"-"+dt.substring(6);
			String ver=tsstr.substring(tsstr.indexOf("_")+1,tsstr.lastIndexOf("_"));
			String seq=tsstr.substring(tsstr.lastIndexOf("_")+1,tsstr.lastIndexOf("."));
			InnerDao id=new InnerDao();
			id.setVerification_dt(date);
			id.setVerification_batch(ver);
			id.setVerification_seq(seq);
			t47mainDao.delInnerDao(sqlSession,id);
			t47mainDao.insertInnerDao(sqlSession, in);
		    logger.debug("执行插入到需求表成功");
			t47mainDao.matchInnerDao(sqlSession,id);
		 	logger.debug("执行MATCH存储过程");
			t47mainDao.updateZhL(sqlSession,id);
			logger.debug("执行UPDATE JSON表成功");
				List<OuterDao> od=t47mainDao.getOuterDao(sqlSession,id);
				logger.debug("查询结果数据:"+od);
				List<ReturnObject> reo=CopyBean.copyOut(od);
				logger.debug("查询结果数据:"+reo);
				JSonUtil.writeJson(reo,ver+"_"+bankcode+"_"+seq+".txt",ver+"_"+bankcode+"_"+seq+prefix+".txt",hashMap);			
				ts4.setErrmsg("匹配成功");
			} catch (Exception e) {
				e.printStackTrace();		
				ts4.setErrmsg(" 匹配出错！");	
				ts4.setMiStatus("5");
				ts4.setMingwStatus("5");				 
			}
			}
			// 出错的文件不会推送  可以在此加入推送错误文件的生成程序只推送一个空文件
			try {
				t47mainDao.updataT47Log(ts4,sqlSession);
			} catch (SQLException e) {
				logger.info("更改T47LOG表状态失败");
			}
		}
	}else{
		logger.info("解密取数程序处理文件个数0个");
	}
}
public void fileToDB(ArrayList<String> tempFilString,ArrayList<String> dbString,SqlSession sqlSession){
	logger.info("开始执行比对程序");	
	ArrayList<String> te=new ArrayList<String>();
	if(dbString!=null&&dbString.size()>0){
		if(tempFilString!=null&&tempFilString.size()>0){
			for(int h=0;h<tempFilString.size();h++){
				String tr=tempFilString.get(h);
				if(!dbString.contains(tr)){
					te.add(tr);
				}
			}
		}
	}else{
		te=tempFilString;
	}
	if(te!=null&&te.size()>0){
		logger.info("本次比对结果获取需入库T47_FINLOG文件数"+te.size());
		logger.info("开始将比对结果文件写入到INNER目录,并入库T47_FINLOG表");
		for(int d=0;d<te.size();d++){
			String match=te.get(d);
			    InputStream is = null;
		        OutputStream os = null;
		       try {
		            is = new FileInputStream(TEPFILEPATH+match);
		            os = new FileOutputStream(INNERFILEPATH+match);
		            int len = 0;
		            byte[] bytes = new byte[1024*1024];
		            while ((len = is.read(bytes)) > 0) {
		                os.write(bytes, 0, len);
		            }		      		            
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println(e);
		        } finally {
		            try {
		                if (os != null) {
		                    os.close();
		                }
		                if (is != null) {
		                    is.close();
		                }
		            } catch (Exception e2) {
		                e2.printStackTrace();
		            }
		        }
		    T47Flog ts1=new T47Flog();
		    ts1.setXqFileName(match);
			String dt=match.substring(0, match.indexOf("_"));
			String date=dt.substring(0,4)+"-"+dt.substring(4,6)+"-"+dt.substring(6);
			String ver=match.substring(match.indexOf("_")+1,match.lastIndexOf("_"));
			String seq=match.substring(match.lastIndexOf("_")+1,match.lastIndexOf("."));
			String resultname1=ver+"_"+bankcode+"_"+seq+prefix+".txt";
			String resultname2=ver+"_"+bankcode+"_"+seq+".txt";
			ts1.setVerification_DT(date);
			ts1.setVerification_BATCH(ver);
			ts1.setVerification_SEQ(seq);
			ts1.setMwFileName(resultname1);
			ts1.setMiFileName(resultname2);
			ts1.setMiStatus("1");
			ts1.setMingwStatus("1");
			sqlSession.insert("com.ist.t47.T47Fin.insertT47Log", ts1);
			sqlSession.commit();
		}
	}else{
		logger.info("本次比对结果获取需入库T47_FINLOG文件数0个");
	}
	logger.info("比对结果文件写入到INNER目录,并入库T47_FINLOG表结束");
}
public ArrayList<String> getFileString(String path){
	File fg=new File(path);
	File []tempFile=fg.listFiles();
	ArrayList<String> arr=new ArrayList<String>();
	if (tempFile!=null&&tempFile.length>=1){
		logger.info("获取到所有民政局文件数TEMP"+tempFile.length);
		for(int i=0;i<tempFile.length;i++){
	     File fh=tempFile[i];
	     arr.add(fh.getName());
		}
	}else{
		logger.info("获取到所有民政局文件数TEMP0个");
	}
	return arr;
}
public ArrayList<String> getFileStringDB(T47Flog t47Flog,SqlSession sqlsession,String listname){
	ArrayList<String> temString=new ArrayList<String>();
	List<T47Flog> list=sqlSession.selectList("com.ist.t47.T47Fin.getT47Log", t47Flog);
	if(list!=null&&list.size()>0){
		logger.info("获取到"+listname+list.size()+"个");
		for(int j=0;j<list.size();j++){
		   T47Flog gd=list.get(j);
		   temString.add(gd.getXqFileName());
		}
	}else{
		logger.info("获取到"+listname+"0个");
	}
	list=null;
	return temString;
}
}
