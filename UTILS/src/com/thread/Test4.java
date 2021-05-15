package com.thread;

import java.io.File;
import java.util.HashSet;

public class Test4 {
	private  static volatile boolean gh=true;
	private static Object ob=new Object();
	public static void main(String[] args) {
		
      Thread t1=new Thread(new Runnable(){
    	  @Override
    	public void run() {
    		  for(int i=0;i<100;i++){
    			  synchronized (ob) {
    				  if(gh){
    					  System.out.println("线程"+Thread.currentThread().getName()+"  "+i); 
    					  gh=false;
    				  }
    			  }
    		  }
    	  	}    	  
    	  });
      
      
      Thread t2=new Thread(new Runnable(){
    	  @Override
    	public void run() {
    		  for(int i=0;i<100;i++){
    			  synchronized (ob) {
    				  if(!gh){
    					  System.out.println("线程"+Thread.currentThread().getName()+"  "+i);   
    					  gh=true;
    		       		}  
    			  }
    		  }
    	  	}    	  
    	  });
      
      
      t1.start();
      t2.start();
	 	
	}
}
