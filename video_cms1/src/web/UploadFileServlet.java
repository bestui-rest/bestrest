package web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.ResponseBody;

public class UploadFileServlet extends HttpServlet {

	public  void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//创建工厂，为解析器提供了缺省配置
		FileItemFactory factory = new DiskFileItemFactory();
		//创建解析器
		ServletFileUpload sfu = 
			new ServletFileUpload(factory);
		try {
			//解析器会调用request.getInputStream()获取流数据，
			//分析流，将其封装成一个个FileItem对象，一个文本域对
			//应着一个FileItem对象
			List<FileItem> list = 
				sfu.parseRequest(request);
			for(int i=0;i<list.size();i++){
				FileItem item = list.get(i);
				//判断表单域类型
				if(item.isFormField()){
					//普通表单域
					String value = item.getString("utf-8");
					System.out.println("value="+value);
				}else{
					//文件表单域
					//获取upload文件夹的物理地址
					ServletContext sc = getServletContext();
					String path = sc.getRealPath("upload");
					//获取源文件名称 aaa.jpg
					String name = item.getName();
					//jpg  
					String fix = name.substring(name.lastIndexOf(".")+1);
					//以时间戳命名
					String newName = System.currentTimeMillis()+"";
					//D：....\\upload\\1233243.jpg
					File file = new File(path+"\\"+newName+"."+fix);
					item.write(file);
					//返回数据
					Map<String,Object> map = 
						new HashMap<String, Object>();
					map.put("fix", fix);
					map.put("newName", newName);
					ResponseBody body = new ResponseBody();
					body.setObj(map);
					String data = 
						JSONObject.fromObject(body).toString();
					out.print(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.close();
	}

}
