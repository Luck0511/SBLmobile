package com.itlvck.sblmobile.fragment.books;

import android.content.Context;
import android.util.Log; // Importante
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itlvck.sblmobile.R;
import com.itlvck.sblmobile.dto.BookItem;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    //Variables
    private List<BookItem> bookList;
    private final Context context;

    public BookAdapter(Context context, List<BookItem> bookList) {
        this.context = context;
        this.bookList = new ArrayList<>(bookList);
    }

    public void updateList(List<BookItem> newList) {
        this.bookList.clear();
        if (newList != null) {
            this.bookList.addAll(newList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewTyp) {
        //Here it goes the layout of the fragment
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_book_card, parent, false);
        return new BookViewHolder(view);
    }

    // ✅ IMPLEMENTAZIONE CORRETTA E UNICA di onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookItem book = bookList.get(position);

        String imageUrl = book.getCoverImageUrl();
        Log.d("COVER_DEBUG", "URL Immagine: " + imageUrl); // <-- Log di verifica

        // Title
        holder.bookTitle.setText(book.getTitle());

        //Cover Image
        // Glide caricherà l'immagine dall'URL. Se l'URL è nullo o vuoto,
        // verrà visualizzato il placeholder (R.drawable.books).
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.books)
                .into(holder.bookCoverImage);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        final TextView bookTitle;
        final ImageView bookCoverImage;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookCoverImage = itemView.findViewById(R.id.bookCoverImage);

        }
    }
}