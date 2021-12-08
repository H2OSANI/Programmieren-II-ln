package com.example.programmiereniiln;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdp extends SliderViewAdapter<SliderAdp.Holder> {
    //Initialize variable
    int[] images;

    //Constructor
    public SliderAdp(int[] images){
        this.images = images;
    }

    @Override
    public SliderAdp.Holder onCreateViewHolder(ViewGroup parent) {

        //Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_to_slide,parent, false);

        //return the view
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdp.Holder viewHolder, int position) {
        //Set image on the image view
        viewHolder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getCount() {
        //return images length
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
        //Initialize variable
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            //Assign variable
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
