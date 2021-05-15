package com.cmd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class CmdShell {
    public static void runCMDShow(String path) throws Exception
    {
    	 String osname=System.getProperty("os.name");
    	 System.out.println(osname);
       //   Process p = Runtime.getRuntime().exec(" cmd /k start cmd.exe /k"+path);
            Process p = Runtime.getRuntime().exec(" cmd /c start cmd.exe /c arp -a");
      // Process p = Runtime.getRuntime().exec("sh test.sh ");
         dealProcessStream(p.getInputStream());
         dealProcessStream(p.getErrorStream()); 
         p.waitFor(); //0  成功  1 失败
        p.destroy();
        p=null;
    }
    public static void main(String[] args) {  
        String path = "F:\\lll.bat"; 
        System.out.println(new Date());
        try {
            runCMDShow(path);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(new Date());
    } 
    /**
     * 处理进程中的输出流
     */
    public static  void dealProcessStream(InputStream in){
        BufferedReader br = new BufferedReader(new InputStreamReader(in));  
        try{
	        String readLine = br.readLine();  
	        while (readLine != null) {
	            readLine = br.readLine();
	            System.out.println(readLine);
	        }
	        br.close();
        }catch ( Exception e){
        	e.printStackTrace();
        }
    }
}




