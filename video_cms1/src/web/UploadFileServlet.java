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
		//����������Ϊ�������ṩ��ȱʡ����
		FileItemFactory factory = new DiskFileItemFactory();
		//����������
		ServletFileUpload sfu = 
			new ServletFileUpload(factory);
		try {
			//�����������request.getInputStream()��ȡ�����ݣ�
			//�������������װ��һ����FileItem����һ���ı����
			//Ӧ��һ��FileItem����
			List<FileItem> list = 
				sfu.parseRequest(request);
			for(int i=0;i<list.size();i++){
				FileItem item = list.get(i);
				//�жϱ�������
				if(item.isFormField()){
					//��ͨ����
					String value = item.getString("utf-8");
					System.out.println("value="+value);
				}else{
					//�ļ�����
					//��ȡupload�ļ��е������ַ
					ServletContext sc = getServletContext();
					String path = sc.getRealPath("upload");
					//��ȡԴ�ļ����� aaa.jpg
					String name = item.getName();
					//jpg  
					String fix = name.substring(name.lastIndexOf(".")+1);
					//��ʱ�������
					String newName = System.currentTimeMillis()+"";
					//D��....\\upload\\1233243.jpg
					File file = new File(path+"\\"+newName+"."+fix);
					item.write(file);
					//��������
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
