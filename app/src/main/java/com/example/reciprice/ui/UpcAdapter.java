package com.example.reciprice.ui;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reciprice.R;
import com.example.reciprice.model.Product;
import com.example.reciprice.model.Upc;

import java.util.List;

public class UpcAdapter extends RecyclerView.Adapter<UpcAdapter.UpcViewHolder> {
    public List<Product> upcs;
   // private UpcAdapter.ItemClickListener mClickListener;


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
//            textViewName.setPaintFlags(textViewName.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
    }
//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return upcs.get(id).getUpc();
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(UpcAdapter.ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }

}
