package com.mario.service.order;

import com.mario.dao.BaseDao;
import com.mario.dao.bill.BillDaoImpl;
import com.mario.dao.order.OrderDao;
import com.mario.dao.order.OrderDaoImpl;
import com.mario.pojo.Bill;
import com.mario.pojo.Order;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderOriServiceImpl implements OrderOriService {

    private OrderDao orderOriDao;
    public OrderOriServiceImpl(){
        orderOriDao = new OrderDaoImpl();
    }


    @Override
    public Order getByPoNumber(String poNumber) {
        return orderOriDao.findByPoNumber(poNumber);
    }

    @Override
    public List<Order> getAll() {
        return orderOriDao.findAll();
    }

    @Override
    public List<Order> getOrdersByStyle(String style) {
        Boolean flag=false;
        List result = new ArrayList<Bill>();
        int modifyNum=0;
        Connection connection=null;
        try {
            connection= BaseDao.getConnection();
            result=orderOriDao.getOrderByStyle(connection,style);
            if(modifyNum>0){
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return result;
    }

    @Override
    public boolean insert(Order orderOri) {
        return orderOriDao.insert(orderOri) > 0;
    }

    @Override
    public boolean update(Order orderOri) {
        return orderOriDao.update(orderOri) > 0;
    }

    @Override
    public boolean deleteByPoNumber(String poNumber) {
        return orderOriDao.delete(poNumber) > 0;
    }
}
