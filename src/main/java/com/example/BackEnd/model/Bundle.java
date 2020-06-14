package com.example.BackEnd.model;

import org.springframework.data.annotation.Id;

public class Bundle {

    @Id
    private int id ;
    private Double size ;
    private Double price  ;
    private String activateDate;
    private String end_date ;

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

    public String getactivate_date() {
        return activateDate;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setactivate_date(String activate_date) {
        this.activateDate = activate_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}