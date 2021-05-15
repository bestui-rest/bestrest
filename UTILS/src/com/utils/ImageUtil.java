package com.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
	
	private static final String [] CHARS = 
		{"1","2","3","4","5","6","7","8","9"};
	
	private static final Integer SIZE = 4;
	private static final Integer WIDTH = 75;
	private static final Integer HEIGHT = 30;
	private static final Integer FONT_SIZE = 30;
	private static final Integer LINES = 20;
	
	//生成验证码图片，Map中的Key是验证码图片上的数字，
	//Value是包含验证码数字的图片
	public static Map<String,BufferedImage> createImage(){
		StringBuffer sb = new StringBuffer();
		BufferedImage image = new BufferedImage(
				WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		//获取画笔对象
		Graphics phs = image.getGraphics();
		//设置画笔颜色
		phs.setColor(Color.WHITE);
		//画背景
		phs.fillRect(0,0,WIDTH,HEIGHT);
		
		Random random = new Random();
		
		//画随机字符
		for(int i=0;i<SIZE;i++){
			int x = random.nextInt(CHARS.length);
			phs.setColor(new Color(random.nextInt(256),
					random.nextInt(256),random.nextInt(256)));
			phs.setFont(new Font("宋体",Font.BOLD,FONT_SIZE));
			
			//画字符
			phs.drawString(CHARS[x],(i*WIDTH)/SIZE,WIDTH/3);
			sb.append(CHARS[x]);
		}
		
		//画干扰线
		for(int i=0;i<LINES;i++){
			phs.setColor(new Color(random.nextInt(256),
					random.nextInt(256),random.nextInt(256)));
			phs.drawLine(
				random.nextInt(WIDTH),
				random.nextInt(HEIGHT),
				random.nextInt(WIDTH),
				random.nextInt(HEIGHT));
		}
		
		Map<String,BufferedImage> map =
			new HashMap<String, BufferedImage>();
		map.put(sb.toString(),image);
		
		return map;
	}
	
	//将图片转化成字节数组
	public static byte [] changeImage(BufferedImage image){
		ByteArrayOutputStream baos = null;
		try{
			baos = new ByteArrayOutputStream();
			//使用JPEGImageEncoder将图片压缩编码
			JPEGImageEncoder encoder =
				JPEGCodec.createJPEGEncoder(baos);
			encoder.encode(image);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return baos.toByteArray();
	}
	
	public static void main(String[] args ){
	Map<String,BufferedImage> sf=ImageUtil.createImage();
	Iterator it=sf.keySet().iterator();
	while(it.hasNext()){
		String key=(String)it.next();
		System.out.println(key);
		File tmpfile=new File("E:\\"+key+".jpg");
		try {
			ImageIO.write(sf.get(key), "jpg", tmpfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}
}
