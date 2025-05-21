package com.mario.service.bill;

import com.mario.pojo.Bill;
import com.mario.pojo.Style;

import java.util.List;

public interface BillService {
    /**
     * 增加订单
     * @param bill
     * @return
     */
    public boolean add(Bill bill);


    /**
     * 通过条件获取订单列表-模糊查询-billList
     * @param bill
     * @return
     */
    public List<Bill> getBillList(Bill bill);

    /**
     * 通过条件获取订单列表-模糊查询-billList
     * @param style
     * @return
     */
    public List<Style> getStyleList(Style style,String s,String a);

    public boolean updateComment(String style, String comment);
    /**
     * 通过billId删除Bill
     * @param delId
     * @return
     */
    public boolean deleteBillById(String delId);


    /**
     * 通过billId获取Bill
     * @param id
     * @return
     */
    public Bill getBillById(String id);
    /**
     * 通过billId获取Bill
     * @param id
     * @return
     */
    public Style getStyleById(String id);

    /**
     * 修改订单信息
     * @param bill
     * @return
     */
    public boolean modify(Bill bill);
    /**
     * 修改款式信息
     * @param style
     * @return
     */
    public boolean modifyStyle(Style style);
}
