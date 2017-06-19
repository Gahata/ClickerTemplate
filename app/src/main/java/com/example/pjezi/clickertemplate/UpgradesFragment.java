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

import java.util.ArrayList;
import java.util.List;

import static com.example.pjezi.clickertemplate.MainActivity.buildings2;

public class UpgradesFragment extends Fragment {

    private ArrayList upgradesList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;

    public UpgradesFragment() {
    }

    public static UpgradesFragment newInstance() {
        UpgradesFragment fragment = new UpgradesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createUpgrades(MainActivity.buildings);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upgrades, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.upgrades_recycler_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (upgradesList.size() != 0) {
            mAdapter = new UpgradesAdapter(upgradesList);
            mRecyclerView.setAdapter(mAdapter);
        }
        return v;
    }

    private void createUpgrades(ArrayList buildings) {
        upgradesList.add(new Upgrade(getString(R.string.upgrade1_name),getString(R.string.upgrade1_description), 10000, buildings2.get(0)));
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
