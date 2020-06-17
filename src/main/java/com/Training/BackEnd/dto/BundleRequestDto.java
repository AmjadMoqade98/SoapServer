package com.Training.BackEnd.dto;

import lombok.Data;

@Data
public class BundleRequestDto {
    private Double size;
    private Double price;
    private int period;

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
