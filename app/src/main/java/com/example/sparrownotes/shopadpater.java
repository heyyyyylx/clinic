package com.example.sparrownotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class shopadpater extends RecyclerView.Adapter<shopadpater.ViewHolder> {
    private List<Shop> list;
    public shopadpater(List<Shop>list){
        this.list=list;
    }
    public boolean[] flag=new boolean[100];
    public int sum=0;
    public int sum1=0;
    TextView priceAll;
    //计算sum和
    public int getSum1(){
        sum1=0;
        for(int i=0;i<list.size();i++){
            if(flag[i]==true){
                sum1+=list.get(i).getNum()*list.get(i).getPrice();
            }
        }
        return sum1;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        ImageView photo_add;
        ImageView photo_down;
        TextView text1;
        TextView text2;
        TextView price;
        TextView num;
        CheckBox radio;
        public ViewHolder(@NonNull View view){
            super(view);
            photo=(ImageView) view.findViewById(R.id.photo);
            photo_add=(ImageView) view.findViewById(R.id.photo_add);
            photo_down=(ImageView) view.findViewById(R.id.ptoto_down);
            text1=(TextView) view.findViewById(R.id.text1);
            text2=(TextView) view.findViewById(R.id.test2);
            price=(TextView) view.findViewById(R.id.price);
            num=(TextView) view.findViewById(R.id.num);
            radio=(CheckBox) view.findViewById(R.id.radio);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_shoppingcar, parent, false);
        //监听事件
        //监听点击的图片事件
        viewHolder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPosition(viewHolder.getAdapterPosition());
                int pos = viewHolder.getAdapterPosition();
                Shop shop = list.get(pos);
                Uri webpage;
                if (shop.getPhotoId() == R.drawable.pic1) {
                    webpage = Uri.parse("https://item.jd.com/100018761109.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic2){
                    webpage = Uri.parse("https://item.jd.com/10023842249226.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic3){
                    webpage=Uri.parse("https://item.jd.com/100018633249.html");
                }
                else {
                    webpage = Uri.parse("");
                }
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(webpage);
                parent.getContext().startActivity(intent);
            }
        });
        viewHolder.photo_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=viewHolder.getAdapterPosition();
                Shop shop= list.get(pos);
                int x=shop.getNum();
                x++;
                shop.setNum(x);
                viewHolder.num.setText(x+"");
                if(flag[pos]==true){
                    priceAll=view2.findViewById(R.id.priceall);
                    priceAll.setText("一共："+getSum1()+".0元");
                    Toast.makeText(view.getContext(),""+priceAll.getText(),Toast.LENGTH_SHORT).show();

                }
            }
        });
        //down-1
        viewHolder.photo_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=viewHolder.getAdapterPosition();
                Shop shop= list.get(pos);
                int x=shop.getNum();
                x--;
                if(x<0) x=0;
                shop.setNum(x);
                viewHolder.num.setText(x+"");
                if(flag[pos]==true){
                    priceAll=view2.findViewById(R.id.priceall);
                    priceAll.setText("一共："+getSum1()+".0元");
                    Toast.makeText(view.getContext(),""+priceAll.getText(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置图片监听
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position){
        Shop shop=list.get(position);
        holder.text1.setText(shop.getText1());
        holder.text2.setText(shop.getText2());
        holder.price.setText(shop.getPrice()+".0");
        holder.num.setText(shop.getNum()+"");
        holder.photo.setImageResource(shop.getPhotoId());
        //       holder.photo_add.setImageResource(shop.getPhoto_addId());
//        holder.photo_down.setImageResource(shop.getPhoto_downId());
//        holder.radio.setVisibility(View.VISIBLE);
        //checkbutton的绑定
        holder.radio.setText((position+1)+"");//设置多选按钮的位置值，删除行后会更新
        holder.radio.setOnCheckedChangeListener(null);
        holder.radio.setChecked(flag[position]);
        holder.radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flag[position]=b;
            }
        });
    }
    @Override
    public int getItemCount(){
        return list.size();
    }
    private int position;
    public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position=position;
    }
}
