package com.example.programmiereniiln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


public class MainActivity extends AppCompatActivity {

    //Initialize variable
    SliderView  sliderView;
    //Images from drawable die geladen werden
    int[]       images = {R.drawable.one,R.drawable.two,R.drawable.three};
    //Class sliderAdp - adapter for sliding images
    SliderAdp   sliderAdp;

    private Button buttonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign variable
        sliderView = findViewById(R.id.slider_view);
        //Initialize adapter
        sliderAdp = new SliderAdp(images);
        //set adapter
        sliderView.setSliderAdapter(sliderAdp);
        //set indicator animation
        sliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
        //set transformation animation
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        //start auto cycle
        sliderView.startAutoCycle();

        buttonStart = findViewById(R.id.button_start);
        buttonStart.setOnClickListener(v -> launchParameterInput());
    }
    public void launchParameterInput(){
        Intent i = new Intent(this, ParameterInput.class);
        startActivity(i);
    }
}