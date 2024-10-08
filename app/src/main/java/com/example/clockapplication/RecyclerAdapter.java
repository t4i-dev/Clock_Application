package com.example.clockapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CountryViewHolder>{
    private ArrayList<String> arrayTime;
    private Context content;
    private CardView cardView;
    public RecyclerAdapter(ArrayList<String> arrayTime, Context content) {
        this.arrayTime = arrayTime;
        this.content = content;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.textView.setText(arrayTime.get(position));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public CountryViewHolder (@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.textTime);
        }
    }

}




