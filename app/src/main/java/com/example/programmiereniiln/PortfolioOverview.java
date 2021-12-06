package com.example.programmiereniiln;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PortfolioOverview extends AppCompatActivity{
    int money;
    String risk;
    String marketCap;
    int year;
    ListView listView;
    List<String> stockList = new ArrayList<>();
    TextView outComeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_overview);
        Intent i = getIntent();

        money = Integer.parseInt(i.getStringExtra("moneyAmount")) ;
        risk = i.getStringExtra("riskButton");
        year = Integer.parseInt(i.getStringExtra("year"));

        marketCap = riskToMarketCap(risk);

        listView = findViewById(R.id.stock_list);
        outComeText = findViewById(R.id.outcome_list);

        final PortfolioMap map = new PortfolioMap(money, year, marketCap);
        map.start();
        try {
            map.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stockList = map.stockListPortfolio;
        outComeText.setText(new DecimalFormat("Estimated outcome: ###.##$").format(map.potentialOutcome));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stockList);
        listView.setAdapter(arrayAdapter);
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