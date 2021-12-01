package com.example.programmiereniiln;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PortfolioOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_overview);

        new Thread(() -> getUrl()).start();
    }
    public void getUrl() {
        final String url = "https://finviz.com/screener.ashx?v=111&f=cap_mega,fa_pe_u20";

        try {
            final Document document = Jsoup.connect(url).get();
            for (Element row : document.select("table tr.table-dark-row-cp")) {
                if (row.select("td:nth-of-type(3)").text().equals("")) {
                    continue;
                } else {
                    final String company = row.select("td:nth-of-type(3)").text();
                    Log.d("finviz", company);
                }
            }
            for (Element row : document.select("table tr.table-light-row-cp")) {
                if (row.select("td:nth-of-type(3)").text().equals("")) {
                    continue;
                } else {
                    final String company = row.select("td:nth-of-type(3)").text();
                    Log.d("finviz", company);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}