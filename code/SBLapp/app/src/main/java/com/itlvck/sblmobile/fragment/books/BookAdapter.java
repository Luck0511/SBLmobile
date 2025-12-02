package com.itlvck.sblmobile.fragment.books;

import android.content.Context;
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

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    //Variables
    private final List<BookItem> bookList;
    private final Context context;

    public BookAdapter(Context context, List<BookItem> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewTyp) {

        //Here it goes the layout of the fragment
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_book_card, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookItem book = bookList.get(position);

        // Title
        holder.bookTitle.setText(book.getTitle());

        //Cover Image
        Glide.with(context)
                .load(book.getCoverImageUrl())
                .placeholder(R.drawable.books) // Immagine di fallback temporanea
                .into(holder.bookCoverImage);

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        final TextView bookTitle;
        final ImageView bookCoverImage;
        // final ImageView bookmarkButton; //bookmark click

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookCoverImage = itemView.findViewById(R.id.bookCoverImage);
            //bookmarkButton = itemView.findViewById(R.id.myImageButton);
        }
    }

}

