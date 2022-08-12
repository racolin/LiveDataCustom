package com.banvie.myapplication.data.remote;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request newRequest = request.newBuilder().headers(Config.headers).build();
        Response response = chain.proceed(newRequest);
        return response;
    }
}
