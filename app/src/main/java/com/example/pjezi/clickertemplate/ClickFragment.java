package com.example.pjezi.clickertemplate;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ClickFragment extends Fragment {

    ImageView click_object;

    private OnFragmentInteractionListener mListener;

    public ClickFragment() {
    }

    public static ClickFragment newInstance() {
        ClickFragment fragment = new ClickFragment();
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
        View v =  inflater.inflate(R.layout.fragment_click, container, false);

        click_object = (ImageView) v.findViewById(R.id.cookie);

        click_object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_object.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.image_click));
                MainActivity.bankValue++;
                MainActivity.totalValue++;
                MainActivity.clicksAmount++;
            }
        });

        return v;
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
