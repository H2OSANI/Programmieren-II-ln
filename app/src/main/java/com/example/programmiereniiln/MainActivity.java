package com.example.programmiereniiln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


public class MainActivity extends AppCompatActivity {

    SliderView sliderView;
    int[] images = {R.drawable.one,R.drawable.two,R.drawable.three};
    SliderAdp sliderAdp;
    private Button buttonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderView = findViewById(R.id.slider_view);

        sliderAdp = new SliderAdp(images);
        sliderView.setSliderAdapter(sliderAdp);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        buttonStart = findViewById(R.id.button_start);
        buttonStart.setOnClickListener(v -> launchParameterInput());
    }

    public void launchParameterInput(){
        Intent i = new Intent(this, ParameterInput.class);
        startActivity(i);
    }
}