package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.JosnBody;
import bean.Search;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import entity.Admin;
import entity.Course;
import entity.Lore;
import entity.Product;
import entity.Role;
import entity.User;

import service.AdminService;
import service.ProductService;
import service.UserService;
import util.CookieUtil;

public class ActionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),
				uri.lastIndexOf("."));
		UserService us = new UserService();
		AdminService as = new AdminService();
		ProductService ps = new ProductService();
		if("/videos".equals(action)){
			//ת����videos.jspҳ��
			request.getRequestDispatcher("/WEB-INF/videos.jsp").
			forward(request, response);
		}else if("/user-admin".equals(action)){
			request.getRequestDispatcher("/WEB-INF/user-admin.jsp").
			forward(request, response);
		}else if("/user-vip".equals(action)){
			request.getRequestDispatcher("/WEB-INF/user-vip.jsp").
			forward(request, response);
		}else if("/role-list".equals(action)){
			request.getRequestDispatcher("/WEB-INF/role-list.jsp").
			forward(request, response);
		}else if("/videos-list".equals(action)){
			request.getRequestDispatcher("/WEB-INF/videos-list.jsp").
			forward(request, response);
		}else if("/videos-upload".equals(action)){
			request.getRequestDispatcher("/WEB-INF/videos-upload.jsp").
			forward(request, response);
		}else if("/userList".equals(action)){
			try {
				int page = Integer.parseInt(
						request.getParameter("page"));
				int pageSize = 2;
				List<User> list = us.findAll(page,pageSize);
				//����service ��ȡ��ҳ��
				int totalPages = us.getTotalPages(pageSize);
				Map<String,Object> map = 
					new HashMap<String, Object>();
				map.put("totalPages", totalPages);
				map.put("list", list);
				String data = 
					JSONObject.fromObject(map).toString();
				out.print(data);
				//�����ajax����������Ҫ��listת���ַ�������
				//�����������������󣬽�list�󶨵�request��
				//��ת����listҳ��
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/adminList".equals(action)){
			try {
				int page = Integer.parseInt(
						request.getParameter("page"));
				int pageSize = 2;
				List<Admin> list = as.findAll(page,pageSize);
				int totalPages = as.getTotalPages(pageSize);
				Map<String,Object> map = 
					new HashMap<String, Object>();
				map.put("list",list);
				map.put("totalPages", totalPages);
				String data = JSONObject.fromObject(map).toString();
				out.print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/roleList".equals(action)){
			try {
				List<Role> list = as.findRoles();
				String data = 
					JSONArray.fromObject(list).toString();
				out.print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/addAdmin".equals(action)){
			String username = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String[] roleIds = 
				request.getParameterValues("roleAdd_name");
			Admin admin = new Admin(username,pwd,name);
			try {
				as.addAdmin(admin, roleIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//���
//			System.out.println(username+","+pwd+","+name);
//			for(String id :roleIds ){
//				System.out.println(id);
//			}
		}else if("/delAdmin".equals(action)){
			//int id = Integer.parseInt(
			//		request.getParameter("id"));
			String[] ids = request.getParameterValues("id");
			try {
				for(String id:ids){
					as.delAdmin(Integer.parseInt(id));
				}
//				JosnBody body = new JosnBody();
//				body.setCode("0");
//				body.setMsg("success");
//				String data = 
//					JSONObject.fromObject(body).toString();
//				out.print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if("/loadAdmin".equals(action)){
			int id = Integer.parseInt(
					request.getParameter("id"));
			try {
				List<Role> list = as.findRoles();
				Admin admin = as.findById(id);
				Map<String,Object> map = 
					new HashMap<String, Object>();
				map.put("roleList", list);
				map.put("admin",admin);
				String data = 
					JSONObject.fromObject(map).toString();
				out.print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/updateAdmin".equals(action)){
			int id = Integer.parseInt(
					request.getParameter("id"));
			String username = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String[] roleIds = 
				request.getParameterValues("roleEdit_name"); 
			Admin admin = new Admin(id,username,pwd,name);
			try {
				as.updateAdmin(admin, roleIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/loadCourse".equals(action)){
			try {
				List<Course> list = ps.findCourse();
				String data = JSONArray.fromObject(list).toString();
				out.print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/loadLore".equals(action)){
			int courseId = Integer.parseInt(
					request.getParameter("courseId"));
			try {
				List<Lore> list = ps.findLore(courseId);
				String data = JSONArray.fromObject(list).toString();
				out.print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/uploadProduct".equals(action)){
			String name = request.getParameter("name");
			int courseId = Integer.parseInt(
					request.getParameter("course_id"));
			int loreId = Integer.parseInt(
					request.getParameter("lore_id"));
			String description = 
				request.getParameter("description");
			String status =  request.getParameter("status");
			double price = Double.parseDouble(
					request.getParameter("price"));
			String image = request.getParameter("image");
			String video = request.getParameter("video");
			String creater = "scott";
			Product p = new Product(courseId,loreId,name,description,
					status,price,image,video,creater);
			try {
				ps.save(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/listProduct".equals(action)){
			int pageSize = 2;
			int page = Integer.parseInt(
					request.getParameter("page"));
			String courseName = 
				request.getParameter("courseName");
			String name = request.getParameter("name");
			String creater = request.getParameter("creater");
			String createtime = request.getParameter("createtime");
			Search search = new Search(courseName,name,creater,
					createtime,page,pageSize);
			try {
				List<Product> list = 
					ps.findAll(search);
				int totalPages = ps.getTotalPages(search);
				Map<String,Object> map = 
					new HashMap<String, Object>();
				map.put("list",list);
				map.put("totalPages",totalPages);
				String data = 
					JSONObject.fromObject(map).toString();
				out.print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/delProduct".equals(action)){
			int id = Integer.parseInt(
					request.getParameter("id"));
			try {
				ps.delProduct(id, getServletContext());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/loadOne".equals(action)){
			int id = Integer.parseInt(
					request.getParameter("id"));
			try {
				Product p = ps.findById(id);
				String data = 
					JSONObject.fromObject(p).toString();
				out.print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/loginform".equals(action)){
			request.getRequestDispatcher("/WEB-INF/login.jsp").
			forward(request, response);
		}else if("/login".equals(action)){
			//��ȡ�û��������֤��
			String userCode = request.getParameter("userCode");
			//��ȡ��ȷ����֤��
			HttpSession session = request.getSession();
			String rightCode = 
				(String)session.getAttribute("rightCode");
			if(!userCode.equalsIgnoreCase(rightCode)){
				request.setAttribute("code_msg","��֤�����");
				request.getRequestDispatcher("/WEB-INF/login.jsp").
				forward(request, response);
				return;
			}
			
			//��ȡ�����û��������Ϣ
			String username = 
				request.getParameter("username");
			String pwd = request.getParameter("pwd");
			//У��
			try {
				Admin admin = as.findByUsername(username);
				if(admin==null){
					//�˺Ŵ���
					//��ת����¼ҳ�棬��ʾ��Ӧ��Ϣ
					request.setAttribute("login_msg", "�˺Ŵ���");
					request.getRequestDispatcher("/WEB-INF/login.jsp").
					forward(request, response);
				}else{
					//�Ƚ������Ƿ���ȷ
					if(admin.getPwd().equals(pwd)){
						//���û�����Ϣ���浽session��
						session.setAttribute("admin",admin);
						//�ж��û��Ƿ�ѡ���Զ���¼
						String auto = request.getParameter("auto");
						if("0".equals(auto)){
							//���û�����Ϣ���浽cookie��
							CookieUtil.addCookie("admin",
									username,3*60, response);
						}
						//��ת��listҳ��
						response.sendRedirect("videos.do");
					}else{
						//�������
						request.setAttribute("login_msg", "�������");
						request.getRequestDispatcher("/WEB-INF/login.jsp").
						forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.close();
	}
}



