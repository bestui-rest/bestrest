package com.algorithm;

public class DataOrder {
	public static void Buble(Integer gh[]){
		int leng=gh.length;
		for(int i=0;i<leng-1;i++){
			for(int j=0;j<leng-i-1;j++){
				if(gh[j]>gh[j+1]){
				    int t=gh[j];
				    gh[j]=gh[j+1];
				    gh[j+1]=t;
				}
				
			}
		}
	}
	public static void Selsct(Integer gh[]){
		int leng=gh.length;
		for(int i=0;i<leng-1;i++){
			int k=i;
			for(int j=i+1;j<gh.length;j++){
				if(gh[k]>gh[j]){
					k=j;
				}
			}
			if(k!=i){
				int t=gh[k];
				gh[k]=gh[i];
				gh[i]=t;
			}
		}
	}
}
