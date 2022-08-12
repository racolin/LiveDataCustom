package com.banvie.myapplication.mvc;

import com.banvie.myapplication.data.model.Song;
import com.banvie.myapplication.data.remote.song.SongParam;
import com.banvie.myapplication.data.remote.song.SongRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SongController {
    SongRepository repository;
    SongMVCActivity activity;
    Disposable disposable;
    public SongController(SongMVCActivity activity, SongRepository repository) {
        this.repository = repository;
        this.activity = activity;
    }

    public void setUpListSongs(SongParam param) {
        List<Song> songs = new ArrayList<>();
        repository.getListSongs(param)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((songWrapper -> { return songWrapper.response.songs; }))
                .subscribe(getObserverSong());
        activity.setUpListSongs(songs);
    }

    public void loadMoreListSongs() {
        List<Song> songs = new ArrayList<>();

        activity.loadMoreListSongs(songs);
    }

    private Observer<List<Song>> getObserverSong() {
        return new Observer<List<Song>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull List<Song> songs) {
                activity.setUpListSongs(songs);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                disposable.dispose();
            }
        };
    }
}
