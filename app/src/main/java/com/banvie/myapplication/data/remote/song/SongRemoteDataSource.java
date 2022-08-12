package com.banvie.myapplication.data.remote.song;

import com.banvie.myapplication.data.remote.SongService;

import io.reactivex.rxjava3.core.Observable;

public class SongRemoteDataSource {
    SongService songService;

    public SongRemoteDataSource(SongService songService) {
        this.songService = songService;
    }

    public Observable<SongWrapper> getSongs(SongParam param) {
        return songService.getSongs(param.getQueryMap());
    }
}
