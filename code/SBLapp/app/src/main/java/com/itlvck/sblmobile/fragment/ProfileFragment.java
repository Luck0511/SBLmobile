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
import com.itlvck.sblmobile.fragment.books.BooksFragment;

public class ProfileFragment extends Fragment {
    //Variables
    private ImageButton btnGoToLists;
    private ImageButton btnGoToBooks;

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
        btnGoToBooks = view.findViewById(R.id.btnGoToBooks); //Initializing GoToBooks Button

        //Click Event management
        // Go to Lists (finished)
        btnGoToLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToListsFragment();
            }
        });
        // Go to Books (it doesn't work)
        btnGoToBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToBooksFragment();
            }
        });

        return view;
    }

    //Method to navigate to the lists
    private void navigateToListsFragment() {
        ListsFragment listsFragment = new ListsFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, listsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //Method to navigate to the books (it doesn't work)
    private void navigateToBooksFragment() {
        BooksFragment booksFragment = new BooksFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, booksFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
