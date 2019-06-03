package com.example.reciprice.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reciprice.R;
import com.example.reciprice.model.Product;
import com.example.reciprice.model.Recipe;
import com.example.reciprice.model.Upc;
import com.google.gson.Gson;

import java.util.List;

public class UpcAdapter extends RecyclerView.Adapter<UpcAdapter.UpcViewHolder> {
    public List<Product> upcs;
    private int position;


    public UpcAdapter(List<Product> upcs){
        this.upcs = upcs;
    }

    @NonNull
    @Override
    public UpcViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_upc, viewGroup, false);
        UpcViewHolder upcViewHolder = new UpcViewHolder(rootView);

        return upcViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UpcViewHolder upcViewHolder, int i) {
        Product currentUpc = upcs.get(i);
        Log.e("currentUpc", currentUpc.getTitle());
        upcViewHolder.textViewName.setText(currentUpc.getTitle());
        Log.e("title", currentUpc.getTitle());

    }

    @Override
    public int getItemCount() {
        if(upcs == null){
            return 0;
        }else{
            return upcs.size();
        }
    }

    public class UpcViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;

        public UpcViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView_upc_name);
            textViewName.setPaintFlags(textViewName.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getLayoutPosition();

                    Product product =  upcs.get(position);

                    Gson gson = new Gson();
                    String json = gson.toJson(product);

                    Intent intent = new Intent(v.getContext(), PriceActivity.class);
                    intent.putExtra("upc", json);
                    v.getContext().startActivity(intent);
                }


            });
        }

    }

    public int getPosition() {
        return position;

    }
}
