package com.example.programmiereniiln;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PortfolioOverview extends AppCompatActivity {
    //final private PortfolioMap map = new PortfolioMap(10000, 20, "smallCap");
    int money;
    String risk;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_overview);
        Intent i = getIntent();

        money = Integer.parseInt(i.getStringExtra("moneyAmount")) ;
        risk = i.getStringExtra("riskButton");
        year = Integer.parseInt(i.getStringExtra("year"));


        //new Thread(() -> map.generateOutput()).start();

    }
}