package com.Training.BackEnd.dao;

import org.springframework.data.annotation.Id;


public class BundleDao {

    @Id
    private int id ;
    private Double size ;
    private int period ;
    private Double price  ;
    private String activateDate;
    private String endDate ;

    public int getId() {
        return id;
    }

    public Double getSize() {
        return size;
    }

    public Double getPrice() {
        return price;
    }

    public String getActivateDate() {
        return activateDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setActivateDate(String activateDate) {
        this.activateDate = activateDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}