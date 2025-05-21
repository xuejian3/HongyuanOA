package com.mario.service.bill;

import com.mario.dao.BaseDao;
import com.mario.dao.bill.BillDao;
import com.mario.dao.bill.BillDaoImpl;
import com.mario.pojo.Bill;
import com.mario.pojo.Style;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService{

    //业务层都会调用dao层，所以我们要引入Dao层；
    private BillDao billDao;
    public BillServiceImpl(){
        billDao = new BillDaoImpl();
    }

    //增加订单表
    public boolean add(Bill bill) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();//获得连接
            connection.setAutoCommit(false);//开启JDBC事务管理
            int updateRows = billDao.add(connection,bill);
            connection.commit();
            if(updateRows > 0){
                flag = true;
                System.out.println("add success!");
            }else{
                System.out.println("add failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();//失败就回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            //在service层进行connection连接的关闭
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    public List<Bill> getBillList(Bill bill) {
        List<Bill> billList = new ArrayList<Bill>();
        Connection connection=null;
        System.out.println("query productName ---- > " + bill.getProductName());
        System.out.println("query providerId ---- > " + bill.getProviderId());
        System.out.println("query isPayment ---- > " + bill.getIsPayment());
        try {
            connection=BaseDao.getConnection();
            billList=billDao.getBillList(connection,bill);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return billList;
    }
    public List<Style> getStyleList(Style style,String s,String a) {
        List<Style> styleList = new ArrayList<Style>();
        Connection connection=null;
        try {
            connection=BaseDao.getConnection();
            styleList=billDao.getStyleList(connection,style,s,a);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return styleList;
    }


    public boolean updateComment(String style, String comment) {
        Connection connection=null;
        int result=0;
        try {
            connection=BaseDao.getConnection();
            result=billDao.updateComment(connection,style, comment);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return result > 0;
    }
    public boolean deleteBillById(String delId) {
        boolean flag=false;
        int delNum=0;
        Connection connection=null;
        try {
            connection=BaseDao.getConnection();
            delNum=billDao.deleteBillById(connection,delId);
            if(delNum>0){
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    public Bill getBillById(String id) {
        Bill bill = new Bill();
        Connection connection=null;
        try {
            connection=BaseDao.getConnection();
            bill = billDao.getBillById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return bill;
    }
    public Style getStyleById(String id) {
        Style style = new Style();
        Connection connection=null;
        try {
            connection=BaseDao.getConnection();
            style = billDao.getStyleById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return style;
    }
    public boolean modify(Bill bill) {
        Boolean flag=false;
        int modifyNum=0;
        Connection connection=null;
        try {
            connection=BaseDao.getConnection();
            modifyNum=billDao.modify(connection,bill);
            if(modifyNum>0){
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }
    public boolean modifyStyle(Style style) {
        Boolean flag=false;
        int modifyNum=0;
        Connection connection=null;
        try {
            connection=BaseDao.getConnection();
            modifyNum=billDao.modifyStyle(connection,style);
            if(modifyNum>0){
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }
}
