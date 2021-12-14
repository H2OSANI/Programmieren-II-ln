package com.example.programmiereniiln;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PortfolioMap extends Thread{

    //Stock Screener URL Mapping
    final String            megaCap = "https://finviz.com/screener.ashx?v=111&f=cap_mega,fa_pe_u30&r=";
    final String            largeCap = "https://finviz.com/screener.ashx?v=111&f=cap_large,fa_pe_u20&r=";
    final String            midCap = "https://finviz.com/screener.ashx?v=111&f=cap_mid,fa_pe_u20&r=";
    final String            smallCap = "https://finviz.com/screener.ashx?v=111&f=cap_small,fa_pe_u20&r=";

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

    private int             investmentAmount;
    private int             investmentYear;
    private String          marketCap;

    public double           potentialOutcome= 0;
    private double          interest = 0;

    private final Random    r = new Random();
    //Init Attributes for Output generation
    public void setAttributes(int _investmentAmount, int _investmentYear, String _marketCap){
        this.investmentAmount   = _investmentAmount;
        this.investmentYear     = _investmentYear;
        this.marketCap          = _marketCap;
    }
    //Generate Output in Thread
    @Override
    public void run() {
        generateOutput();
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
        if (stockList.size() > 15){
            for (int i = 0; i < 15; i++) {
                int index = r.nextInt(this.stockList.size());
                String s = this.stockList.get(index);
                if (!stockListPortfolio.contains(s)){
                    this.stockListPortfolio.add(s);
                }
                else
                {
                    i--;
                }
                if (stockListPortfolio.size() == 15){
                    break;
                }
            }
        }
        else{
            stockListPortfolio.addAll(stockList);
        }

    }
    //Generates new Output
    public void setStockPickNew(){
        this.stockListPortfolio.clear();
        setStockPick();
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
            //Connect to Finviz.com
            documentStockCount = Jsoup.connect(url).timeout(5000).get();
            //Select total stock count in Screener
            //Format stockCount
            stockCountSelector = documentStockCount.select("td .count-text").first();
            stockCountManipulate = stockCountSelector.text();
            stockCountManipulate= stockCountManipulate.substring(stockCountManipulate.indexOf(":") + 2, stockCountManipulate.indexOf("#") - 1);
            stockCount = Integer.parseInt(stockCountManipulate);
            //Loop through list
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
