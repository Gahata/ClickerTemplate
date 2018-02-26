package com.example.pjezi.clickertemplate;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.pjezi.clickertemplate.BuildingsFragment.perSecondView;
import static com.example.pjezi.clickertemplate.BuildingsFragment.setCostTextView;
import static com.example.pjezi.clickertemplate.MainActivity.bankValue;
import static com.example.pjezi.clickertemplate.MainActivity.buildings;
import static com.example.pjezi.clickertemplate.MainActivity.contextMainActivity;
import static com.example.pjezi.clickertemplate.MainActivity.numberToLetterFromDoublePerSecond;
import static com.example.pjezi.clickertemplate.MainActivity.perSecondValue;
import static com.example.pjezi.clickertemplate.MainActivity.resources;
import static com.example.pjezi.clickertemplate.MainActivity.updatePerSecondValue;

class BuildingsAdapter extends RecyclerView.Adapter<BuildingsAdapter.ViewHolder> {

    private Context context;

    private static BuildingsRecyclerViewClickListener mListener;

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView priceTextView;
        private Button purchaseButton;
        private Button sellButton;
        private Button expandButton;

        private ViewHolder(View v) {
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

    public BuildingsAdapter() {
        this.context = contextMainActivity;
    }

    @Override
    public BuildingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.building_card, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Building building = buildings.get(position);
        viewHolder.nameTextView.setText(building.getName());
        viewHolder.descriptionTextView.setText(building.getDescription());
        viewHolder.priceTextView.setText(String.valueOf(building.getCost()));
        viewHolder.purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(building);
            }
        });
        viewHolder.sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(building);
            }
        });
        viewHolder.expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    //method for purchasing a building
    private void purchase(Building building) {
        if (bankValue >= building.cost) {
            //DecimalFormat format = new DecimalFormat("#");
            //format.setDecimalSeparatorAlwaysShown(false);

            bankValue = bankValue - building.cost;
            building.amount++;
            //TODO check if cost is set
            //building.amountTextView.setText(getString(R.string.building_amount, building.amount));
            if (building.amount == 0)
                building.cost = building.baseCost;
            else
                building.cost = building.baseCost * (Math.pow(1.15, building.amount));

            setCostTextView(building);

            updatePerSecondValue();
            //perSecondValue = perSecondValue + building.production;
            perSecondTextChanger(perSecondValue);
        } else {
            Toast.makeText(context,"You don't have enough currency for that",Toast.LENGTH_SHORT).show();
        }
    }

    //method for selling a building for a part of it's cost
    private void sell(Building building) {
        if (building.amount >= 1) {
            //DecimalFormat format = new DecimalFormat("#.#");
            //format.setDecimalSeparatorAlwaysShown(false);

            bankValue = bankValue + 0.5 * building.cost;
            building.amount--;
            //TODO check if cost is set
            //building.amountTextView.setText(getString(R.string.building_amount, building.amount));
            if (building.amount == 0)
                building.cost = building.baseCost;
            else
                building.cost = building.baseCost * (1.15 * (building.amount));

            setCostTextView(building);

            updatePerSecondValue();
            //perSecondValue = perSecondValue + building.production;
            perSecondTextChanger(perSecondValue);
        }
        else
            Toast.makeText(context,"You don't have any building to sell",Toast.LENGTH_SHORT).show();
    }

    //confirmation window for selling
    private void sellConfirmation(final Building building) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder
                .setMessage("Are you sure that you want to sell this bulding?")
                .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        sell(building);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private void perSecondTextChanger(double value) {
        String perSecondString = resources.getString(R.string.per_second_production) + " " + numberToLetterFromDoublePerSecond(value);
        perSecondView.setText(perSecondString);
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }
}