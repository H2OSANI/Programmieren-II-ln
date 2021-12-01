package com.example.programmiereniiln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;




public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button)findViewById(R.id.button_start);
        buttonStart.setOnClickListener(v -> launchParameterInput());
    }

    public void launchParameterInput(){
        Intent i = new Intent(this, ParameterInput.class);
        startActivity(i);
    }
}