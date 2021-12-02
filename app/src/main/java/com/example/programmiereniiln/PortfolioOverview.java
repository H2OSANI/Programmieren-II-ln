package com.example.programmiereniiln;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PortfolioOverview extends AppCompatActivity {
    int money;
    String risk;
    String marketCap;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_overview);
        Intent i = getIntent();

        money = Integer.parseInt(i.getStringExtra("moneyAmount")) ;
        risk = i.getStringExtra("riskButton");
        year = Integer.parseInt(i.getStringExtra("year"));

        marketCap = riskToMarketCap(risk);

        final PortfolioMap map = new PortfolioMap(money, year, marketCap);

        new Thread(() -> map.generateOutput()).start();

    }
    private String riskToMarketCap(String _radioButtonSelected){
        String sReturn ="";
        switch(_radioButtonSelected){
            case "Low Risk":
                sReturn = "megaCap";
                break;
            case "Medium Risk":
                sReturn = "largeCap";
                break;
            case "High Risk":
                sReturn = "midCap";
                break;
            case "Critical Risk":
                sReturn = "smallCap";
                break;
        }
        return sReturn;
    }
}