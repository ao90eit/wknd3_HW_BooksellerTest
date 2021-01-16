package com.aoinc.wknd3_hw_bookseller.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aoinc.wknd3_hw_bookseller.R;
import com.aoinc.wknd3_hw_bookseller.model.Book;
import com.bumptech.glide.Glide;

import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.BookViewHolder> {
    private List<Book> bookList;

    public BookRecyclerAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void updateBookList(List<Book> newBookList){
        bookList = newBookList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        Log.d("TAG_X", book.getImageURL());
        Glide.with(holder.itemView.getContext())
                .load(book.getImageURL().replace("http:", "https:"))
                .placeholder(R.drawable.ic_round_menu_book_24)
                .into(holder.bookThumbnail);

        holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView bookThumbnail;
        TextView bookTitle;
        TextView bookAuthor;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            bookThumbnail = itemView.findViewById(R.id.book_thumbnail_imageView);
            bookTitle = itemView.findViewById(R.id.book_title_textView);
            bookAuthor = itemView.findViewById(R.id.book_author_textView);
        }
    }
}
