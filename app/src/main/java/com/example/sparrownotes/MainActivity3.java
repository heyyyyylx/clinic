package com.example.sparrownotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        String[] titles=new String[]{"眼保健操","海报"};
        int[] image=new int[]{R.drawable.eyes,R.drawable.haibao};
        GridView gvinfo=(GridView) findViewById(R.id.gvinfo);
        List<Map<String,Object>>list=new ArrayList<>();
        for(int i=0;i<image.length;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("ii",image[i]);
            map.put("tt",titles[i]);
            map.put("gg",titles[i]);
            list.add(map);
        }
        SimpleAdapter ad=new SimpleAdapter(this,list,R.layout.gvitem,new String[]{"ii","tt","gg","ff","aa","hh","uu"},new int[]{R.id.images,R.id.titles});
        gvinfo.setAdapter(ad);
        gvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
                Intent intent=null;
                switch (arg2){
                    case 0:
                        intent=new Intent(MainActivity3.this,eye.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent=new Intent(MainActivity3.this,bizhi.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}