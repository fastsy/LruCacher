package com.hyts.lrucacher.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hyts.lrucacher.LruCacher;
import com.hyts.lrucacher.R;

/**
 * Created by sunyan on 17/5/4.
 */

public class ImageAdapter extends BaseAdapter {
    private Context c;
    private String[] urls;
    private LruCacher lrucacher;

    public ImageAdapter(Context context, String[] imgUrls){
        c = context;
        urls = imgUrls;
        lrucacher = LruCacher.getInstance();
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public Object getItem(int position) {
        return urls[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String url = (String) getItem(position);
        View view;
        if (convertView == null){
            view = View.inflate(c, R.layout.item_lv,null);
        }else{
            view = convertView;
        }
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        iv.setTag(url);
        Bitmap bitmap = lrucacher.getBitmapFromMemory(url);
        if (bitmap != null){
            iv.setImageBitmap(bitmap);
        }else{
            iv.setImageResource(R.mipmap.ic_launcher);
        }
        return view;
    }
}
