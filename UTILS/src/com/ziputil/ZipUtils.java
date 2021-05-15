/**
 * Copyright 2003 (C) PANLAB ��All Rights Reserved.
 * ����         ���� 			����
 * 2003-10-20   ����                     ����
 */
package com.ziputil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

public class ZipUtils {

    public ZipUtils(){

    }

    /**
     * ѹ��һ���ļ�����Ŀ¼
     * @param zipFileName ѹ������ļ�����·��
     * @param inputFile ��Ҫ��ѹ�����ļ�·��
     * @throws Exception
     */
    public void zip(String zipFileName,String inputFile)throws Exception{
        zip(zipFileName,new File(inputFile));
    }

    /**
     *
     * @param zipFileName ѹ������ļ�����·��
     * @param inputFile Ҫ��ѹ�����ļ���������
     * @throws Exception
     */
    public void zip(String zipFileName,File inputFile)throws Exception{
        ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out,inputFile,"");
        System.out.println("zip done");
        out.close();
    }

    /**
     * ����ѹ������Ŀ¼���ߵ����ļ�
     * @param out Դ�ļ��������
     * @param f Ŀ��ѹ���ļ���������
     * @param base a
     * @throws Exception
     */
    public void zip(ZipOutputStream out,File f,String base)throws Exception{
        System.out.println("Zipping  "+f.getName());
        if (f.isDirectory())
        {
            File[] fl=f.listFiles();
            out.putNextEntry(new ZipEntry(base+"/"));
            for (int i=0;i<fl.length ;i++ )
            {
                zip(out,fl[i],base);
            }
        }
        else
        {
            base=base.length()==0?"":base+"/";
            out.putNextEntry(new ZipEntry(base+f.getName()));
            FileInputStream in=new FileInputStream(f);
            int b;
            while ((b=in.read()) != -1)
                out.write(b);
            in.close();
        }
    }
    

    /**
     * ѹ��һ���ļ�
     * @param zipFileName
     * @param fileList
     * @param base
     * @throws Exception
     */
    public void zip(String zipFileName,ArrayList fileList,String base)throws Exception{
        //ѹ���ļ���
        ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipFileName));
//        out.putNextEntry(new ZipEntry(base+"/"));
        for(int i=0;i<fileList.size();i++){
            String filename = (String)fileList.get(i);
            System.out.println("add to zip:"+filename);
            File file = new File(filename);
            zip(out,file,base);
            //
        }
        out.close();
    }
    

    /**
     * ��ѹ��
     * @param zipFileName ѹ���ļ�
     * @param outputDirectory Ŀ��·��
     * @throws Exception
     */
    public void unzip(String zipFileName,String outputDirectory)throws Exception{
        ZipInputStream in=new ZipInputStream(new FileInputStream(zipFileName));
        ZipEntry z;
        while ((z=in.getNextEntry() )!= null)
        {
            System.out.println("unziping "+z.getName());
            if (z.isDirectory())
            {
                String name=z.getName();
                name=name.substring(0,name.length()-1);
                File f=new File(outputDirectory+File.separator+name);
                f.mkdir();
                System.out.println("mkdir "+outputDirectory+File.separator+name);
            }
            else{
                File f=new File(outputDirectory+File.separator+z.getName());
                f.createNewFile();
                FileOutputStream out=new FileOutputStream(f);
                int b;
                while ((b=in.read()) != -1)
                    out.write(b);
                out.close();
            }
        }

        in.close();
    }
    public static void downloadZip(String path,HttpServletResponse response) {
        try {
        File file=new File(path);
        // ��������ʽ�����ļ���
        InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // ���response
        response.reset();

        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("text/html;charset=UTF-8;");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        } catch (IOException ex) {
        ex.printStackTrace();
        }finally{
             
        }
    }

    public static void main(String[] args)
    {
        try{
            ZipUtils t = new ZipUtils();
            ArrayList list = new ArrayList();
            String url1="F:\\video.sql";
            String url2="F:\\XMSCode.sql";
            String url3="F:\\myzip.Z";
            list.add(url1);
            list.add(url2);
            list.add(url3);
            //t.zip("F:\\ZIPTEST\\myzip.Z", list, "");
            t.unzip("F:\\789.zip", "F:\\ZIPTEST");
        }
        catch(Exception e){e.printStackTrace(System.out);}
    }
}
