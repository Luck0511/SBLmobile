package com.itlvck.sblmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itlvck.sblmobile.R;
import com.itlvck.sblmobile.dto.BookItem;
import com.itlvck.sblmobile.fragment.books.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResearchFragment extends Fragment {
    //variables
    private RecyclerView booksRecyclerView;
    private EditText searchEditText;
    private ImageButton clearButton;
    private BookAdapter booksAdapter;
    private List<BookItem> allBooks;
    private List<BookItem> filteredBooks;


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
    @Override
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialisation variables
        booksRecyclerView = view.findViewById(R.id.booksRecyclerView);
        searchEditText = view.findViewById(R.id.searchEditText);
        clearButton = view.findViewById(R.id.clearButton);
    }

    //Creating the logic for the research (not finished)
    private void researchView(){}
}

