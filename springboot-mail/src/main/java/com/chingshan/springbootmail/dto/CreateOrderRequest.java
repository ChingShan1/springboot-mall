package com.chingshan.springbootmail.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateOrderRequest {

    @NotEmpty
    private List<BuyItem> buyitemList;

    public List<BuyItem> getBuyitemList() {
        return buyitemList;
    }

    public void setBuyitemList(List<BuyItem> buyitemList) {
        this.buyitemList = buyitemList;
    }
}
