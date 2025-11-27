package com.itlvck.sblmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.itlvck.sblmobile.R;

public class ResearchFragment extends Fragment {
    public ResearchFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        //Here it goes the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_research, container, false);
        return view;
    }

    //Creating the logic for the research (not finished)
    private void researchView(){}
}

