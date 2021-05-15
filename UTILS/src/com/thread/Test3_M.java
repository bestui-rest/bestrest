package com.thread;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

 
 /**  
  * @title �����߳����ν���ִ��
  * @description �ٶ������̷ֱ߳�Ϊ��ơ��̵ƣ�ִ�е��ڴ����Ϊ����-��-��-��-��-��...
  * @author ocaicai@yeah.net
  * @since 2013-8-17 ����12:51:11
  */
 public class Test3_M  {
 
     private volatile  int tnum = 1;// �̱߳��,Thread Number
     public Object obj=new Object();
 
     public static void main(String[] args) {
         new Test3_M().run();
     }
 

     public void run() {
    	 RedThread r1= new RedThread();
    	 GreenThread g1=new GreenThread() ;
         new Thread(r1, "red light").start();
         new Thread(g1, "green light").start();
     }
 
     class RedThread implements Runnable {

   
         public void run() {
        	 for(int i=0;i<10;i++) {
                 try {
                     while (tnum ==2) {// �ж��Ƿ���Լ�ִ����[����while����if��Ϊ�˷�ֹ����]
                    	 synchronized(obj){
                    		 obj.wait();
                    	 }
                     }
                     System.out.println(Thread.currentThread().getName()+ " is flashing...");
                     tnum=2;
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }finally{
                	 synchronized(obj){
                		 obj.notifyAll();
                	 }
                 }
         }
 

     }
     }
 
     class GreenThread implements Runnable {
 

         public void run() {
        	 for(int i=0;i<10;i++) {
                 try {
                     while (tnum == 1) {
                    	 synchronized(obj){
                    		 obj.wait();
                    	 }
                     }
                     System.out.println(Thread.currentThread().getName()+ " is flashing...");
 
                     tnum = 1;
        
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }finally{
                	 synchronized(obj){
                		 obj.notifyAll();
                	 }
                 }
        	 }
             
         }
 
     }
 
 }