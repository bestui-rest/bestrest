package com.zyd.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zyd.bean.Course;
import com.zyd.bean.CourseDirection;

@TestSca
public interface IndexDao {
List<CourseDirection> findAll();
CourseDirection findById();
Integer findTotalPage(Map map);
}
