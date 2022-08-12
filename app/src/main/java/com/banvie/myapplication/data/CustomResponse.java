package com.banvie.myapplication.data;

import retrofit2.Response;

public class CustomResponse<T> {
    public <T> CustomResponse(Response<T> response) {
    }

    public CustomResponse(Throwable t) {
    }
}
