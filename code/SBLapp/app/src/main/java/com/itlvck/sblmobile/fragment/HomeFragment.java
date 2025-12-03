package com.itlvck.sblmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.itlvck.sblmobile.R;
import com.itlvck.sblmobile.dto.BookItem;
import com.itlvck.sblmobile.dto.TrendingResponse;
import com.itlvck.sblmobile.fragment.books.BookAdapter;
import com.itlvck.sblmobile.service.ApiServicies;
import com.itlvck.sblmobile.service.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    //Variables
    private RecyclerView booksRecyclerView;
    private BookAdapter bookAdapter;
    private ApiServicies apiServices;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiServices = RetrofitClient.getInstance().getApiService();

        // Initialization
        booksRecyclerView = view.findViewById(R.id.booksRecyclerView);
        bookAdapter = new BookAdapter(requireContext(), new ArrayList<>());
        booksRecyclerView.setAdapter(bookAdapter);
        booksRecyclerView.setHasFixedSize(false);

        //API call
        fetchTrendingBooks();
    }

    private void fetchTrendingBooks() {
        Toast.makeText(getContext(), "Caricamento libri più venduti...", Toast.LENGTH_SHORT).show();

        RetrofitClient.getInstance()
                .getApiService()
                .getTrending()
                .enqueue(new Callback<TrendingResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<TrendingResponse> call, @NonNull Response<TrendingResponse> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().getTrending() != null) {

                            List<BookItem> trendingBooks = response.body().getTrending().getUnifiedList();

                            if (trendingBooks != null && !trendingBooks.isEmpty()) {
                                // Adding data in the RecycleView
                                bookAdapter.updateList(trendingBooks);
                            } else {
                                //Any book found
                                Toast.makeText(getContext(), "Nessun libro trend trovato.", Toast.LENGTH_SHORT).show();
                                bookAdapter.updateList(Collections.emptyList());
                            }
                        } else {
                            // Error
                            Toast.makeText(getContext(), "Errore nel recupero dei dati: " + response.code(), Toast.LENGTH_SHORT).show();
                            // In caso di errore, pulisci la lista
                            bookAdapter.updateList(Collections.emptyList());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<TrendingResponse> call, @NonNull Throwable t) {
                        // Error connection
                        Toast.makeText(getContext(), "Errore di connessione: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        bookAdapter.updateList(Collections.emptyList());
                    }
                });
    }
}