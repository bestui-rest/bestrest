package dao;

import java.util.List;

import bean.Search;

import entity.Course;
import entity.Lore;
import entity.Product;

public interface ProductDAO {
	List<Course> findCourse() throws Exception;
	List<Lore> findLore(int courseId) throws Exception;
	void save(Product p) throws Exception;
	List<Product> findAll(Search search) throws Exception;
	int getTotalPages(Search search) throws Exception;
	void delProduct(int id) throws Exception;
	Product findById(int id) throws Exception;
}

