package com.example.sparrownotes;

import static com.example.sparrownotes.R2.id.buttonpay;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class shoppingcar extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView1;
    private CheckBox selectAll;
    private boolean[] f;
    private TextView clr;
    private TextView del;
    private Button buttonAll;
    private Button buttonpay;
    private TextView priceAll;
    private List<Shop> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private shopadpater adapter;

    public void initShop() {
        Shop shop = new Shop("999 板蓝根颗粒20袋*10g", "【2袋装】低至13.2/袋", R.drawable.pic1, 1, 25);
        list.add(shop);
        Shop shop1 = new Shop("都乐牌金嗓子润喉片喉片", "12片广西金嗓子喉宝咽痛咽喉炎急慢性咽炎", R.drawable.pic2, 1, 12);
        list.add(shop1);
        Shop shop2 = new Shop("美林布洛芬混悬液100ml", "儿童解热镇痛 1盒+1盒4贴退热贴", R.drawable.pic3, 1, 48);
        list.add(shop2);
    }

    public void initRecyclerView2() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // 定义一个线性布局管理器（默认是垂直排列）
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new shopadpater(list);
        recyclerView.setAdapter(adapter);
        //添加默认的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    //全选和全不选
    public void selectAll() {
        selectAll = (CheckBox) findViewById(R.id.checkBoxall);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        f = new boolean[100];
        selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    for (int i = 0; i < 100; i++) {
                        adapter.flag[i] = true;
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < 100; i++) {
                        adapter.flag[i] = false;
                        adapter.notifyDataSetChanged();
                    }
                }
                //更新数据
                // adapter = new ShopAdapter(list);
                // adapter.notifyDataSetChanged();
            }
        });
    }

    //移除功能
    public void removeItem(int position) {
        list.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemChanged(position, list.size());
    }

    //删除
    public void delete() {
        del = (TextView) findViewById(R.id.delect);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(shoppingcar.this, "删除功能", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < list.size(); i++) {
                    if (adapter.flag[i] == true) {
                        removeItem(i);
                        //删除list数组后还要更新flag数组 i没有了i+1的变成了i所有flag[i]=flag[1+1]
                        for (int j = i; j < list.size() - 1; j++)
                            adapter.flag[j] = adapter.flag[j + 1];
                        //删除后i,i--才行,列如第0行删除后仍然从第0行开始
                        i--;
                    }
                }
            }
        });
    }

    //清空功能
    public void clear() {
        clr = (TextView) findViewById(R.id.clear);
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Toast.makeText(MainActivity.this,"清空功能",Toast.LENGTH_SHORT).show();
                list.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    //计算合计总价钱
    public void allPrice(){
        buttonAll=(Button)findViewById(R.id.buttonall);
        priceAll=(TextView)findViewById(R.id.priceall);
        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priceAll.setText(adapter.getSum1()+".0");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcar);
        initShop();
        initRecyclerView2();
        selectAll();
        delete();
        clear();
        allPrice();
        buttonpay=(Button)findViewById(R.id.buttonpay);
        buttonpay.setOnClickListener(shoppingcar.this);
        //ActionBar
        ActionBar actionBar=getSupportActionBar();

        //清屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void onClick(View view){
        Intent intent=new Intent(shoppingcar.this,CustomDialogActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}