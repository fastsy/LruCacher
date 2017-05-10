package com.hyts.lrucacher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sunyan on 17/5/4.
 */

public class HttpHelper {

    public static Bitmap loadBitmapFromNet(String path)  {
        URL url;
        HttpURLConnection conn = null;
        Bitmap bitmap = null;
        try {
            url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(4000);
            InputStream in = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (conn != null){
                conn.disconnect();
            }
        }

        return bitmap;
    }
}
