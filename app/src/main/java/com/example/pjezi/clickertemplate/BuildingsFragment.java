package com.example.pjezi.clickertemplate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.pjezi.clickertemplate.MainActivity.buildings;
import static com.example.pjezi.clickertemplate.MainActivity.numberToLetterFromDouble;
import static com.example.pjezi.clickertemplate.MainActivity.numberToLetterFromDoublePerSecond;
import static com.example.pjezi.clickertemplate.MainActivity.resources;

public class BuildingsFragment extends Fragment {

    RecyclerView buildingsRecyclerView;

    private OnFragmentInteractionListener mListener;

    static TextView perSecondView;

    private Context context;

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


    @Override
    public void onStart() {
        super.onStart();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        buildingsRecyclerView = (RecyclerView) getActivity().findViewById(R.id.buildings_recycler_view);
        buildingsRecyclerView.setLayoutManager(mLayoutManager);
        BuildingsAdapter mAdapter = new BuildingsAdapter();
        buildingsRecyclerView.setAdapter(mAdapter);

        //context = contextMainActivity;

        //perSecondView = (TextView)getActivity().findViewById(R.id.currency_per_second_textview);
        //buildingsRecyclerView.notifyDataSetChanged();
    }

    void createBuildingsArray() {
        Building building1 = new Building();
        building1.name = resources.getString(R.string.building1_name);
        building1.level = 1;
        building1.baseCost = 15;
        building1.cost = building1.baseCost;
        building1.production = 0.1;
        buildings.add(building1);

        Building building2 = new Building();
        building2.name = resources.getString(R.string.building2_name);
        building2.amount = 0;
        building2.level = 1;
        building2.baseCost = 100;
        building2.cost = building2.baseCost;
        building2.production = 1;
        buildings.add(building2);

        Building building3 = new Building();
        building3.name = resources.getString(R.string.building3_name);
        building3.amount = 0;
        building3.level = 1;
        building3.baseCost = 1100;
        building3.cost = building3.baseCost;
        building3.production = 8;
        buildings.add(building3);

        Building building4 = new Building();
        building4.name = resources.getString(R.string.building4_name);
        building4.amount = 0;
        building4.level = 1;
        building4.baseCost = 12000;
        building4.cost = building4.baseCost;
        building4.production = 47;
        buildings.add(building4);

        Building building5 = new Building();
        building5.name = resources.getString(R.string.building5_name);
        building5.amount = 0;
        building5.level = 1;
        building5.baseCost = 130000;
        building5.cost = building5.baseCost;
        building5.production = 260;
        buildings.add(building5);

        Building building6 = new Building();
        building6.name = resources.getString(R.string.building6_name);
        building6.amount = 0;
        building6.level = 1;
        building6.baseCost = 1400000;
        building6.cost = building6.baseCost;
        building6.production = 1400;
        buildings.add(building6);

        Building building7 = new Building();
        building7.name = resources.getString(R.string.building7_name);
        building7.amount = 0;
        building7.level = 1;
        building7.baseCost = 20000000;
        building7.cost = building7.baseCost;
        building7.production = 7800;
        buildings.add(building7);

        Building building8 = new Building();
        building8.name = resources.getString(R.string.building8_name);
        building8.amount = 0;
        building8.level = 1;
        building8.baseCost = 330000000;
        building8.cost = building8.baseCost;
        building8.production = 44000;
        buildings.add(building8);

        Building building9 = new Building();
        building9.name = resources.getString(R.string.building9_name);
        building9.amount = 0;
        building9.level = 1;
        building9.baseCost = 5100000000d;
        building9.cost = building9.baseCost;
        building9.production = 260000;
        buildings.add(building9);

        Building building10 = new Building();
        building10.name = resources.getString(R.string.building10_name);
        building10.amount = 0;
        building10.level = 1;
        building10.baseCost = 75000000000d;
        building10.cost = building10.baseCost;
        building10.production = 1600000;
        buildings.add(building10);

        Building building11 = new Building();
        building11.name = resources.getString(R.string.building11_name);
        building11.amount = 0;
        building11.level = 1;
        building11.baseCost = 1000000000000d;
        building11.cost = building11.baseCost;
        building11.production = 10000000;
        buildings.add(building11);

        Building building12 = new Building();
        building12.name = resources.getString(R.string.building12_name);
        building12.amount = 0;
        building12.level = 1;
        building12.baseCost = 14000000000000d;
        building12.cost = building12.baseCost;
        building12.production = 65000000;
        buildings.add(building12);


        //TODO set numerical values that are anyhow proper for buildings 13-15
        Building building13 = new Building();
        building13.name = resources.getString(R.string.building13_name);
        building13.amount = 0;
        building13.level = 1;
        building13.baseCost = 14000000000000d;
        building13.cost = building13.baseCost;
        building13.production = 65000000;
        buildings.add(building13);

        Building building14 = new Building();
        building14.name = resources.getString(R.string.building14_name);
        building14.amount = 0;
        building14.level = 1;
        building14.baseCost = 14000000000000d;
        building14.cost = building14.baseCost;
        building14.production = 65000000;
        buildings.add(building14);

        Building building15 = new Building();
        building15.name = resources.getString(R.string.building15_name);
        building15.amount = 0;
        building15.level = 1;
        building15.baseCost = 14000000000000d;
        building15.cost = building15.baseCost;
        building15.production = 65000000;
        buildings.add(building15);
    }


    /*//method for purchasing a building
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
    public void sell(Building building) {

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
    }*/

    //method setting cost TextView with letters for big numbers
    static void setCostTextView(Building building) {
        String truncatedCost = numberToLetterFromDouble(building.cost);
        //TODO check if cost is set
        //building.costTextView.setText(truncatedCost);
    }

    public void perSecondTextChanger(double value) {
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