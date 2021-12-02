package com.example.programmiereniiln;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PortfolioOverview extends AppCompatActivity {
    final private PortfolioMap map = new PortfolioMap(10000, 20, "smallCap");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_overview);

        new Thread(() -> map.generateOutput()).start();
    }
}