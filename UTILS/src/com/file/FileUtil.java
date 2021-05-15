package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//删除文件
public class FileUtil {
	//单个文件
	private static void delFile(String path){
		File file = new File(path);
		if(file.exists()&&file.isFile()){
			file.delete();
		}
	}
	//文件夹
	public static void delDirectory(String path){
		File file = new File(path);
		if(file.exists()&&file.isDirectory()){
			File[] fs = file.listFiles();
			for(File f:fs ){
				if(f.isFile()){
					f.delete();
				}else{
					delDirectory(f.getAbsolutePath());
				}
			}
		}
		file.delete();
	}
	
	public String copyFile(FileInputStream ins,String filename){
		try{
		File file=new File(filename);
		FileOutputStream filein=new FileOutputStream(file);
		byte[] buffer=new byte[1024*1024];   //1MB
		int len;
		while((len=ins.read(buffer,0,1024))>0){
			filein.write(buffer,0,len);
		}
		filein.flush();
		filein.close();
	}catch(Exception e){
		System.out.println(e);
	}
	return "od";
	}
}