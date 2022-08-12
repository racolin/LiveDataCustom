package com.banvie.myapplication.data.model;

import com.squareup.moshi.Json;

public class Song {
    @Json(name = "id")
    public Long id;
    @Json(name = "artist_names")
    public String artist;
    @Json(name = "full_title")
    public String title;
    @Json(name = "header_image_thumbnail_url")
    public String thumbnailUrl;
    @Json(name = "header_image_url")
    public String imageUrl;
    @Json(name = "release_date_for_display")
    public String release;
}
