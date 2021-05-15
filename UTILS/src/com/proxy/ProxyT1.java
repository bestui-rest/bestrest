package com.proxy;
/**
 *  JDK ´úÀí 
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;

 interface MyInter1{
	void run();
	void say();
}
 class TargeMy implements MyInter1{

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("okk");
	}
	 public void say(){
		 System.out.println("sq");
	 }
 }
 class ExtTare implements InvocationHandler{
   private TargeMy t1;
   public ExtTare(TargeMy t1){
	   this.t1=t1;
   }
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object obj=null;
	if("run".equals(method.getName())){
       System.out.println(method.getName()+"okk11111");
       obj= method.invoke(t1, args);
       System.out.println("okk222");
	}else{
	     obj= method.invoke(t1, args);
	}
		return obj;
	}
	 
 }
public class ProxyT1 {
 public static void main(String[] args) {
    TargeMy t1=new TargeMy();
	MyInter1 mh=(MyInter1) Proxy.newProxyInstance(TargeMy.class.getClassLoader(), TargeMy.class.getInterfaces(), new ExtTare(t1));
	mh.run();
	mh.say();
 }
}
