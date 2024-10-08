package com.example.clockapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CountryViewHolder>{
    private ArrayList<String> arrayTime;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener =clickListener;
    }

    public RecyclerAdapter(ArrayList<String> arrayTime,Context context) {
        this.arrayTime = arrayTime;
        this.context=context;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);

        return new CountryViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.textView.setText(arrayTime.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayTime.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        Button button_delete;

        public CountryViewHolder (@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textTime);

            button_delete=itemView.findViewById(R.id.button_delete);

            button_delete.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        listener.onItemClick(getAdapterPosition());
                     }

            });

        }

    }
}




