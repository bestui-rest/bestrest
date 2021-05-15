package com.zyd.dao;

import java.util.List;

import com.zyd.bean.Items;

@TestSca
public interface ItemDao {
void insert(Items items);
List<Integer> findAllCourseIDByC_Id(int id);
List<Items> findByUserId(Integer userid);
void deleteItemById(Integer id);
}
