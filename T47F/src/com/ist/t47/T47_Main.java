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
	//������־��Ϣ
	PropertyConfigurator.configure(this.getClass().getResource(MAIN_SERVER_LOG_FILE));
	logger = Logger.getLogger(T47_Main.class);
	//����ϵͳ��Ϣ
	Properties pps=System.getProperties();
	sysString=pps.getProperty("os.name");
	pps=null;
	//�ж�ϵͳ��Ϣ
	if(sysString.indexOf("Linux")!=-1)
		PPR=this.LinuxR;
	else
		PPR=this.windowR;	
	//��������
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
	//��ȡsqlsession
	sqlSession= MyBatisUtil.getSession();
	//��ʼ��hashmap
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
	//����tempput
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
	logger.info("���ݿ����ӻ�ȡ����");
	logger.info("����ϵͳ"+sysString);	
	//Linuxϵͳ
	if(sysString.indexOf("Linux")!=-1){
		logger.info("��ʼִ��SFTP��ȡ�ļ��ű�");
		Process pr=null;
		int sfpf=1;
		try {
		   pr=Runtime.getRuntime().exec(SHSFTP);
		   sfpf=pr.waitFor();
	       if(sfpf==0)
			  logger.info("ִ��SFTP��ȡ�ļ��ű��ɹ�");
		   } catch (Exception e) {
		    logger.info("ִ��SFTP�ű��쳣");
		   }
 //�ű�ִ�гɹ�
	if(sfpf!=1){
		//01 ��ȡ·���µ������ļ�  ������ �����ļ�
		tempFilString=t47main.getFileString(TEPFILEPATH);
		//02  ��ȡ�����������ݿ����ļ�
		T47Flog t47_log=new T47Flog();
		dbFilString=t47main.getFileStringDB(t47_log,sqlSession,"���ݿ�T47_FINLOG�ļ���Ŀ");
		t47_log=null;
		try{
		//03   ���ݿ����ļ������������������ļ����бȶ�    ��δ�����ļ�  ����INNER  ���� ��� T47_FINLOG��
		    t47main.fileToDB(tempFilString,dbFilString,sqlSession);
		}catch(Exception e){
	        logger.info("�������T47_FINLOG���쳣");
		}
		logger.info("��ȡ���δ����ļ��б�:T47_FINLOG��״̬Ϊ1���ļ���");
		T47Flog t47_log2=new T47Flog();
		t47_log2.setMiStatus("1");
		dbdealFilString=t47main.getFileStringDB(t47_log2,sqlSession,"������ƥ�䴦���ļ�����");
		logger.info("ִ����Ҫ���ܽ��ܳ������T47_FINSEARCH ȡ�������ɼ��ܷ����ļ�");
		t47main.writeTo(dbdealFilString);
		t47_log2=null;
		t47main.dealResouce();
		t47main.putToFTP();
		}
	}
	// Windows ����ϵͳ
	else if(sysString.indexOf("Windows")!=-1){
		//01 ��ȡ·���µ������ļ�  ������ �����ļ�
		tempFilString=t47main.getFileString(TEPFILEPATH);
		//02  ��ȡ�����������ݿ����ļ�
		T47Flog t47_log=new T47Flog();
		dbFilString=t47main.getFileStringDB(t47_log,sqlSession,"���ݿ�T47_FINLOG�ļ���Ŀ");
		t47_log=null;
		try{
		//03   ���ݿ����ļ������������������ļ����бȶ�    ��δ�����ļ�  ����INNER  ���� ��� T47_FINLOG��
		    t47main.fileToDB(tempFilString,dbFilString,sqlSession);
		}catch(Exception e){
	        logger.info("�������T47_FINLOG���쳣");
		}
		logger.info("��ȡ���δ����ļ��б�:T47_FINLOG��״̬Ϊ1���ļ���");
		T47Flog t47_log2=new T47Flog();
		t47_log2.setMiStatus("1");
		dbdealFilString=t47main.getFileStringDB(t47_log2,sqlSession,"������ƥ�䴦���ļ�����");
		logger.info("ִ����Ҫ���ܽ��ܳ������T47_FINSEARCH ȡ�������ɼ��ܷ����ļ�");
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
			logger.info("����"+fvb.length+"�������ļ�");
			Process p=Runtime.getRuntime().exec(PUTTOFTP);
			int i=p.waitFor();
			if(i==0)
			 logger.info("ִ�������������ļ��ű�PUTTOFTP�ɹ�");
		}else{
			logger.info("����0�������ļ�");
		}
	} catch (Exception e) {
	    logger.info("ִ��putToFTP�ű��쳣"+e);
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
	logger.info("������� ��Դ�رճɹ�");
}

