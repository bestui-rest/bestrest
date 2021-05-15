package com.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import com.bean.EMP;

import com.database.ConnectionUtils;

public class Rej {
	 public static Object initValue(Object _target, TreeMap value)
	  {
	    try
	    {
	    	//rs.getMetaData().getColumnCount();
	      Object method = _target.getClass().newInstance();
	      Method[] methods = method.getClass().getMethods();

	      Field[] yy = method.getClass().getDeclaredFields();
	      String[] proname = new String[yy.length];
	      for (int j = 0; j < yy.length; ++j) {
	        proname[j] = yy[j].getName();
	      }
	      for (int ff = 0; ff < yy.length; ++ff) {
	        try {
	          Object temp_data = value.get(proname[ff].toUpperCase());
	          if (temp_data != null) {
	            String _methodname = "set" + 
	              proname[ff].substring(0, 1).toUpperCase() + 
	              proname[ff].substring(1);
	            for (int dd = 0; dd < methods.length; ++dd) {
	              try {
	                if ((methods[dd].getName() == _methodname) || 
	                  (methods[dd].getName().equals(_methodname)))
	                {
	                  methods[dd].invoke(method, new Object[] { temp_data });
	                }
	              }
	              catch (Exception ex) {
	                ex.printStackTrace();
	              }
	            }
	          }

	        }
	        catch (Exception ex)
	        {
	         // DbException.saveExceptionLog(logger, "2", ex);
	          ex.printStackTrace();
	        }
	      }
	      return method;
	    } catch (Exception ex) {
	      ex.printStackTrace(); }
	    return null;
	  }
	 
 public  ArrayList<TreeMap> getRsTreeMap(Connection con,String sql){

	 ArrayList<TreeMap> at=new ArrayList<TreeMap>();
	 
	 try {
		PreparedStatement $pst=con.prepareStatement(sql);
	    ResultSet rs=$pst.executeQuery();
		
		while(rs.next()){
			 TreeMap map=new TreeMap();
			for(int i=0;i<rs.getMetaData().getColumnCount();i++){
				 map.put(rs.getMetaData().getColumnName(i+1), rs.getObject(i+1));
			}
			 at.add(map);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 return at;
 }
 
 public <T> T getInstanceByClassStr(String str){
	 
	 T t=null;
	 try {
		Class clas=Class.forName(str);
		Constructor c=clas.getConstructor(String.class,Integer.class);
		t=(T)c.newInstance("×Ö·û´®",12);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 return t;
 }
 public static void main(String[] args) {
	 ArrayList<TreeMap> at=new Rej().getRsTreeMap(ConnectionUtils.getConnection(), "select * from t00_organ");
	 System.out.println(at);
	 Rej rj=new Rej();
	 EMP tt= rj.<EMP>getInstanceByClassStr("com.zyd.xmlutil.bean.EMP");
	 System.out.println(tt);
	
}

}
