package com.example.pjezi.clickertemplate;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UpgradesAdapter extends RecyclerView.Adapter<UpgradesAdapter.ViewHolder> {

    private List<Upgrade> upgradeList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView priceTextView;
        public Button purchaseButton;

        public ViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.upgrade_card) ;
            titleTextView = (TextView) v.findViewById(R.id.upgrade_title);
            descriptionTextView = (TextView) v.findViewById(R.id.upgrade_description);
            priceTextView = (TextView) v.findViewById(R.id.upgrade_price);
            purchaseButton = (Button) v.findViewById(R.id.upgrade_purchase);
        }
    }

    public UpgradesAdapter(List<Upgrade> upgradeList) {
        this.upgradeList = upgradeList;
    }

    @Override
    public UpgradesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.upgrade_card, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Upgrade upgrade = upgradeList.get(position);
        viewHolder.titleTextView.setText(upgrade.getTitle());
        viewHolder.descriptionTextView.setText(upgrade.getDescription());
        viewHolder.priceTextView.setText(String.valueOf(upgrade.getPrice()));
    }

    @Override
    public int getItemCount() {
        return upgradeList.size();
    }
}