package com.erhanbasaran.retrofitjavacoinapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erhanbasaran.retrofitjavacoinapp.R;
import com.erhanbasaran.retrofitjavacoinapp.model.CryptoModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> implements Filterable  {

    private ArrayList<CryptoModel> cryptoList;
    private ArrayList<CryptoModel> cryptoListFull; //kopi af ovenstående


    //farve hex koder hentet fra color-hex.com
    private String[] colors= {"#36ea4c","#00f6ff","#c80815","#e678b9","#2eb0fb","#da3287","#603a2d","#66cdaa"};

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
        cryptoListFull = new ArrayList<>(cryptoList);// opretter en  kopi liste som indeholder det samme som den anden men som kan bruges uafhængigt af cryptolist
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        //kører på background tread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CryptoModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(cryptoListFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CryptoModel cryptoModel : cryptoListFull){
                    if (cryptoModel.currency.toLowerCase().contains(filterPattern)){
                        filteredList.add(cryptoModel);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cryptoList.clear();
            cryptoList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

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
