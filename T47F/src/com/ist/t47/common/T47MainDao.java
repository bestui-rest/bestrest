package com.ist.t47.common;

import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
public class T47MainDao {
	public void insertInnerDao(SqlSession sqlSession, ArrayList<InnerDao> in)throws SQLException {
		for (int i=0;i<in.size();i++){
		 sqlSession.insert("com.ist.t47.T47Fin.insertInnerDao", in.get(i));
		}
		sqlSession.commit();
	}
	public void delInnerDao(SqlSession sqlSession, InnerDao in)throws SQLException {
		 sqlSession.update("com.ist.t47.T47Fin.delInnerDao", in);	
		 sqlSession.commit();
	}
	public void matchInnerDao(SqlSession sqlSession, InnerDao in)throws SQLException {
		 sqlSession.update("com.ist.t47.T47Fin.matchInnerDao", in);	
		 sqlSession.commit();
	}
	public void updateZhL(SqlSession sqlSession, InnerDao in)throws SQLException {
		List<OuterDao> old=getOuterDao(sqlSession,in);
		sqlSession.commit();
	if(old!=null&&old.size()>0){
		for(int i=0;i<old.size();i++){
			OuterDao outtemp=old.get(i);
			String acctFlag=outtemp.getAcct_num();
			String transFlag=outtemp.getTrans_num();
			String kh_id=outtemp.getKh_id();
			if("KSTRING".equals(kh_id))
				continue;
			//ÕË»§×Ö·û´®
			if(!"0".equals(acctFlag)){
				String lineAcct="[";
				List<AcctTransDao> ats=sqlSession.selectList("com.ist.t47.T47Fin.selAcctOuterDao",outtemp);
				for(int i1=0;i1<ats.size();i1++){
					AcctTransDao acttemp =ats.get(i1);
					if(i1!=0){
						lineAcct=lineAcct+",";	
					}
					//lineAcct=lineAcct+"{\\\"zh\\\":\\\""+acttemp.getZh()+"\\\",\\\"zhlx\\\":\\\""+acttemp.getZhlx()+
					//"\\\",\\\"zhje\\\":\\\""+acttemp.getZhje()+"\\\"}";
					lineAcct=lineAcct+"{\"zh\":\""+acttemp.getZh()+"\",\"zhlx\":\""+acttemp.getZhlx()+
					"\",\"khh\":\""+acttemp.getKhh()+"\",\"zhje\":\""+acttemp.getZhje().setScale(2, RoundingMode.HALF_UP).toString()+"\"}";
				}
				lineAcct=lineAcct+"]";
				outtemp.setZhxx(lineAcct);			
			}
			//½»Ò××Ö·û´®	
            if(!"0".equals(transFlag)){
            	String lineTrans="[";
            	List<AcctTransDao> atsT=sqlSession.selectList("com.ist.t47.T47Fin.selectTransOuterDao",outtemp);
            	for(int i2=0;i2<atsT.size();i2++){
            		AcctTransDao atsTtemp=atsT.get(i2);
            		if(i2!=0){
            			lineTrans=lineTrans+",";
            		}
            		//lineTrans=lineTrans+"{\\\"jyje\\\":\\\""+atsTtemp.getJyje().setScale(2, RoundingMode.HALF_UP).toString()+
            		//"\\\",\\\"jyrq\\\":\\\""+atsTtemp.getJyrq()+"\\\"}";
            		lineTrans=lineTrans+"{\"jyje\":\""+atsTtemp.getJyje().setScale(2, RoundingMode.HALF_UP).toString()+
            		"\",\"jyrq\":\""+atsTtemp.getJyrq()+"\"}";
            	}
            	lineTrans=lineTrans+"]";
            	outtemp.setJyjl(lineTrans);
            }
            sqlSession.update("com.ist.t47.T47Fin.updateOuterDao",outtemp);
            sqlSession.commit();
		}
	} //end main if
	}
	public List<OuterDao> getOuterDao(SqlSession sqlSession, InnerDao in)throws SQLException {
		List<OuterDao> od= sqlSession.selectList("com.ist.t47.T47Fin.getOuterDao", in);
		return od;
	}
	public void updataT47Log(T47Flog t47log,SqlSession sqlSession)throws SQLException {
		sqlSession.update("com.ist.t47.T47Fin.upT47Status", t47log);
		sqlSession.commit();
	}
	public void updataT47LogF(ArrayList<String> sdf,SqlSession sqlSession)throws SQLException {
		if(sdf!=null&&sdf.size()>0){
			for(int y=0;y<sdf.size();y++){
				String fg=sdf.get(y);
				T47Flog t47log=new T47Flog();
				t47log.setMiStatus("4");
				t47log.setXqFileName(fg);
		       sqlSession.update("com.ist.t47.T47Fin.updataT47LogF", t47log);
		       t47log=null;
			}
		sqlSession.commit();
		}
	}
}
