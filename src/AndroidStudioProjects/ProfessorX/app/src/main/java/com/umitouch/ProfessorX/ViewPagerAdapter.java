package com.umitouch.ProfessorX;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter
{
    Fragment Fragment0;
    Fragment activity;
    String[] images;
    LayoutInflater inflater;

    public ViewPagerAdapter(Fragment activity , String[] images)
    {
        this.activity = activity;
        this.images = images;
    }
    /*public ViewPagerAdapter(Fragment Fragment0, String[] images)
    {
        this.Fragment0 = Fragment0;
        this.images = images;
    }*/
    @Override
    public int getCount()
    {
        return images.length;//Integer.MAX_VALUE;
    }
    public int getStartPageIndex()
    {
        int index = getCount() / 2;
        int remainder = index % images.length;
        index = index - remainder;
        return index;
    }
    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        inflater = (LayoutInflater)activity.getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item,container,false);
        ImageView image;
        image = (ImageView)itemView.findViewById(R.id.imageView);
        DisplayMetrics dis = new DisplayMetrics();
        activity.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dis);
        int height = dis.heightPixels;
        int width = dis.widthPixels;
        image.setMinimumHeight(height);
        image.setMinimumWidth(width);
        Picasso.with(activity.getActivity().getApplicationContext()).load(images[position]).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(image);
        container.addView(itemView);
        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    private int getBannerIndexOfPosition(int position)
    {
        return position % images.length;
    }

}
