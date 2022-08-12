package com.banvie.myapplication.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<T> implements CallAdapter<T, LiveData<CustomResponse<T>>> {

    private final Type responseType;

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<CustomResponse<T>> adapt(Call<T> call) {
        return new LiveData<CustomResponse<T>>() {

            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onInactive() {
                super.onInactive();
            }

            @Override
            protected void onActive() {
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<T>() {
                        @Override
                        public void onResponse(Call<T> call, Response<T> response) {
                            postValue(new CustomResponse(response));
                        }

                        @Override
                        public void onFailure(Call<T> call, Throwable t) {
                            postValue(new CustomResponse<T>(t));
                        }
                    });
                }
                super.onActive();
            }
        };
    }
}
