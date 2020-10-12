package com.example.reciprice.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reciprice.R;
import com.example.reciprice.model.Offer;

import java.util.List;

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceViewHolder> {
    private List<Offer> offers;
    private int position;

    public class PriceViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewMerchant;
        private TextView textViewPrice;
        private TextView textViewLink;

        public PriceViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView_price_layout_name);
            textViewMerchant = itemView.findViewById(R.id.textView_price_layout_merchant);
            textViewPrice = itemView.findViewById(R.id.textView_price_layout_price);
            textViewLink = itemView.findViewById(R.id.textView_price_link);

        }
    }

    public PriceAdapter(List<Offer> offers) {
        this.offers = offers;
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_price, viewGroup, false);
        PriceViewHolder priceViewHolder = new PriceViewHolder(rootView);

        return priceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder priceViewHolder, int i) {
        Offer offer = offers.get(i);

        priceViewHolder.textViewName.setText(offer.getTitle());
        priceViewHolder.textViewMerchant.setText(offer.getMerchant());
        priceViewHolder.textViewPrice.setText("$" + offer.getPrice());
        priceViewHolder.textViewLink.setText(offer.getLink());
    }

    @Override
    public int getItemCount() {
        if(offers == null){
            return 0;
        }else{
            return offers.size();
        }
    }

    public int getPosition(){
        return position;
    }
}
