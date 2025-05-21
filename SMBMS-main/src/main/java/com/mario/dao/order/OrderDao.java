package com.mario.dao.order;

import com.mario.pojo.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    Order findByPoNumber(String poNumber);

    List<Order> findAll();

    int insert(Order orderOri);

    int update(Order orderOri);

    int delete(String poNumber);

    List<Order> getOrderByStyle(Connection connection,String style) throws SQLException;
}

