package com.aoinc.wknd3_hw_bookseller.network;

import com.aoinc.wknd3_hw_bookseller.model.Book;
import com.aoinc.wknd3_hw_bookseller.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookGetterRetrofit {

    public static BookGetterRetrofit bookGetterRetrofitSingleton = null;
    private BookGetterService bookGetterService;

    public static BookGetterRetrofit getBookGetterRetrofit() {
        if (bookGetterRetrofitSingleton == null)
            return new BookGetterRetrofit();
        return bookGetterRetrofitSingleton;
    }

    private BookGetterRetrofit(){
        this.bookGetterService = createBookGetterApi(createRetrofit());
    }

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private BookGetterService createBookGetterApi(Retrofit retrofit) {
        return retrofit.create(BookGetterService.class);
    }

    public Call<List<Book>> getBooks(){
        return bookGetterService.fetchBooks();
    }
}
