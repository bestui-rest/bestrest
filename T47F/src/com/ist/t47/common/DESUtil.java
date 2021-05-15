package com.ist.t47.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESUtil {
	private static final String KEY_ALGORITHM = "DES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5PADDING";
    private static final String CHAR_SET = "UTF-8";
    
    private DESUtil(){}
    /**
     * DES ���ܲ���
     *
     * @param content
     *            ����������
     * @param password
     *            ��������
     * @return ����Base64ת���ļ�������
     */
    public static String encrypt(String content, String password){
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(password.getBytes(CHAR_SET), KEY_ALGORITHM));
            return new String(Base64.encodeBase64(cipher.doFinal(content.getBytes(CHAR_SET))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * DES ���ܲ���
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {
        try {
            // ʵ����
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // ʹ����Կ��ʼ��������Ϊ����ģʽ
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(password.getBytes(CHAR_SET), KEY_ALGORITHM));
            // ִ�в���
            byte[] result = cipher.doFinal(Base64.decodeBase64(content.getBytes(CHAR_SET)));
            return new String(result, CHAR_SET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
//      String password = "12345678";
//      String p = encrypt("{people_id:\"100001\",xm:\"����\",sfzh:\"320412198505021254\"}", password);
//      String s = decrypt(p, password);
//      System.out.println(p);
//      System.out.println(s);      
          ArrayList<RequestObject> obj=new ArrayList<RequestObject>();
  //        obj=JSonUtil.readJson("3203002017100017_95555_01.txt");
//          JSonUtil.writeJson(obj, "3203002017100017_95555_02.txt");
  }
}