public void writeTo(ArrayList<String> dbdealFilString ){
	T47MainDao t47mainDao=new T47MainDao();
	boolean fa=true;
	if(dbdealFilString!=null &&dbdealFilString.size()>=1){
		logger.info("����ȡ���������ļ�����"+dbdealFilString.size()+"��");
		for(int l=0;l<dbdealFilString.size();l++){
			fa=true;
			T47Flog ts4=new T47Flog();
			ts4.setMiStatus("4");
			ts4.setMingwStatus("2");
			String tsstr=dbdealFilString.get(l);
			logger.debug("ƥ��������ڴ�����ļ�"+tsstr);
			ts4.setXqFileName(tsstr);
			ArrayList<RequestObject> ro = null;
			try {
				//��������
				ro=JSonUtil.readJson(tsstr,hashMap);
			} catch (Exception e) {			
				ts4.setErrmsg("����ʧ��");
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
		    logger.debug("ִ�в��뵽�����ɹ�");
			t47mainDao.matchInnerDao(sqlSession,id);
		 	logger.debug("ִ��MATCH�洢����");
			t47mainDao.updateZhL(sqlSession,id);
			logger.debug("ִ��UPDATE JSON��ɹ�");
				List<OuterDao> od=t47mainDao.getOuterDao(sqlSession,id);
				logger.debug("��ѯ�������:"+od);
				List<ReturnObject> reo=CopyBean.copyOut(od);
				logger.debug("��ѯ�������:"+reo);
				JSonUtil.writeJson(reo,ver+"_"+bankcode+"_"+seq+".txt",ver+"_"+bankcode+"_"+seq+prefix+".txt",hashMap);			
				ts4.setErrmsg("ƥ��ɹ�");
			} catch (Exception e) {
				e.printStackTrace();		
				ts4.setErrmsg(" ƥ�����");	
				ts4.setMiStatus("5");
				ts4.setMingwStatus("5");				 
			}
			}
			// ������ļ���������  �����ڴ˼������ʹ����ļ������ɳ���ֻ����һ�����ļ�
			try {
				t47mainDao.updataT47Log(ts4,sqlSession);
			} catch (SQLException e) {
				logger.info("����T47LOG��״̬ʧ��");
			}
		}
	}else{
		logger.info("����ȡ���������ļ�����0��");
	}
}
public void fileToDB(ArrayList<String> tempFilString,ArrayList<String> dbString,SqlSession sqlSession){
	logger.info("��ʼִ�бȶԳ���");	
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
		logger.info("���αȶԽ����ȡ�����T47_FINLOG�ļ���"+te.size());
		logger.info("��ʼ���ȶԽ���ļ�д�뵽INNERĿ¼,�����T47_FINLOG��");
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
		logger.info("���αȶԽ����ȡ�����T47_FINLOG�ļ���0��");
	}
	logger.info("�ȶԽ���ļ�д�뵽INNERĿ¼,�����T47_FINLOG�����");
}
public ArrayList<String> getFileString(String path){
	File fg=new File(path);
	File []tempFile=fg.listFiles();
	ArrayList<String> arr=new ArrayList<String>();
	if (tempFile!=null&&tempFile.length>=1){
		logger.info("��ȡ�������������ļ���TEMP"+tempFile.length);
		for(int i=0;i<tempFile.length;i++){
	     File fh=tempFile[i];
	     arr.add(fh.getName());
		}
	}else{
		logger.info("��ȡ�������������ļ���TEMP0��");
	}
	return arr;
}
public ArrayList<String> getFileStringDB(T47Flog t47Flog,SqlSession sqlsession,String listname){
	ArrayList<String> temString=new ArrayList<String>();
	List<T47Flog> list=sqlSession.selectList("com.ist.t47.T47Fin.getT47Log", t47Flog);
	if(list!=null&&list.size()>0){
		logger.info("��ȡ��"+listname+list.size()+"��");
		for(int j=0;j<list.size();j++){
		   T47Flog gd=list.get(j);
		   temString.add(gd.getXqFileName());
		}
	}else{
		logger.info("��ȡ��"+listname+"0��");
	}
	list=null;
	return temString;
}
}
