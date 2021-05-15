package com.zyd.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zyd.bean.Course;
import com.zyd.bean.CourseContent;
import com.zyd.bean.CourseDirection;
import com.zyd.dao.IndexDao;
import com.zyd.service.MainService;

public class TestCase {
	ApplicationContext ap=new ClassPathXmlApplicationContext("spring00.xml");
	MainService dao=ap.getBean("mainService",MainService.class);
@Test
public void test02(){
	
	
	List<CourseDirection> list=dao.findAll();
  for(CourseDirection li:list){
	  System.out.println(li.getPicture_url());
  }
}
@Test
public void test03(){
	List<Course> list=dao.findCourse(1);
	 for(Course li:list){
		  System.out.println(li.getPicture());
	  }
}
@Test
public void test04(){
	List<Course> list=dao.findCourse(3);
	 for(Course li:list){
		  System.out.println(li.getPicture());
	  }
}
@Test
public void test05(){
	List<Course> list=dao.findAllAll(5,1,2);
	 for(Course li:list){
		  System.out.println(li.getPicture());
	  }
	 
}
@Test
public void test06(){
	Integer id=dao.findTotalPageByCourseDirId(0,0);
	 
	 
}
}
