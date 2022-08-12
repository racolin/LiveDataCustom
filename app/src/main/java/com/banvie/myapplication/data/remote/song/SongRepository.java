package com.banvie.myapplication.data.remote.song;

import io.reactivex.rxjava3.core.Observable;

public class SongRepository {
    SongRemoteDataSource dataSource;
    public SongRepository(SongRemoteDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Observable<SongWrapper> getListSongs(SongParam param) {
        return dataSource.getSongs(param);
    }
}
