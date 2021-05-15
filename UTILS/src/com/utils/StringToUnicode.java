package com.utils;

public class StringToUnicode {

	
	public String toUnionde(String str){
		StringBuilder sb=new StringBuilder();
		
		char[] chr=str.toCharArray();
		for(char charTmp:chr){
			int b=(int)charTmp;
			String s="\\u"+Integer.toHexString(b);
			sb.append(s);
		}
		
		
		return sb.toString();
	}
	public static void main(String[] args) {
		StringToUnicode tt=new StringToUnicode();
		String str=tt.toUnionde("≤‚ ‘").replace("\\\\", "\\");
		System.out.println(str);
		char t1='\u6d4b';
		System.out.println(t1);

	}

	
}
