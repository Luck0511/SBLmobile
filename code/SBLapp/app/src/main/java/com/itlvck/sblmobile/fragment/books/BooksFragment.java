package com.itlvck.sblmobile.fragment.books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.itlvck.sblmobile.R;

public class BooksFragment extends Fragment {
    //Variables
    private ImageButton btnBackToProfile;

    public BooksFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        //Here it goes the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        //Initialisation variables
        btnBackToProfile = view.findViewById(R.id.btnBackToProfile);

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
