package com.zyd.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyd.bean.Car_Item;
import com.zyd.bean.Cart;
import com.zyd.bean.Course;
import com.zyd.bean.CourseContent;
import com.zyd.bean.CourseDirection;
import com.zyd.bean.Items;
import com.zyd.dao.CarDao;
import com.zyd.dao.Car_It;
import com.zyd.dao.CourseContentDao;
import com.zyd.dao.CourseDao;
import com.zyd.dao.IndexDao;
import com.zyd.dao.ItemDao;

@Service
public class MainService {
@Resource
private IndexDao indexdao;
@Resource
private CourseDao courseDao;
@Resource
private CourseContentDao coursecontent;
@Resource
private CarDao carDao;
@Resource 
private ItemDao itemDao;
@Resource
private Car_It car_It;
public List<CourseDirection> findAll(){
	List<CourseDirection> list=indexdao.findAll();
	return list;
}
public List<Course> findCourse(Integer n){
	List<Course> list;
	int flag;
	if(n==1){
		list=courseDao.findAll();
		flag=5;
	}
	else{
		list=courseDao.findNewCou();
		flag=10;
	}
	if(list.size()<=flag){
		return list;
	}else{
		Random random =new Random();
		List<Course> listd=new ArrayList<Course>();
		for(int i=0;i<flag;i++){
			Course e=list.remove(random.nextInt(list.size()));
			listd.add(e);
		}
		return listd;
	}
}
public Course findById(int id){
	return courseDao.findById(id);
}

public List<CourseContent> findAllContent(){
	return coursecontent.findAll();
}
public List<CourseContent> findAllContentByID(int id){
	System.out.println(id);
	return coursecontent.findContentByDir(id);
}
private Map<String,Integer> map=new HashMap<String, Integer>();
public List<Course> findAllAll(int coursedir,int page,int contentid){
	map.put("coursedir", coursedir);
	map.put("page", (page-1)*10);
	map.put("contentid", contentid);
	System.out.println(coursedir+"\t"+page+"\t"+contentid);
	return courseDao.findAllByDirID(map);
}
public Cart findCartById(int id){
	return carDao.findById(id);
}
public void insertCart(Cart cart){
carDao.insertcart(cart);
}
public void insertcar_item(Car_Item items){
	car_It.insertCar_it(items);
}
public void insetitem(Items items){
	itemDao.insert(items);
}
public List<Integer> findItemByC_Id(int id){
	return itemDao.findAllCourseIDByC_Id(id);
}
@Transactional
public int buy(int id,int idu){
	System.out.println("进入buy");
	Cart cart=findCartById(idu);
	if(cart==null){
		cart=new Cart();
		cart.setUserid(idu);
		insertCart(cart);
	}
	List<Integer> item=findItemByC_Id(id);
	Items items;
	if(!item.contains(id)){
		Course course=findById(id);
		items=new Items();
		items.setId(null);
		items.setC_id(course.getId());
		items.setC_name(course.getName());
		items.setC_picture(course.getPicture());
		items.setC_price(course.getPrice());
		items.setAdd_time(new Timestamp(System.currentTimeMillis()));
		items.setRemove_time(new Timestamp(System.currentTimeMillis()));
		insetitem(items);
		Car_Item cari=new Car_Item();
		cari.setC_id(cart.getId());
		cari.setI_id(items.getId());
		insertcar_item(cari);
		return 1;
	}
	return 2;
}
public List<Items> findByuserId(Integer id){
	return itemDao.findByUserId(id);
}
public void delteCar_Item(Integer id){
	car_It.deleteCarItemByItemId(id);
}
public void deleteItem(Integer id){
	itemDao.deleteItemById(id);
}
@Transactional
public void deleteItemById(Integer id){
	delteCar_Item(id);
	deleteItem(id);
}
public Integer findTotalPageByCourseDirId(Integer coudirid,Integer coursecontentid){
	Map map=new HashMap();
	map.put("coudirid", coudirid);
	map.put("contid", coursecontentid);
	Integer to=indexdao.findTotalPage(map);
	if(to%5==0){
		System.out.println("coursedir"+coudirid+"\t"+coursecontentid+"\t"+(to/5));
		return  (to/5);
	}else{
		System.out.println("coursedir"+coudirid+"\t"+(to/5+1));
		return (to/5+1);
	}
}
}
