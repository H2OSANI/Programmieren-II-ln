package com.example.programmiereniiln;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button)findViewById(R.id.button_start);
        buttonStart.setOnClickListener(v -> launchParameterInput());
        new Thread(() -> getUrl()).start();
    }

    public void launchParameterInput(){
        Intent i = new Intent(this, ParameterInput.class);
        startActivity(i);
    }

    public void getUrl() {
        final String url ="https://finviz.com/screener.ashx?v=111&f=cap_mega,fa_pe_u20";

        try {
           final Document document = Jsoup.connect(url).get();
           for (Element row : document.select("table tr.table-dark-row-cp")){
               if(row.select("td:nth-of-type(3)").text().equals("")){
                   continue;
               }
               else{
                   final String company = row.select("td:nth-of-type(3)").text();
                   Log.d("finviz", company);
               }
           }
           for (Element row : document.select("table tr.table-light-row-cp")){
               if(row.select("td:nth-of-type(3)").text().equals("")){
                   continue;
               }
               else{
                   final String company = row.select("td:nth-of-type(3)").text();
                   Log.d("finviz", company);
               }
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        }
}