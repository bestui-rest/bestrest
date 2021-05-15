package com.zyd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zyd.bean.CourseContent;


@TestSca
public interface CourseContentDao {
List<CourseContent> findAll();
List<CourseContent> findContentByDir(@Param("id")int id);//加上注解if中的test标签才能获取到值
}
