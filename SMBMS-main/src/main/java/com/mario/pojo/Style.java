package com.mario.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Style {
    private String Style;   //style
    private Integer Status; //状态
    private String ClassStyle; //款号
    private String Designer; //设计师
    private String Technologist; //版型师
    private String Special; //

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private byte[] Pic; //商品描述
    private byte[] label; //商品单位
    private Integer Weight; //克重
    private Integer createdBy; //创建者
    private Date creationDate; //创建时间
    private Integer modifyBy; //更新者
    private Date modifyDate;//更新时间
    private Date SRS ;//SRS时间
    private Date PPS1 ;//PPS1更新时间
    private Date PPS2 ;//PPS2更新时间
    private Date PPS3 ;//PPS3更新时间
    private Date PPS4 ;//PPS4更新时间
    private String comment ;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    private Date shipmentSample ;//更新时间
    private Date deadline ;//更新时间

    public Date getShipReport() {
        return ShipReport;
    }

    public void setShipReport(Date shipReport) {
        ShipReport = shipReport;
    }

    private Date ShipReport ;//更新时间
    private Date LC ;//更新时间
    private String ProCode ;//品牌编码

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getClassStyle() {
        return ClassStyle;
    }

    public void setClassStyle(String classStyle) {
        ClassStyle = classStyle;
    }

    public String getDesigner() {
        return Designer;
    }

    public void setDesigner(String designer) {
        Designer = designer;
    }

    public String getTechnologist() {
        return Technologist;
    }

    public void setTechnologist(String technologist) {
        Technologist = technologist;
    }

    public String getSpecial() {
        return Special;
    }

    public void setSpecial(String special) {
        Special = special;
    }

    public String getProCode() {
        return ProCode;
    }

    public void setProCode(String proCode) {
        ProCode = proCode;
    }

    public byte[] getPic() {
        return Pic;
    }

    public void setPic(byte[] pic) {
        Pic = pic;
    }

    public byte[] getLabel() {
        return label;
    }

    public void setLabel(byte[] label) {
        this.label = label;
    }

    public Integer getWeight() {
        return Weight;
    }

    public void setWeight(Integer weight) {
        Weight = weight;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getSRS() {
        return SRS;
    }

    public void setSRS(Date SRS) {
        this.SRS = SRS;
    }

    public Date getPPS1() {
        return PPS1;
    }

    public void setPPS1(Date PPS1) {
        this.PPS1 = PPS1;
    }

    public Date getPPS2() {
        return PPS2;
    }

    public void setPPS2(Date PPS2) {
        this.PPS2 = PPS2;
    }

    public Date getPPS3() {
        return PPS3;
    }

    public void setPPS3(Date PPS3) {
        this.PPS3 = PPS3;
    }

    public Date getPPS4() {
        return PPS4;
    }

    public void setPPS4(Date PPS4) {
        this.PPS4 = PPS4;
    }

    public Date getShipmentSample() {
        return shipmentSample;
    }

    public void setShipmentSample(Date shipmentSample) {
        shipmentSample = shipmentSample;
    }

    public Date getLC() {
        return LC;
    }

    public void setLC(Date LC) {
        this.LC = LC;
    }
}

