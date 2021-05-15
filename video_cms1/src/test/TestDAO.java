package test;

import java.util.List;

import org.junit.Test;

import dao.AdminDAO;
import dao.AdminDAOImpl;
import entity.Admin;
import entity.Role;

public class TestDAO {
	@Test
	public void test1() throws Exception{
		AdminDAO dao = new AdminDAOImpl();
		List<Admin> list = dao.findAll(1,2);
		for(Admin a:list){
			System.out.print(a.getUsername()+" ");
			List<Role> rs = a.getRoles();
			for(Role r:rs){
				System.out.print(r.getName()+"/");
			}
			System.out.println();
		}
	}
}





