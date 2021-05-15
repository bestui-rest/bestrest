package com.zyd.dao;

import com.zyd.bean.Cart;

@TestSca
public interface CarDao {
Cart findById(int id);
void insertcart(Cart cart);
}
