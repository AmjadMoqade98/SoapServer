package com.Training.BackEnd.dto;

public class BundleResponseDto {
    private Double size ;
    private Double price  ;
    private String activateDate;
    private String endDate ;

    public Double getSize() {
        return size;
    }

    public Double getPrice() {
        return price;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getActivateDate() {
        return activateDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setActivateDate(String activateDate) {
        this.activateDate = activateDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
