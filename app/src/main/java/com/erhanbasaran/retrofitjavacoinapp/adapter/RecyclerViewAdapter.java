package com.erhanbasaran.retrofitjavacoinapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erhanbasaran.retrofitjavacoinapp.R;
import com.erhanbasaran.retrofitjavacoinapp.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<CryptoModel> cryptoList;

    //farve hex koder hentet fra color-hex.com
    private String[] colors= {"#36ea4c","#00f6ff","#c80815","#e678b9","#2eb0fb","#da3287","#fff6a1","#66cdaa"};

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
    }

    //layouts og recyclerview forbindelsen skabes
    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent, false);
        return new RowHolder(view);
    }

    //Viewholder altinda tanimlanan viewleri burda baglama islemleri yapiliyor
    //hangi positionda ne gosterilsin vs
    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cryptoList.get(position),colors, position);

    }

    //antallet af rows der skal oprettes
    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPrice;

        public RowHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(CryptoModel cryptoModel, String[] colors, Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));// % 8 remainder 8, dvs brug altid det resterende til at vælge farve da vi kun har 8 farver at vælge imellem
            textName = itemView.findViewById(R.id.text_name);
            textPrice = itemView.findViewById(R.id.text_price);
            textName.setText(cryptoModel.currency);
            textPrice.setText(cryptoModel.price);
        }

    }
}
