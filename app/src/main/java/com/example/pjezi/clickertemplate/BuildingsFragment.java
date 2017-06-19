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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static com.example.pjezi.clickertemplate.MainActivity.bankValue;
import static com.example.pjezi.clickertemplate.MainActivity.buildings;
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

        MainActivity.building building1 = buildings.get(0);
        building1.nameTextView = (TextView) v.findViewById(R.id.building1name);
        building1.amountTextView = (TextView) v.findViewById(R.id.building1amount);
        building1.costTextView = (TextView) v.findViewById(R.id.building1cost);
        building1.purchaseButton = (Button) v.findViewById(R.id.building1purchase);
        building1.sellButton = (Button) v.findViewById(R.id.building1sell);
        building1.expandButton = (Button) v.findViewById(R.id.building1expand);

        MainActivity.building building2 = buildings.get(1);
        building2.nameTextView = (TextView) v.findViewById(R.id.building2name);
        building2.amountTextView = (TextView) v.findViewById(R.id.building2amount);
        building2.costTextView = (TextView) v.findViewById(R.id.building2cost);
        building2.purchaseButton = (Button) v.findViewById(R.id.building2purchase);
        building2.sellButton = (Button) v.findViewById(R.id.building2sell);
        building2.expandButton = (Button) v.findViewById(R.id.building2expand);

        MainActivity.building building3 = buildings.get(2);
        building3.nameTextView = (TextView) v.findViewById(R.id.building3name);
        building3.amountTextView = (TextView) v.findViewById(R.id.building3amount);
        building3.costTextView = (TextView) v.findViewById(R.id.building3cost);
        building3.purchaseButton = (Button) v.findViewById(R.id.building3purchase);
        building3.sellButton = (Button) v.findViewById(R.id.building3sell);
        building3.expandButton = (Button) v.findViewById(R.id.building3expand);

        MainActivity.building building4 = buildings.get(3);
        building4.nameTextView = (TextView) v.findViewById(R.id.building4name);
        building4.amountTextView = (TextView) v.findViewById(R.id.building4amount);
        building4.costTextView = (TextView) v.findViewById(R.id.building4cost);
        building4.purchaseButton = (Button) v.findViewById(R.id.building4purchase);
        building4.sellButton = (Button) v.findViewById(R.id.building4sell);
        building4.expandButton = (Button) v.findViewById(R.id.building4expand);

        MainActivity.building building5 = buildings.get(4);
        building5.nameTextView = (TextView) v.findViewById(R.id.building5name);
        building5.amountTextView = (TextView) v.findViewById(R.id.building5amount);
        building5.costTextView = (TextView) v.findViewById(R.id.building5cost);
        building5.purchaseButton = (Button) v.findViewById(R.id.building5purchase);
        building5.sellButton = (Button) v.findViewById(R.id.building5sell);
        building5.expandButton = (Button) v.findViewById(R.id.building5expand);

        MainActivity.building building6 = buildings.get(5);
        building6.nameTextView = (TextView) v.findViewById(R.id.building6name);
        building6.amountTextView = (TextView) v.findViewById(R.id.building6amount);
        building6.costTextView = (TextView) v.findViewById(R.id.building6cost);
        building6.purchaseButton = (Button) v.findViewById(R.id.building6purchase);
        building6.sellButton = (Button) v.findViewById(R.id.building6sell);
        building6.expandButton = (Button) v.findViewById(R.id.building6expand);

        MainActivity.building building7 = buildings.get(6);
        building7.nameTextView = (TextView) v.findViewById(R.id.building7name);
        building7.amountTextView = (TextView) v.findViewById(R.id.building7amount);
        building7.costTextView = (TextView) v.findViewById(R.id.building7cost);
        building7.purchaseButton = (Button) v.findViewById(R.id.building7purchase);
        building7.sellButton = (Button) v.findViewById(R.id.building7sell);
        building7.expandButton = (Button) v.findViewById(R.id.building7expand);

        MainActivity.building building8 = buildings.get(7);
        building8.nameTextView = (TextView) v.findViewById(R.id.building8name);
        building8.amountTextView = (TextView) v.findViewById(R.id.building8amount);
        building8.costTextView = (TextView) v.findViewById(R.id.building8cost);
        building8.purchaseButton = (Button) v.findViewById(R.id.building8purchase);
        building8.sellButton = (Button) v.findViewById(R.id.building8sell);
        building8.expandButton = (Button) v.findViewById(R.id.building8expand);

        MainActivity.building building9 = buildings.get(8);
        building9.nameTextView = (TextView) v.findViewById(R.id.building9name);
        building9.amountTextView = (TextView) v.findViewById(R.id.building9amount);
        building9.costTextView = (TextView) v.findViewById(R.id.building9cost);
        building9.purchaseButton = (Button) v.findViewById(R.id.building9purchase);
        building9.sellButton = (Button) v.findViewById(R.id.building9sell);
        building9.expandButton = (Button) v.findViewById(R.id.building9expand);

        MainActivity.building building10 = buildings.get(9);
        building10.nameTextView = (TextView) v.findViewById(R.id.building10name);
        building10.amountTextView = (TextView) v.findViewById(R.id.building10amount);
        building10.costTextView = (TextView) v.findViewById(R.id.building10cost);
        building10.purchaseButton = (Button) v.findViewById(R.id.building10purchase);
        building10.sellButton = (Button) v.findViewById(R.id.building10sell);
        building10.expandButton = (Button) v.findViewById(R.id.building10expand);

        MainActivity.building building11 = buildings.get(10);
        building11.nameTextView = (TextView) v.findViewById(R.id.building11name);
        building11.amountTextView = (TextView) v.findViewById(R.id.building11amount);
        building11.costTextView = (TextView) v.findViewById(R.id.building11cost);
        building11.purchaseButton = (Button) v.findViewById(R.id.building11purchase);
        building11.sellButton = (Button) v.findViewById(R.id.building11sell);
        building11.expandButton = (Button) v.findViewById(R.id.building11expand);

        MainActivity.building building12 = buildings.get(11);
        building12.nameTextView = (TextView) v.findViewById(R.id.building12name);
        building12.amountTextView = (TextView) v.findViewById(R.id.building12amount);
        building12.costTextView = (TextView) v.findViewById(R.id.building12cost);
        building12.purchaseButton = (Button) v.findViewById(R.id.building12purchase);
        building12.sellButton = (Button) v.findViewById(R.id.building12sell);
        building12.expandButton = (Button) v.findViewById(R.id.building12expand);

        MainActivity.building building13 = buildings.get(12);
        building13.nameTextView = (TextView) v.findViewById(R.id.building13name);
        building13.amountTextView = (TextView) v.findViewById(R.id.building13amount);
        building13.costTextView = (TextView) v.findViewById(R.id.building13cost);
        building13.purchaseButton = (Button) v.findViewById(R.id.building13purchase);
        building13.sellButton = (Button) v.findViewById(R.id.building13sell);
        building13.expandButton = (Button) v.findViewById(R.id.building13expand);

        MainActivity.building building14 = buildings.get(13);
        building14.nameTextView = (TextView) v.findViewById(R.id.building14name);
        building14.amountTextView = (TextView) v.findViewById(R.id.building14amount);
        building14.costTextView = (TextView) v.findViewById(R.id.building14cost);
        building14.purchaseButton = (Button) v.findViewById(R.id.building14purchase);
        building14.sellButton = (Button) v.findViewById(R.id.building14sell);
        building14.expandButton = (Button) v.findViewById(R.id.building14expand);

        MainActivity.building building15 = buildings.get(14);
        building15.nameTextView = (TextView) v.findViewById(R.id.building15name);
        building15.amountTextView = (TextView) v.findViewById(R.id.building15amount);
        building15.costTextView = (TextView) v.findViewById(R.id.building15cost);
        building15.purchaseButton = (Button) v.findViewById(R.id.building15purchase);
        building15.sellButton = (Button) v.findViewById(R.id.building15sell);
        building15.expandButton = (Button) v.findViewById(R.id.building15expand);

        //setting OnClickListener for all purchase buttons
        buildings.get(0).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(0));
            }
        });

        buildings.get(1).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(1));
            }
        });

        buildings.get(2).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(2));
            }
        });

        buildings.get(3).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(3));
            }
        });

        buildings.get(4).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(4));
            }
        });

        buildings.get(5).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(5));
            }
        });

        buildings.get(6).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(6));
            }
        });

        buildings.get(7).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(7));
            }
        });

        buildings.get(8).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(8));
            }
        });

        buildings.get(9).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(9));
            }
        });

        buildings.get(10).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(10));
            }
        });

        buildings.get(11).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(11));
            }
        });

        buildings.get(12).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(12));
            }
        });

        buildings.get(13).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(13));
            }
        });

        buildings.get(14).purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase(buildings.get(14));
            }
        });

        buildings.get(0).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(0));
            }
        });

        buildings.get(1).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(1));
            }
        });

        buildings.get(2).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(2));
            }
        });

        buildings.get(3).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(3));
            }
        });

        buildings.get(4).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(4));
            }
        });

        buildings.get(5).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(5));
            }
        });

        buildings.get(6).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(6));
            }
        });

        buildings.get(7).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(7));
            }
        });

        buildings.get(8).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(8));
            }
        });

        buildings.get(9).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(9));
            }
        });

        buildings.get(10).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(10));
            }
        });

        buildings.get(11).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(11));
            }
        });

        buildings.get(12).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(12));
            }
        });

        buildings.get(13).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(13));
            }
        });

        buildings.get(14).sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellConfirmation(buildings.get(14));
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

        buildings.get(1).expandedLayout = (LinearLayout) v.findViewById(R.id.building2expandedLayout);
        buildings.get(1).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(1).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(1).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(1).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(2).expandedLayout = (LinearLayout) v.findViewById(R.id.building3expandedLayout);
        buildings.get(2).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(2).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(2).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(2).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(3).expandedLayout = (LinearLayout) v.findViewById(R.id.building4expandedLayout);
        buildings.get(3).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(3).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(3).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(3).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(4).expandedLayout = (LinearLayout) v.findViewById(R.id.building5expandedLayout);
        buildings.get(4).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(4).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(4).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(4).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(5).expandedLayout = (LinearLayout) v.findViewById(R.id.building6expandedLayout);
        buildings.get(5).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(5).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(5).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(5).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(6).expandedLayout = (LinearLayout) v.findViewById(R.id.building7expandedLayout);
        buildings.get(6).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(6).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(6).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(6).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(7).expandedLayout = (LinearLayout) v.findViewById(R.id.building8expandedLayout);
        buildings.get(7).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(7).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(7).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(7).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(8).expandedLayout = (LinearLayout) v.findViewById(R.id.building9expandedLayout);
        buildings.get(8).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(8).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(8).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(8).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(9).expandedLayout = (LinearLayout) v.findViewById(R.id.building10expandedLayout);
        buildings.get(9).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(9).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(9).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(9).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(10).expandedLayout = (LinearLayout) v.findViewById(R.id.building11expandedLayout);
        buildings.get(10).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(10).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(10).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(10).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(11).expandedLayout = (LinearLayout) v.findViewById(R.id.building12expandedLayout);
        buildings.get(11).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(11).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(11).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(11).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(12).expandedLayout = (LinearLayout) v.findViewById(R.id.building13expandedLayout);
        buildings.get(12).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(12).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(12).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(12).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(13).expandedLayout = (LinearLayout) v.findViewById(R.id.building14expandedLayout);
        buildings.get(13).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(13).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(13).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(13).expandedLayout.setVisibility(View.GONE);
            }
        });

        buildings.get(14).expandedLayout = (LinearLayout) v.findViewById(R.id.building15expandedLayout);
        buildings.get(14).expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buildings.get(14).expandedLayout.getVisibility() == View.GONE)
                    buildings.get(14).expandedLayout.setVisibility(View.VISIBLE);
                else
                    buildings.get(14).expandedLayout.setVisibility(View.GONE);
            }
        });

        for (MainActivity.building building : buildings) {
            building.costTextView.setText(numberToLetterFromDouble(building.cost)+" "+getString(R.string.currency));
        }

        for (MainActivity.building building : buildings) {
            building.amountTextView.setText(getString(R.string.building_amount, building.amount));
        }

        return v;
    }

    //method for purchasing a building
    void purchase(MainActivity.building building) {
        if (bankValue >= building.cost) {
            //DecimalFormat format = new DecimalFormat("#");
            //format.setDecimalSeparatorAlwaysShown(false);

            bankValue = bankValue - building.cost;
            building.amount++;
            building.amountTextView.setText(getString(R.string.building_amount, building.amount));
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
    void sell(MainActivity.building building) {

        if (building.amount >= 1) {
            //DecimalFormat format = new DecimalFormat("#.#");
            //format.setDecimalSeparatorAlwaysShown(false);

            bankValue = bankValue + 0.5 * building.cost;
            building.amount--;
            building.amountTextView.setText(getString(R.string.building_amount, building.amount));
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
    private void sellConfirmation(final MainActivity.building building) {
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
    static void setCostTextView(MainActivity.building building) {
        String truncatedCost = numberToLetterFromDouble(building.cost);
        building.costTextView.setText(truncatedCost);
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