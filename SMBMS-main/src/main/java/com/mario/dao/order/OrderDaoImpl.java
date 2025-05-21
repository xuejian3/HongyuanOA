package com.mario.dao.order;

import com.mario.dao.BaseDao;
import com.mario.dao.bill.BillDao;
import com.mario.pojo.Bill;
import com.mario.pojo.Order;
import com.mario.pojo.Style;
import com.mysql.cj.util.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {


    @Override
    public Order findByPoNumber(String poNumber) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public int insert(Order orderOri) {
        return 0;
    }

    @Override
    public int update(Order orderOri) {
        return 0;
    }

    @Override
    public int delete(String poNumber) {
        return 0;
    }

    @Override
    public List<Order> getOrderByStyle(Connection connection, String style) throws SQLException {
        List<Order> orderList = new ArrayList<Order>();
        PreparedStatement pstm=null;
        ResultSet rs=null;
        if(connection!=null){
            String sql="SELECT * FROM `order_ori` WHERE `Style`=?";
            Object[] params={style};
            rs = BaseDao.execute(connection, sql, params, rs, pstm);
            while(rs.next()){
                Order order = new Order();//创建一个Style对象存储查询到的属性
                order.setClassStyle(rs.getString("Class / Style"));
                order.setPoNumber(rs.getString("PO#"));
                order.setStatus(rs.getInt("status"));
                order.setStyle(rs.getString("style"));
                order.setFactoryDate(rs.getDate("Factory Date"));

                orderList.add(order);
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return orderList;
    }
}
