package com.mario.service.order;

import com.mario.pojo.Order;

import java.util.List;

public interface OrderOriService {

    Order getByPoNumber(String poNumber);

    List<Order> getAll();

    public List<Order> getOrdersByStyle(String poNumber) ;


    boolean insert(Order orderOri);

    boolean update(Order orderOri);

    boolean deleteByPoNumber(String poNumber);
}
