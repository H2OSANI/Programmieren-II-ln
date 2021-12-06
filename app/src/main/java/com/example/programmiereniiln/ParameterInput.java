package com.example.programmiereniiln;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class ParameterInput extends AppCompatActivity {
    Button buttonGenerate;
    EditText parameterMoney;
    SeekBar seekBarMoney;
    EditText parameterYearField;
    SeekBar seekBarYear;
    RadioGroup rg;
    RadioButton rb;
    final LoadingDialog loadingDialog = new LoadingDialog(ParameterInput.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_input);

        parameterMoney      = findViewById(R.id.ParameterMoneyField);
        buttonGenerate      = findViewById(R.id.parameter_button_generate);
        seekBarMoney        = findViewById(R.id.ParameterMoneyBar);
        parameterYearField  = findViewById(R.id.ParameterYearField);
        seekBarYear         = findViewById(R.id.ParameterYearBar);
        rg                  = findViewById(R.id.rgroup);

        buttonGenerate.setOnClickListener(v -> launchPortfolioOverview());
        buttonGenerate.setEnabled(false);
        onCreateSeekBarListener();
        onCreateMoneyListener();
        onCreateYearListener();
        onCreateYearBarListener();
    }
    public void launchPortfolioOverview(){
        int rbid = rg.getCheckedRadioButtonId();
        rb = findViewById(rbid);
        Intent i = new Intent(this, PortfolioOverview.class);
        i.putExtra("riskButton", rb.getText()); // Uebergabe von dem Radiobutton value
        i.putExtra("moneyAmount", parameterMoney.getText().toString()); // Uebergabe von dem angegebenen Geld
        i.putExtra("year", parameterYearField.getText().toString()); // Uebergabe von dem angegebenen Jahr
        loadingDialog.startLoadingDialog();
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadingDialog.dismissDialog();
    }

    private void onCreateYearBarListener(){
        seekBarYear.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                parameterYearField.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
    private void onCreateYearListener(){
        parameterYearField.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFields();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    private void onCreateMoneyListener(){

        parameterMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              checkFields();
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
    private void checkFields(){
        String s1;
        String s2;
        s1 = parameterMoney.getText().toString();
        s2 = parameterYearField.getText().toString();

        if(!s1.equals("") && !s2.equals("")){
            if(Integer.parseInt(s1) >= 100 && Integer.parseInt(s2) > 1){
                buttonGenerate.setEnabled(true);
            }
        }
        else{
            buttonGenerate.setEnabled(false);
        }
    }
}