package com.example.reciprice.ui;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reciprice.R;

import java.util.List;


    public class FindGroceryStoreAdapter extends RecyclerView.Adapter<FindGroceryStoreAdapter.ViewHolder> {

        private List<String> ingredientList;
        private LayoutInflater mInflater;
        private ItemClickListener mClickListener;


        FindGroceryStoreAdapter(Context context, List<String> data) {
            this.mInflater = LayoutInflater.from(context);
            this.ingredientList = data;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.layout_ingredient_item, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String ingredient = ingredientList.get(position);
            holder.myTextView.setText(ingredient);
        }


        @Override
        public int getItemCount() {
            return ingredientList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView myTextView;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.textView_ingredient);
                myTextView.setPaintFlags(myTextView.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        // convenience method for getting data at click position
        String getItem(int id) {
            return ingredientList.get(id);
        }

        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }