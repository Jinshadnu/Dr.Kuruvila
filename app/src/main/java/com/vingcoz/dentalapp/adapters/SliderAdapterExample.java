package com.vingcoz.dentalapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.vingcoz.dentalapp.R;


public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;
    private int mCount;

    public SliderAdapterExample(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {


        viewHolder.itemView.setOnClickListener(v -> {
            // Toast.makeText(context, "Dr. Kuruvila" + position, Toast.LENGTH_SHORT).show();
        });


        switch (position) {
            case 0:
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load("http://drkuruvilamemorial.site/uploads/scrollimages/bg2.jpg")
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load("http://drkuruvilamemorial.site/uploads/scrollimages/bg1.jpg")
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load("http://drkuruvilamemorial.site/uploads/scrollimages/bg3.jpg")
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 3:
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load("http://drkuruvilamemorial.site/uploads/scrollimages/bg4.jpg")
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 4:
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load("http://drkuruvilamemorial.site/uploads/scrollimages/bg5.jpg")
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            default:

                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load(ContextCompat.getDrawable(context, R.drawable.home_bg1))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);

//                viewHolder.textViewDescription.setTextSize(29);
//                viewHolder.textViewDescription.setTextColor(Color.WHITE);
//                viewHolder.textViewDescription.setText("Ohhhh! look at this!");
//                viewHolder.imageGifContainer.setVisibility(View.VISIBLE);
//                Glide.with(viewHolder.itemView)
//                        .load(R.drawable.puma_offer)
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//                Glide.with(viewHolder.itemView)
//                        .asGif()
//                        .load(R.drawable.oh_look_at_this)
//                        .into(viewHolder.imageGifContainer);
                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }


}