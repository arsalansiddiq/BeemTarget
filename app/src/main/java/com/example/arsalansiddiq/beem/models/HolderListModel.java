package com.example.arsalansiddiq.beem.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HolderListModel extends RealmObject{

    @PrimaryKey
    private int id;
    private int storeId;
    private int salesId;
    private String oDate;
    private String brand;
    private int skuCategory;
    private int SKU;
    private int saleType;
    private int noItem;
    private int price;
    private int sAmount;
    private int orderStatus;
    private int orderId;


    public HolderListModel(int storeId, int salesId, String oDate, String brand, int skuCategory, int SKU, int saleType, int noItem, int price, int sAmount) {
        this.storeId = storeId;
        this.salesId = salesId;
        this.oDate = oDate;
        this.brand = brand;
        this.skuCategory = skuCategory;
        this.SKU = SKU;
        this.saleType = saleType;
        this.noItem = noItem;
        this.price = price;
        this.sAmount = sAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HolderListModel() {
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSkuCategory() {
        return skuCategory;
    }

    public void setSkuCategory(int skuCategory) {
        this.skuCategory = skuCategory;
    }

    public int getSKU() {
        return SKU;
    }

    public void setSKU(int SKU) {
        this.SKU = SKU;
    }

    public int getSaleType() {
        return saleType;
    }

    public void setSaleType(int saleType) {
        this.saleType = saleType;
    }

    public int getNoItem() {
        return noItem;
    }

    public void setNoItem(int noItem) {
        this.noItem = noItem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getsAmount() {
        return sAmount;
    }

    public void setsAmount(int sAmount) {
        this.sAmount = sAmount;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
