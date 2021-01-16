package com.aoinc.wknd3_hw_bookseller.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aoinc.wknd3_hw_bookseller.model.Book;
import com.aoinc.wknd3_hw_bookseller.network.BookGetterRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<List<Book>> bookRequestResults = new MutableLiveData<>();
    public MutableLiveData<Integer> progressBarVisibility = new MutableLiveData<>();

    public LiveData<List<Book>> getBookList(){
        new Thread(){
            @Override
            public void run() {
                super.run();

                progressBarVisibility.postValue(View.VISIBLE);
                BookGetterRetrofit.getBookGetterRetrofit().getBooks()
                        .enqueue(new Callback<List<Book>>() {
                            @Override
                            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                                if (response != null && !response.body().isEmpty()) {
                                    bookRequestResults.postValue(response.body());
                                }
                                else {
                                    Log.d("TAG_X", "not error, but no data");
                                }

                                progressBarVisibility.postValue(View.INVISIBLE);
                            }

                            @Override
                            public void onFailure(Call<List<Book>> call, Throwable t) {
                                Log.d("TAG_X", t.getMessage());
                                progressBarVisibility.postValue(View.INVISIBLE);
                            }
                        });
            }
        }.start();

        return bookRequestResults;
    }
}
