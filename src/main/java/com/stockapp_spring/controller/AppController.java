package com.stockapp_spring.controller;

import com.stockapp_spring.service.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private Trader trader;

    @PostMapping("/buy/{stock}/{price}")
    public String start(@PathVariable("stock") String symbol, @PathVariable("price") int price) {
        String content = "";
        String tradeContent = "";
        try {
            boolean purchased = trader.buy(symbol, price);
            tradeContent = trader.getContent();
            if (purchased) {
                content = "Purchased stock!";
            }
            else {
                content = "Couldn't buy the stock at that price.";
            }
        } catch (Exception e) {
            content = "There was an error while attempting to buy the stock: " + e.getMessage();
        }
        return tradeContent + "\n" + content;
    }

}
