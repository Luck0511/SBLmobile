package com.itlvck.sblmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.itlvck.sblmobile.R;

public class ListsFragment extends Fragment {
    //Variables
    private ImageButton btnBackToProfile;
    private Button btnCreateList;

    public ListsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        //Here it goes the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        //Initialisation variables
        btnBackToProfile = view.findViewById(R.id.btnBackToProfile);
        btnCreateList = view.findViewById(R.id.btnCreateList);

        //Click Event management
        btnBackToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToProfileFragment();
            }
        });

        return view;
    }

    //Method to navigate back to the profile
    private void backToProfileFragment() {
        getParentFragmentManager().popBackStack();
    }
}
