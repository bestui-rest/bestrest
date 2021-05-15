package com.zyd.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zyd.bean.Course;
@TestSca
public interface CourseDao {
List<Course> findAll();
List<Course> findNewCou();
List<Course> findAllAll();
List<Course> findAllByDirID(Map<String,Integer> map);
Course findById(int id);
}
