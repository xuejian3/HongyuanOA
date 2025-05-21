package com.mario.pojo;

import java.util.Date;
public class Order {
    private String poNumber;          // PO#
    private String style;
    private String classStyle;        // Class / Style
    private Date factoryDate;         // Factory Date
    private Integer status;
    private String poPcsQuantity;     // PO Pcs Quantity
    private Double poFobCost;         // PO FOB Cost
    private Double totalFobValue;     // Total FOB Value
    private String description;
    private String department;
    private String supplierNumber;    // Supplier #
    private String supplier;
    private String forClient;
    private String nameOfClient;      // Name of Client
    private String countryOfOrigin;
    private String comp1;             // Comp 1
    private String sizes;
    private String packingRatio;
    private String noOfMixes;
    private String qtyInMixes;
    private String fold;
    private String developNumber;
    private String reference;
    private byte[] pic;
    private String colour;
    private String fabric;
    private Date updateDate;

    // Getters and Setters
    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getClassStyle() {
        return classStyle;
    }

    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }

    public Date getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(Date factoryDate) {
        this.factoryDate = factoryDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPoPcsQuantity() {
        return poPcsQuantity;
    }

    public void setPoPcsQuantity(String poPcsQuantity) {
        this.poPcsQuantity = poPcsQuantity;
    }

    public Double getPoFobCost() {
        return poFobCost;
    }

    public void setPoFobCost(Double poFobCost) {
        this.poFobCost = poFobCost;
    }

    public Double getTotalFobValue() {
        return totalFobValue;
    }

    public void setTotalFobValue(Double totalFobValue) {
        this.totalFobValue = totalFobValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getForClient() {
        return forClient;
    }

    public void setForClient(String forClient) {
        this.forClient = forClient;
    }

    public String getNameOfClient() {
        return nameOfClient;
    }

    public void setNameOfClient(String nameOfClient) {
        this.nameOfClient = nameOfClient;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getComp1() {
        return comp1;
    }

    public void setComp1(String comp1) {
        this.comp1 = comp1;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getPackingRatio() {
        return packingRatio;
    }

    public void setPackingRatio(String packingRatio) {
        this.packingRatio = packingRatio;
    }

    public String getNoOfMixes() {
        return noOfMixes;
    }

    public void setNoOfMixes(String noOfMixes) {
        this.noOfMixes = noOfMixes;
    }

    public String getQtyInMixes() {
        return qtyInMixes;
    }

    public void setQtyInMixes(String qtyInMixes) {
        this.qtyInMixes = qtyInMixes;
    }

    public String getFold() {
        return fold;
    }

    public void setFold(String fold) {
        this.fold = fold;
    }

    public String getDevelopNumber() {
        return developNumber;
    }

    public void setDevelopNumber(String developNumber) {
        this.developNumber = developNumber;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
