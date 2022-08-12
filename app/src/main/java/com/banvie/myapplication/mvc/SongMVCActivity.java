package com.banvie.myapplication.mvc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.banvie.myapplication.R;
import com.banvie.myapplication.data.model.Song;
import com.banvie.myapplication.data.remote.song.SongParam;
import com.banvie.myapplication.data.remote.song.SongRemoteDataSource;
import com.banvie.myapplication.data.remote.song.SongRepository;
import com.banvie.myapplication.data.remote.SongService;

import java.util.ArrayList;
import java.util.List;

public class SongMVCActivity extends AppCompatActivity {

    RecyclerView rvMVC;
    SongMVCAdapter adapter;
    SongController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        SongRemoteDataSource dataSource = new SongRemoteDataSource(SongService.songService);
        SongRepository repository = new SongRepository(dataSource);
        controller = new SongController(this, repository);

        rvMVC = findViewById(R.id.rvMVC);
        adapter = new SongMVCAdapter(new ArrayList<>());
        rvMVC.setAdapter(adapter);
        rvMVC.setLayoutManager(new LinearLayoutManager(this));

        SongParam param = new SongParam();
        param.per_page = 50;
        param.page = 5;
        controller.setUpListSongs(param);
    }

    public void setUpListSongs(List<Song> songs) {
        adapter.setSongs(songs);
    }

    public void loadMoreListSongs(List<Song> songs) {
        adapter.addSongs(songs);
    }
}