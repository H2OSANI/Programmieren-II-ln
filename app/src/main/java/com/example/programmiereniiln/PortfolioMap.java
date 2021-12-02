package com.example.programmiereniiln;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PortfolioMap {

    //Stock Screener URL Mapping
    private final String megaCap = "https://finviz.com/screener.ashx?v=111&f=cap_mega,fa_pe_u20&r=";
    private final String largeCap = "https://finviz.com/screener.ashx?v=111&f=cap_large,fa_pe_u20&r=";
    private final String midCap = "https://finviz.com/screener.ashx?v=111&f=cap_mid,fa_pe_u20&r=";
    private final String smallCap = "https://finviz.com/screener.ashx?v=111&f=cap_small,fa_pe_u20&r=";

    //Variable Declaration
    List<String>            stockList = new ArrayList<>();
    List<String>            stockListPortfolio = new ArrayList<>();
    String                  url = null;
    String                  urlChanged;
    String                  stockCountManipulate;
    Element                 stockCountSelector;
    Document                documentStockCount;
    Document                documentStock;

    int                     stockCount = 0;
    int                     counter = 1;

    final private int       investmentAmount;
    final private int       investmentYear;
    final private String    marketCap;

    public double           potentialOutcome= 0;
    private double          interest = 0;

    private Random          r = new Random();

    PortfolioMap(int _investmentAmount, int _investmentYear, String _marketCap){
        this.investmentAmount   = _investmentAmount;
        this.investmentYear     = _investmentYear;
        this.marketCap          = _marketCap;
    }
    //Sets Parameters for Stock Portfolio Calculation
    private void setStockListParameter(){
        switch(this.marketCap){
            case "megaCap":
                this.url = this.megaCap;
                this.interest = 5;
                break;
            case "largeCap":
                this.url = this.largeCap;
                this.interest = 7;
                break;
            case "midCap":
                this.url = this.midCap;
                this.interest = 10;
                break;
            case "smallCap":
                this.url = this.smallCap;
                this.interest = 15;
                break;
        }
    }
    //Sets Potential Outcome After Years
    private void setPotentialOutcome(){
        this.potentialOutcome = this.investmentAmount * Math.pow(1+this.interest/100, this.investmentYear);
    }
    //Picks Random Stocks from List
    private void setStockPick() {
        for (int i = 0; i < 15; i++) {
            int index = r.nextInt(this.stockList.size());
            this.stockListPortfolio.add(this.stockList.get(index));
        }
    }
    //Generates Output
    public void generateOutput(){
        setStockListParameter();
        setPotentialOutcome();
        scrapeFromFinviz();
        setStockPick();
    }
    //Try connection to finviz.com
    //Scrape Stocks filtered P/E under 20 and Market Cap
    //Parameter Market Cap: Mega, Large, Mid, Small
    private void scrapeFromFinviz() {
        try{
            documentStockCount = Jsoup.connect(url).get();
            stockCountSelector = documentStockCount.select("td .count-text").first();
            stockCountManipulate = stockCountSelector.text();
            stockCountManipulate= stockCountManipulate.substring(stockCountManipulate.indexOf(":") + 2, stockCountManipulate.indexOf("#") - 1);
            stockCount = Integer.parseInt(stockCountManipulate);

            while (counter <= stockCount) {
                urlChanged = url + counter;
                documentStock = Jsoup.connect(urlChanged).get();
                for (Element row : documentStock.select("table tr.table-dark-row-cp")) {
                    final String company = row.select("td:nth-of-type(3)").text();
                    stockList.add(company);

                }
                for (Element row : documentStock.select("table tr.table-light-row-cp")) {
                    final String company = row.select("td:nth-of-type(3)").text();
                    stockList.add(company);
                }
                counter += 20;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}