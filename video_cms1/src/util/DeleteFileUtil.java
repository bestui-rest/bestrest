package util;

import java.io.File;

//ɾ���ļ�
public class DeleteFileUtil {
	//�����ļ�
	public static void delFile(String path){
		File file = new File(path);
		if(file.exists()&&file.isFile()){
			file.delete();
		}
		
	}	
	//�ļ���
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
			file.delete();
		}
	}
	
	public static void main(String[] args) {
		//delFile("D:\\aaaaa.txt");
		delDirectory("D:\\aaa");
	}
}




