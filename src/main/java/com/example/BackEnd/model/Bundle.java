package com.example.BackEnd.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Bundle {

    @Id
    private int id ;
    private Double size ;
    private Double price  ;
    private String activate_date ;
    private String end_date ;


    public int getId() {
        return id;
    }

    public Double getSize() {
        return size;
    }

    public Double getPrice() {
        return price;
    }

    public String getactivate_date() {
        return activate_date;
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
        this.activate_date = activate_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }


}