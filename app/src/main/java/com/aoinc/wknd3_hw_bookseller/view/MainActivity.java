package com.aoinc.wknd3_hw_bookseller.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.aoinc.wknd3_hw_bookseller.R;
import com.aoinc.wknd3_hw_bookseller.model.Book;
import com.aoinc.wknd3_hw_bookseller.view.adapter.BookRecyclerAdapter;
import com.aoinc.wknd3_hw_bookseller.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;

    private BookRecyclerAdapter bookRecyclerAdapter = new BookRecyclerAdapter(new ArrayList<>());
    private RecyclerView bookListRecyclerView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.main_progress_cicle);
        bookListRecyclerView = findViewById(R.id.book_list_recyclerView);
        bookListRecyclerView.setAdapter(bookRecyclerAdapter);

        mainActivityViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainActivityViewModel.class);

        mainActivityViewModel.getBookList().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                bookRecyclerAdapter.updateBookList(books);
            }
        });

        mainActivityViewModel.progressBarVisibility.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                progressBar.setVisibility(visibility);
            }
        });
    }
}