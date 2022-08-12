package com.banvie.myapplication.data.remote;

import com.banvie.myapplication.data.remote.song.SongWrapper;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
public interface SongService {
    String GET_ALL_SONG = "artists/16775/songs";

    SongService songService = BaseService.retrofit.create(SongService.class);

    @GET(GET_ALL_SONG)
    Observable<SongWrapper> getSongs(@QueryMap Map<String, Integer> param);
}
