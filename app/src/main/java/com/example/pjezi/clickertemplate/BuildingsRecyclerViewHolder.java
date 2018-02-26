package com.example.pjezi.clickertemplate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class BuildingsRecyclerViewHolder extends RecyclerView.ViewHolder  {

    public Button purchaseButton,sellButton, expandButton;
    public BuildingsRecyclerViewHolder(View itemView) {
        super(itemView);
        this.purchaseButton = (Button) itemView.findViewById(R.id.building_purchase_button);
        this.sellButton = (Button) itemView.findViewById(R.id.building_sell_button);
        this.expandButton = (Button) itemView.findViewById(R.id.building_expand_button);
    }
}