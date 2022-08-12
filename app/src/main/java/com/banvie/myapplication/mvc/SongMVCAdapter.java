package com.banvie.myapplication.mvc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.banvie.myapplication.R;
import com.banvie.myapplication.data.model.Song;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SongMVCAdapter extends RecyclerView.Adapter<SongMVCAdapter.SongMVCHolder> {

    List<Song> songs;

    public SongMVCAdapter(List<Song> songs) {
        this.songs = songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs.clear();
        addSongs(songs);
    }

    public void addSongs(List<Song> songs) {
        int start = this.songs.size();
        this.songs.addAll(songs);
        int count = songs.size();
        notifyItemRangeChanged(start, count);
    }

    @NonNull
    @Override
    public SongMVCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongMVCHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull SongMVCHolder holder) {
        holder.cancelLoadImage();
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull SongMVCHolder holder) {
        holder.loadImage();
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull SongMVCHolder holder, int position) {
        holder.song = songs.get(position);
        holder.tvArtist.setText(songs.get(position).artist);
        holder.tvTitle.setText(songs.get(position).title);
        holder.tvRelease.setText(songs.get(position).release);
        holder.ibt.setOnClickListener((view) -> {
            holder.loadImage();
        });
        holder.setSong(songs.get(position));
    }

    @Override
    public int getItemCount() {
        return songs != null ? songs.size() : 0;
    }

    class SongMVCHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvArtist, tvRelease;
        ImageView ivThumbnail;
        private Song song;
        ProgressBar pgb;
        ImageButton ibt;
        boolean loading = true;
        boolean error = false;

        public SongMVCHolder(@NonNull View itemView) {
            super(itemView);
            tvArtist = itemView.findViewById(R.id.tvArtist);
            tvRelease = itemView.findViewById(R.id.tvRelease);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            pgb = itemView.findViewById(R.id.pgb);
            ibt = itemView.findViewById(R.id.ibt);
        }

        public void setSong(Song song) {
            this.song = song;
        }

        public void loadImage() {
            setError(false);
            if (song != null) {
                setLoading(true);
                pgb.setVisibility(View.VISIBLE);
                ivThumbnail.setVisibility(View.INVISIBLE);
                Picasso.get().load(song.thumbnailUrl).centerCrop().resize(80, 80).into(ivThumbnail, new Callback() {
                    @Override
                    public void onSuccess() {
                        setLoading(false);
                    }

                    @Override
                    public void onError(Exception e) {
                        setError(true);
                    }
                });
            }
        }

        public void cancelLoadImage() {
            Picasso.get().cancelRequest(ivThumbnail);
            pgb.setVisibility(View.VISIBLE);
            ivThumbnail.setVisibility(View.INVISIBLE);
            setLoading(false);
        }

        private void setError(boolean error) {
            this.error = error;
            ibt.setVisibility(error ? View.VISIBLE : View.INVISIBLE);
        }

        private void setLoading(boolean loading) {
            this.loading = loading;
            pgb.setVisibility(loading ? View.VISIBLE : View.INVISIBLE);
            ivThumbnail.setVisibility(loading ? View.INVISIBLE : View.VISIBLE);
        }

    }
}
