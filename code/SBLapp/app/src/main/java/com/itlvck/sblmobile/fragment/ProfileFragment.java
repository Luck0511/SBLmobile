package com.itlvck.sblmobile.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.itlvck.sblmobile.R;

public class ProfileFragment extends Fragment {
    //Variables
    private ImageButton btnGoToLists;

    public ProfileFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        //Here it goes the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //Initialisation variables
        btnGoToLists = view.findViewById(R.id.btnGoToLists); //Initializing GoToLists Button

        //Click Event management
        btnGoToLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToListsFragment();
            }
        });

        return view;
    }

    //Method to navigate to the lists
    private void navigateToListsFragment() {
        ListsFragment listsFragment = new ListsFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, listsFragment);
        transaction.addToBackStack(null); // u can turn back with the "back" Button
        transaction.commit();
    }

}
