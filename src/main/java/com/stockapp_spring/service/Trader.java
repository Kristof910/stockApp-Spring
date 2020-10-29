package com.stockapp_spring.service;

import com.stockapp_spring.service.StockAPIService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Business logic for stock trading
 **/

@Component
public class Trader {

    @Autowired
    private StockAPIService stockService;

    private String content;

    /** Checks the price of a stock, and buys it if the price is not greater than the bid amount.
     * 	@return whether any stock was bought */
    public boolean buy(String symbol, double bid) throws IOException, JSONException {
        double price = stockService.getPrice(symbol);
        boolean result;
        if (price <= bid) {
            result = true;
            stockService.buy(symbol);
            content = "Purchased " + symbol + " stock at $" + bid + ", since its higher that the current price ($" + price + ")";
        }
        else {
            content = "Bid for " + symbol + " was $" + bid + " but the stock price is $" + price + ", no purchase was made.";
            result = false;
        }
        return result;
    }

    public String getContent() {
        return content;
    }
}