package com.utils;

import java.io.PrintStream;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MessageDigest
{
  public String getMD5Digest(String strInfo)
  {
    String strInfoDigest = "";
    try
    {
      java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("MD5");
      messageDigest.update(strInfo.getBytes());
      byte[] bInfoDigest = messageDigest.digest();
      strInfoDigest = byteToHex(bInfoDigest);
    }
    catch (NoSuchAlgorithmException ex)
    {
      System.out.println("非法摘要算法");
    }
    return strInfoDigest.toLowerCase();
  }
  
  public String byteToHex(byte[] bInfoDigest)
  {
    String strInfoDigest = "";
    String strTemp = "";
    for (int i = 0; i < bInfoDigest.length; i++)
    {
      strTemp = Integer.toHexString(bInfoDigest[i] & 0xFF);
      if (strTemp.length() == 1) {
        strInfoDigest = strInfoDigest + "0" + strTemp;
      } else {
        strInfoDigest = strInfoDigest + strTemp;
      }
    }
    return strInfoDigest.toUpperCase();
  }
  
  public byte[] hexToByte(String strInfo)
  {
    String strHexIndex = "0123456789abcdef0123456789ABCDEF";
    int iInfoLength = strInfo.length() / 2;
    byte[] bData = new byte[iInfoLength];
    int j = 0;
    for (int i = 0; i < iInfoLength; i++)
    {
      char c = strInfo.charAt(j++);
      
      int n = strHexIndex.indexOf(c);
      int b = (n & 0xF) << 4;
      c = strInfo.charAt(j++);
      n = strHexIndex.indexOf(c);
      b += (n & 0xF);
      bData[i] = ((byte)b);
    }
    return bData;
  }
  public static void main(String[] args) {
	MessageDigest mest=new MessageDigest();
	System.out.println(mest.getMD5Digest("123456"));
}
}
