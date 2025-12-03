package com.itlvck.sblmobile.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itlvck.sblmobile.R;
import com.itlvck.sblmobile.dto.BookItem;
import com.itlvck.sblmobile.fragment.books.BookAdapter;
import com.itlvck.sblmobile.service.ApiServicies;
import com.itlvck.sblmobile.service.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResearchFragment extends Fragment {
    //variables
    private EditText searchEditText;
    private ImageButton clearButton;
    private RecyclerView booksRecyclerView;
    private BookAdapter bookAdapter;
    private List<BookItem> bookList;
    private ApiServicies apiService;

    private static final String SEARCH_QUERY_KEY = "query";

    public ResearchFragment() {
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

        // Initialisation variables (ID dal layout XML fornito)
        searchEditText = view.findViewById(R.id.searchEditText);
        clearButton = view.findViewById(R.id.clearButton);
        booksRecyclerView = view.findViewById(R.id.booksRecyclerView);

        // Setup RecyclerView
        apiService = RetrofitClient.getInstance().getApiService();
        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(requireContext(), bookList);
        booksRecyclerView.setAdapter(bookAdapter);

        // Listeners
        setupSearchListeners();
        setupClearButton();
    }


    //Creating the logic for the research (not finished)
    private void setupSearchListeners() {
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearButton.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setupClearButton() {
        clearButton.setOnClickListener(v -> {
            searchEditText.setText("");
            bookList.clear();
            bookAdapter.notifyDataSetChanged();
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
    }

    private void performSearch() {
        String query = searchEditText.getText().toString().trim();

        if (query.isEmpty()) {
            Toast.makeText(getContext(), "Inserisci un termine di ricerca", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> queries = new HashMap<>();
        queries.put(SEARCH_QUERY_KEY, query);

        // API Call
        apiService.searchBooks(queries).enqueue(new Callback<BookItem.ResearchResponse>() {
            @Override
            public void onResponse(Call<BookItem.ResearchResponse> call, Response<BookItem.ResearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookItem> newBooks = response.body().getBooks();
                    updateBookList(newBooks);
                } else {

                    Toast.makeText(getContext(), "Errore nella ricerca: " + response.code(), Toast.LENGTH_LONG).show();
                    updateBookList(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<BookItem.ResearchResponse> call, Throwable t) {

                Toast.makeText(getContext(), "Errore di connessione: " + t.getMessage(), Toast.LENGTH_LONG).show();
                updateBookList(new ArrayList<>());
            }
        });
    }

    private void updateBookList(List<BookItem> newBooks) {
        bookList.clear();
        if (newBooks != null && !newBooks.isEmpty()) {
            bookList.addAll(newBooks);
        } else if (searchEditText.getText().length() > 0) {
            Toast.makeText(getContext(), "Nessun risultato trovato", Toast.LENGTH_SHORT).show();
        }

        bookAdapter.notifyDataSetChanged();
    }
}


