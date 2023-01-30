package com.example.sparrownotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class eye extends AppCompatActivity {
    String tag = "MusicService";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye);
        Toast.makeText(eye.this,"MainHelloService onCreate", Toast.LENGTH_SHORT).show();
        Log.i(tag,"MainHelloService onCreate");
        Button b1= (Button)findViewById(R.id.Button01);
        Button b2= (Button)findViewById(R.id.Button02);
        Button b3= (Button)findViewById(R.id.Button03);
        Button b4= (Button)findViewById(R.id.Button04);
        final ServiceConnection conn = new ServiceConnection(){

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Toast.makeText(eye.this, "ServiceConnection onServiceConnected", Toast.LENGTH_SHORT).show();
                Log.i(tag, "ServiceConnection onServiceConnected");

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Toast.makeText(eye.this, "ServiceConnection onServiceDisconnected", Toast.LENGTH_SHORT).show();
                Log.i(tag, "ServiceConnection onServiceDisconnected");

            }};
        View.OnClickListener ocl= new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //显示指定intent所指的对象是个Service
                Intent intent = new Intent(eye.this,MusicService.class);
                switch(v.getId()){
                    case R.id.Button01:
                        //开始服务
                        startService(intent);
                        break;
                    case R.id.Button02:
                        //停止服务
                        stopService(intent);
                        break;
                    case R.id.Button03:
                        //绑定服务
                        bindService(intent,conn, Context.BIND_AUTO_CREATE);
                        break;
                    case R.id.Button04:
                        //解除绑定
                        unbindService(conn);
                        break;
                }
            }
        };
        b1.setOnClickListener(ocl);
        b2.setOnClickListener(ocl);
        b3.setOnClickListener(ocl);
        b4.setOnClickListener(ocl);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(eye.this, "MainHelloService onDestroy", Toast.LENGTH_SHORT).show();
        Log.i(tag, "MainHelloService onDestroy");
    }
}