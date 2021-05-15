package service;

import java.util.List;

import javax.servlet.ServletContext;

import util.DeleteFileUtil;

import bean.Search;

import dao.ProductDAO;
import dao.ProductDAOImpl;

import entity.Course;
import entity.Lore;
import entity.Product;

public class ProductService {
	ProductDAO dao = new ProductDAOImpl();
	
	public List<Course> findCourse() throws Exception{
		return dao.findCourse();
	}
	
	public List<Lore> findLore(int courseId) throws Exception{
		return dao.findLore(courseId);
	}
	
	public void save(Product p) throws Exception{
		dao.save(p);
	}
	
	public List<Product> findAll(Search search) throws Exception{
		return dao.findAll(search);
	}
	
	public int getTotalPages(Search search) throws Exception{
		return dao.getTotalPages(search);
	}
	
	public Product findById(int id) throws Exception{
		return dao.findById(id);
	}
	
	
	
	public void delProduct(int id,ServletContext sc) throws Exception{
		//ɾ���������ϵ��ļ�
		Product p = dao.findById(id);
		String image = "upload\\"+p.getImage();
		String video = "upload\\"+p.getVideo();
		//ͼƬ
		DeleteFileUtil.delFile(sc.getRealPath(image));
		//��Ƶ
		DeleteFileUtil.delFile(sc.getRealPath(video));
		//ɾ����������
		dao.delProduct(id);
	}
	
	
	
}






