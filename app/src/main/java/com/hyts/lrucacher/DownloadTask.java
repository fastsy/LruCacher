package com.hyts.lrucacher;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;

/**
 * 异步任务下载
 * Created by sunyan on 17/5/4.
 */

public class DownloadTask extends AsyncTask<String,Integer,Bitmap> {

    private ImageView iv;

    public DownloadTask(ImageView iv){
        this.iv = iv;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        try {
            bitmap = HttpHelper.loadBitmapFromNet(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        iv.setImageBitmap(bitmap);
    }
}
