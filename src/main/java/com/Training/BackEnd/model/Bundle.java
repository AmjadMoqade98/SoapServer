package com.Training.BackEnd.model;

import org.springframework.data.annotation.Id;

public class Bundle {

    @Id
    private int id ;
    private Double size ;
    private Double price  ;
    private String activateDate;
    private String endDate ;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id ;
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

    public void setSize(Double size) {
        this.size = size;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setActivateDate(String activate_date) {
        this.activateDate = activate_date;
    }

    public void setEndDate(String end_date) {
        this.endDate = end_date;
    }
}