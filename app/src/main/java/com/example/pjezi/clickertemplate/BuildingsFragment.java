package com.example.pjezi.clickertemplate;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.pjezi.clickertemplate.MainActivity.bankValue;
import static com.example.pjezi.clickertemplate.MainActivity.numberToLetterFromDouble;
import static com.example.pjezi.clickertemplate.MainActivity.numberToLetterFromDoublePerSecond;
import static com.example.pjezi.clickertemplate.MainActivity.perSecondValue;
import static com.example.pjezi.clickertemplate.MainActivity.updatePerSecondValue;

public class BuildingsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    TextView perSecondView;

    public BuildingsFragment() {
    }

    public static BuildingsFragment newInstance() {
        BuildingsFragment fragment = new BuildingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        perSecondView = (TextView)getActivity().findViewById(R.id.currency_per_second_textview);

        View v =  inflater.inflate(R.layout.fragment_buildings, container, false);

/*        Building building1 = buildings.get(0);
        building1.nameTextView = (TextView) v.findViewById(R.id.building1name);
        building1.amountTextView = (TextView) v.findViewById(R.id.building1amount);
        building1.costTextView = (TextView) v.findViewById(R.id.building1cost);
        building1.purchaseButton = (Button) v.findViewById(R.id.building1purchase);
        building1.sellButton = (Button) v.findViewById(R.id.building1sell);
        building1.expandButton = (Button) v.findViewById(R.id.building1expand);

        //setting OnClickListener for all purchase buttons
        buildings.get(0).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(0));
            }
        });

        buildings.get(0).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(0));
            }
        });

        buildings.get(0).expandedLayout = (LinearLayout) v.findViewById(R.id.building1expandedLayout);
        buildings.get(0).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(0).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(0).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(0).expandedLayout.setVisibility(View.GONE);
            }
        });

        for (Building building : buildings) {
            building.costTextView.setText(numberToLetterFromDouble(building.cost)+" "+getString(R.string.currency));
        }

        for (Building building : buildings) {
            building.amountTextView.setText(getString(R.string.building_amount, building.amount));
        }*/

        return v;
    }

    //method for purchasing a building
    public void purchase(Building building) {
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
            Toast.makeText(getContext(),"You don't have enough currency for that",Toast.LENGTH_SHORT).show();
        }
    }
    //method for selling a building for a part of it's cost
    void sell(Building building) {

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
            Toast.makeText(getContext(),"You don't have any building to sell",Toast.LENGTH_SHORT).show();

    }
    //confirmation window for selling
    private void sellConfirmation(final Building building) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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

    //method setting cost TextView with letters for big numbers
    static void setCostTextView(Building building) {
        String truncatedCost = numberToLetterFromDouble(building.cost);
        //TODO check if cost is set
        //building.costTextView.setText(truncatedCost);
    }

    void perSecondTextChanger(double value) {
        String perSecondString = getResources().getString(R.string.per_second_production) + " " + numberToLetterFromDoublePerSecond(value);
        perSecondView.setText(perSecondString);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}