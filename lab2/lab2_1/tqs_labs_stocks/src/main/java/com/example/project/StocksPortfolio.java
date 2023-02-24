package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {

    private List<Stock> stocks = new ArrayList<>();
    ;
    private IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket){
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock){
        this.stocks.add(stock);
    }

    public Double getTotalValue(){
        double total = 0;
        for(Stock s : stocks) {
            total = total + (this.stockmarket.lookUpPrice(s.getLabel()) * s.getQuantity());
        }
        return total;
    }

}
