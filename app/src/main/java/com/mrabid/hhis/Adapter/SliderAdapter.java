package com.mrabid.hhis.Adapter;

/**
 * Created by Mr Abid on 9/19/2017.
 */
        import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrabid.hhis.R;

public class SliderAdapter extends PagerAdapter {
    private int[] image_resources = {R.drawable.logonew, R.drawable.history,R.drawable.grafik,R.drawable.news};
    private String[] describe =
            {"    All you need for your health and your disease will contain here safely and none won't know except You and your Doctor"
                    ,"   Login and track what diseaseas ever you had with completly note"
                    ,"    Now you can see your helth body into graph for 1 until 12 months ago !"
                    ,"    Read and understand the News of health information in the world with Health of Article"};
    private String[] titles = {"Welcome to HHIS","History","Your Healthy Graph","Health of Article"};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public SliderAdapter(Context ctx){
        this.ctx=ctx;
    }

    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView imageView = (ImageView) item_view.findViewById(R.id.imgV_slider);
        TextView desc = (TextView)item_view.findViewById(R.id.txt_slider);
        TextView title = (TextView) item_view.findViewById(R.id.txt_titleSlider);
        imageView.setImageResource(image_resources[position]);
        desc.setText(describe[position]);
        title.setText(titles[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
