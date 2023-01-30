package com.example.sparrownotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.Socket;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    private OutputStream outputStream=null;
    private Socket socket=null;
    private String ip="10.131.209.32";
    private Button btn_cnt;
   // private EditText et_ip;
    private String et_ip="10.131.209.32";
    private EditText et_name;
    //private EditText et_port;
    private String et_port="5.5.5.5";
    private TextView myName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglu);
        setContentView(R.layout.activity_main2);
        btn_cnt=(Button)findViewById(R.id.btn_cnt);
        et_ip="10.131.209.32";
        et_port="5.5.5.5";
        et_name=findViewById(R.id.et_name);
        btn_cnt.setOnClickListener(MainActivity2.this);
    }

    @Override
    public void onClick(View view) {
        String name=et_name.getText().toString();
        if("".equals(name)){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent=new Intent(MainActivity2.this,ChatRoom.class);
            intent.putExtra("name",et_name.getText().toString());
            intent.putExtra("ip",et_ip.toString());
            intent.putExtra("port",et_port.toString());
            startActivity(intent);
        }
    }
}