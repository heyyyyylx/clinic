package com.example.sparrownotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class bizhi extends AppCompatActivity {
    private final static String TAG=bizhi.class.getSimpleName();
    private final String PATH = "https://photo.16pic.com/00/60/10/16pic_6010612_b.jpg";
    private ImageView imageView;
    private Button bt_set_wallpaper;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizhi);
        imageView = findViewById(R.id.iv_image);
        bt_set_wallpaper = findViewById(R.id.bt_set_wallpaper);
        Button bt_get_image = findViewById(R.id.bt_get_image);
        bt_get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //让异步任务执行耗时操作
                new DownloadImage().execute(PATH);
            }
        });
        bt_set_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != bitmap) {
                    try {
                        setWallpaper(bitmap);
                        Toast.makeText(bizhi.this, "设置成功", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(bizhi.this, "设置失败", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private Bitmap bitmap;

    class DownloadImage extends AsyncTask<String, Void, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //弹出进度条
            progressDialog = new ProgressDialog(bizhi.this);
            progressDialog.setMessage("正在下载...");
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(String... strings) {
            try {
                /**
                 * 第一步
                 * HttpClient是接口 不能直接实例化，需要通过HttpClient接口的子类 DefaultHttpClient来实例化
                 */
                HttpClient httpClient = new DefaultHttpClient();
                //第二步get方法请求对象
                HttpUriRequest getRequest = new HttpGet(PATH);
                /**
                 * 第三步
                 * 执行任务
                 */
                HttpResponse response = httpClient.execute(getRequest);
                int responseCode = response.getStatusLine().getStatusCode();
                Log.d(TAG, "responseCode:" + responseCode);
                if (HttpURLConnection.HTTP_OK == responseCode) {
                    /**
                     * 第五步
                     * 获取到服务区响应的字节流数据 然后转换成图片Bitmap数据
                     */
                    InputStream inputStream = response.getEntity().getContent();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 耗时执行过程中 更新进度条刻度操作
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 耗时操作执行完成，用于更新UI
         *
         * @param o
         */
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o != null) {//成功
                bitmap = (Bitmap) o;
                // 故意放慢两秒，以便检查是否成功
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                        bt_set_wallpaper.setEnabled(true);
                        progressDialog.dismiss();
                    }
                }, 2000);
            } else {//失败
                bt_set_wallpaper.setEnabled(false);
                Toast.makeText(bizhi.this, "下载失败", Toast.LENGTH_LONG).show();
                // 关闭进度条
                progressDialog.dismiss();
            }

        }
    }
}