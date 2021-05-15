package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		//获取字节输出流
		OutputStream out = response.getOutputStream();
		//画板 图片映射区
		BufferedImage image = new BufferedImage(
				80, 40, BufferedImage.TYPE_INT_RGB);
		//画笔
		Graphics g = image.getGraphics();
		//设置画笔颜色
		Random r = new Random();
		g.setColor(new Color(r.nextInt(256),r.nextInt(256),
				r.nextInt(256)));
		g.fillRect(0,0,80,40);
		String num = getNum(5);
		//将正确的验证码保存到session中
		HttpSession session = request.getSession();
		session.setAttribute("rightCode", num);
		//画字符串
		g.setColor(Color.black);
		g.setFont(new Font(null,Font.BOLD,20));
		g.drawString(num, 5,30);
		
		//画线
		for(int i=0;i<5;i++){
			g.setColor(new Color(r.nextInt(256),r.nextInt(256),
					r.nextInt(256)));
			g.drawLine(r.nextInt(80),r.nextInt(40),
					r.nextInt(80), r.nextInt(40));
		}
		//压缩图片
		ImageIO.write(image,"jpeg",out);
		out.close();
	}

	//返回验证码
	public String getNum(int length){
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sf = new StringBuffer();
		Random r = new Random();
		for(int i=0;i<length;i++){
			int index = r.nextInt(str.length());
			char c = str.charAt(index);
			sf.append(c);
		}
		return sf.toString();
	}
	
	
	
	
}



