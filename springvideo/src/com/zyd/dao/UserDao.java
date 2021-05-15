package com.zyd.dao;

import com.zyd.bean.User;

@TestSca
public interface UserDao {
User findByEmail(String email);
void insert(User user);
User findByemAndpwd(User user);
}
