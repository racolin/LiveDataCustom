package com.banvie.myapplication.data.remote;

import okhttp3.Headers;

public class Config {
    public static final String URL = "https://genius.p.rapidapi.com/";
    public static final Headers headers = new Headers.Builder()
            .add("X-RapidAPI-Key", "c2ea1791f4msh268be2d94389479p199095jsn990208cd6ace")
            .add("X-RapidAPI-Host", "genius.p.rapidapi.com")
            .build();
}
