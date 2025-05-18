package com.example.project_samsung.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_samsung.FirbaseClass.Markets;
import com.example.project_samsung.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

    private List<Markets> MarketsList;

    public MarketAdapter(List<Markets> marketsList) {
        this.MarketsList = marketsList;
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_content, parent, false);
        return new MarketViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
        Markets markets = MarketsList.get(position);
        holder.textName.setText(markets.name);
        holder.textLoginMarket.setText(markets.login);
        Picasso.get().load(markets.foto).into(holder.imageViewMarket);

    }

    @Override
    public int getItemCount() {
        return MarketsList.size();
    }

    public static class MarketViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textContent, textLoginMarket;
        ImageView imageViewMarket;

        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            imageViewMarket = itemView.findViewById(R.id.imageViewMarket);
            textLoginMarket = itemView.findViewById(R.id.textLoginMarket);
        }
    }

    public void updateData(List<Markets> newMarkets) {
        this.MarketsList = newMarkets;
        notifyDataSetChanged();
    }
}