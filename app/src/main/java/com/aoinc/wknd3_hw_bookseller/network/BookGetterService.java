package com.aoinc.wknd3_hw_bookseller.network;

import com.aoinc.wknd3_hw_bookseller.model.Book;
import com.aoinc.wknd3_hw_bookseller.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookGetterService {
    @GET(Constants.PATH_URL)
    public Call<List<Book>> fetchBooks();
}
