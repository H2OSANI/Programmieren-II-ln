package com.example.programmiereniiln;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class ParameterInput extends AppCompatActivity {
    Button buttonGenerate;
    EditText parameterMoney;
    SeekBar seekBarMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_input);

        parameterMoney  = (EditText) findViewById(R.id.ParameterMoneyField);
        buttonGenerate  = (Button)findViewById(R.id.parameter_button_generate);
        seekBarMoney    = (SeekBar) findViewById(R.id.ParameterMoneyBar);

        buttonGenerate.setOnClickListener(v -> launchPortfolioOverview());
        onCreateSeekBarListener();
        onCreateButtonListener();

    }
    public void launchPortfolioOverview(){
            Intent i = new Intent(this, PortfolioOverview.class);
            startActivity(i);

    }
    private void onCreateButtonListener(){
        buttonGenerate.setEnabled(false);


        parameterMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0 || Integer.parseInt(s.toString()) < 100){
                    buttonGenerate.setEnabled(false);
                } else {
                    buttonGenerate.setEnabled(true);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
    private void onCreateSeekBarListener(){
        seekBarMoney.incrementProgressBy(100);
        seekBarMoney.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //TODO: WTF?
                progress = progress /100;
                progress = progress *100;
                parameterMoney.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }

}