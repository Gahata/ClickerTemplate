package com.example.pjezi.clickertemplate;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.example.pjezi.clickertemplate.MainActivity.upgrades;

public class UpgradesAdapter extends RecyclerView.Adapter<UpgradesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView nameTextView;
        public TextView descriptionTextView;
        public TextView priceTextView;
        public Button purchaseButton;

        public ViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.upgrade_cardiew) ;
            nameTextView = (TextView) v.findViewById(R.id.upgrade_name_textview);
            descriptionTextView = (TextView) v.findViewById(R.id.upgrade_description_textview);
            priceTextView = (TextView) v.findViewById(R.id.upgrade_price_textview);
            purchaseButton = (Button) v.findViewById(R.id.upgrade_purchase_button);
        }
    }

    public UpgradesAdapter() {
    }

    @Override
    public UpgradesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.upgrade_card, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Upgrade upgrade = upgrades.get(position);
        viewHolder.nameTextView.setText(upgrade.getName());
        viewHolder.descriptionTextView.setText(upgrade.getDescription());
        viewHolder.priceTextView.setText(String.valueOf(upgrade.getPrice()));
    }

    @Override
    public int getItemCount() {
        return upgrades.size();
    }
}