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
 public class Test3  {
 
     private int tnum = 1;// �̱߳��,Thread Number
 
     private ReentrantLock lock = new ReentrantLock();
 
     private Condition redCon = lock.newCondition();
     private Condition greenCon = lock.newCondition();
 
     public static void main(String[] args) {
         new Test3().run();
     }
 

     public void run() {
         new Thread(new RedThread(), "red light").start();
         new Thread(new GreenThread(), "green light").start();
     }
 
     class RedThread implements Runnable {
 
         @Override
         public void run() {
             while (true) {
                 try {
                     lock.lock();
                     while (tnum != 1) {// �ж��Ƿ���Լ�ִ����[����while����if��Ϊ�˷�ֹ����]
                         redCon.await();
                     }
                     System.out.println(Thread.currentThread().getName()+ " is flashing...");
 
                     TimeUnit.SECONDS.sleep(1);// ͣ��ʱ�䣬���ڴӿ���̨�ۿ�
 
                     tnum = 2;
                     greenCon.signal();
 
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } finally {
                     lock.unlock();
                 }
             }
         }
     }
 
     class GreenThread implements Runnable {
 
         @Override
         public void run() {
 
             while (true) {
                 try {
                     lock.lock();
                     while (tnum != 2) {
                         greenCon.await();
                     }
                     System.out.println(Thread.currentThread().getName()+ " is flashing...");
 
                     TimeUnit.SECONDS.sleep(1);// ͣ��ʱ�䣬���ڴӿ���̨�ۿ�
 
                     tnum = 1;
                     redCon.signal();
 
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } finally {
                     lock.unlock();
                 }
             }
         }
 
     }
 
 }
