package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.AdminService;
import util.CookieUtil;

import entity.Admin;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = 
				(HttpServletRequest)req;
		HttpServletResponse response = 
				(HttpServletResponse)res;
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),
				uri.lastIndexOf("."));
		if("/loginform".equals(action)||
				"/login".equals(action)){
			chain.doFilter(request, response);
			return;
		}
		//session��֤
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		if(admin==null){
			//�ж�cookie���Ƿ����admin��Ϣ
			String value = 
				CookieUtil.findCookie("admin", request);
			if(value==null){
				response.sendRedirect("loginform.do");
			}else{
				//��cookie�����ݻָ���session��
				AdminService as = new AdminService();
				try {
					session.setAttribute("admin",
							as.findByUsername(value));
				} catch (Exception e) {
					e.printStackTrace();
				}
				chain.doFilter(request, response);
			}
			//δ��¼
			//�ض��򵽵�¼ҳ��
			return;
		}
		//�������ʺ�����web���
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
