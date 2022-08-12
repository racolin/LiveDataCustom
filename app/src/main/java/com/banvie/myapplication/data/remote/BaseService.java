package com.banvie.myapplication.data.remote;

import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public interface BaseService {
    Moshi moshi = new Moshi.Builder().build();
    OkHttpClient okhttp = new OkHttpClient.Builder()
            .addInterceptor(new MyInterceptor())
            .build();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okhttp)
            .build();
}
