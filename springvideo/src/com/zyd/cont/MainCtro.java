package com.zyd.cont;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyd.bean.Car_Item;
import com.zyd.bean.Cart;
import com.zyd.bean.Course;
import com.zyd.bean.CourseDirection;
import com.zyd.bean.Items;
import com.zyd.bean.User;
import com.zyd.dao.IndexDao;
import com.zyd.dao.ItemDao;
import com.zyd.service.MainService;

@Controller
@RequestMapping("main")
public class MainCtro {
	@Resource 
	private MainService mainService;
	
@RequestMapping("toMain.do")
public String toMain(Model model){
	List<CourseDirection> list=mainService.findAll();
	List<Course> list1=mainService.findCourse(1);
	List<Course> list2=mainService.findCourse(3);
	model.addAttribute("list", list);
	model.addAttribute("list1",list1);
	model.addAttribute("list2", list2);
	return "jsp/index";
}
@RequestMapping("tovideo.do")
public String toVideo(@RequestParam("id")int id,Model model,HttpServletRequest request){
    Course course =mainService.findById(id);
    model.addAttribute("video", course);
    model.addAttribute("user", request.getSession().getAttribute("user"));
	return "jsp/video";
}
@RequestMapping("tocourse.do")
public String toCourse(@RequestParam("coursedir")int coursedir,@RequestParam("page")int page,
		@RequestParam("contentid")int contentid,Model model,HttpServletRequest request){
	model.addAttribute("AllCourseDir", mainService.findAll());
	if(coursedir==0){
	model.addAttribute("AllCourseCon", mainService.findAllContent());
	}else{
		model.addAttribute("AllCourseCon", mainService.findAllContentByID(coursedir));
	}
    model.addAttribute("AllCourse", mainService.findAllAll(coursedir,page,contentid));
    model.addAttribute("curcoursedir", coursedir);
    model.addAttribute("page", page);
    model.addAttribute("contentid", contentid);
    model.addAttribute("totalPage",mainService.findTotalPageByCourseDirId(coursedir,contentid));
	return "jsp/course";
	//链接、表单提交可以省略@RequestParam("coursedir") 
	//ajax请求不能省略
	//@RequestParam("coursedir")@RequestParam("page")@RequestParam("contentid")
}
@RequestMapping("/buy.do")
@ResponseBody
public int buy(int id,HttpServletRequest req){
	System.out.println("进入buy");
	User user=(User)req.getSession().getAttribute("user");
	Integer idu=user.getId();
	return mainService.buy(id,idu);
	
}
@RequestMapping("/car.do")
public String cardo(@RequestParam("userid")Integer id,Model model){
	List<Items> list=mainService.findByuserId(id);
	model.addAttribute("listitem", list);
	System.out.println(list.size());
	return "jsp/car";
}
@RequestMapping("/deletecaritem.do")
public String deletecaritem(@RequestParam("itemid")Integer itemid,HttpServletRequest req){
	mainService.deleteItemById(itemid);
	User use=(User)req.getSession().getAttribute("user");
	Integer userid=use.getId();
	return "redirect:/main/car.do?userid="+userid;
}
}
