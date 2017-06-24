package com.example.pjezi.clickertemplate;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BuildingsAdapter extends RecyclerView.Adapter<BuildingsAdapter.ViewHolder> {

    private List<Building> buildingList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView nameTextView;
        public TextView descriptionTextView;
        public TextView priceTextView;
        public Button purchaseButton;
        public Button sellButton;
        public Button expandButton;

        public ViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.building_cardview) ;
            nameTextView = (TextView) v.findViewById(R.id.building_name_textview);
            descriptionTextView = (TextView) v.findViewById(R.id.building_description_textview);
            priceTextView = (TextView) v.findViewById(R.id.building_price_textview);
            purchaseButton = (Button) v.findViewById(R.id.building_purchase_button);
            sellButton = (Button) v.findViewById(R.id.building_sell_button);
            expandButton = (Button) v.findViewById(R.id.building_expand_button);
        }
    }

    public BuildingsAdapter(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    @Override
    public BuildingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.building_card, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Building building = buildingList.get(position);
        viewHolder.nameTextView.setText(building.getName());
        viewHolder.descriptionTextView.setText(building.getDescription());
        viewHolder.priceTextView.setText(String.valueOf(building.getCost()));
        viewHolder.purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //purchase(building);
            }
        });
    }

    @Override
    public int getItemCount() {
        return buildingList.size();
    }
}