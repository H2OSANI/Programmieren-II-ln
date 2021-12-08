package com.example.programmiereniiln;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PortfolioOverview extends AppCompatActivity{
    int                     money, year;
    String                  risk, marketCap;
    ListView                listView;
    List<String>            stockList = new ArrayList<>();
    ArrayAdapter<String>    arrayAdapter;
    TextView                outComeText, resultPool;
    public PortfolioMap     map = new PortfolioMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_overview);
        Intent i = getIntent();

        money           = Integer.parseInt(i.getStringExtra("moneyAmount")) ;
        risk            = i.getStringExtra("riskButton");
        year            = Integer.parseInt(i.getStringExtra("year"));
        marketCap       = riskToMarketCap(risk);

        map.setAttributes(money, year, marketCap);

        listView        = findViewById(R.id.stock_list);
        outComeText     = findViewById(R.id.outcome_list);
        resultPool      = findViewById(R.id.result_pool);

        map.start();
        try {
            map.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stockList       = map.stockListPortfolio;
        arrayAdapter    = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stockList);
        listView.setAdapter(arrayAdapter);

        outComeText.setText(new DecimalFormat("Estimated outcome: ###.##$").format(map.potentialOutcome));
        resultPool.setText("Result: 15 of " + map.stockList.size() + " possible");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.generate_new_button) {
            map.setStockPickNew();
            stockList = map.stockListPortfolio;
            arrayAdapter.notifyDataSetChanged();
            Toast.makeText(PortfolioOverview.this, "New Portfolio generated!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
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