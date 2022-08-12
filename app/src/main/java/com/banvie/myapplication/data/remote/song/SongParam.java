package com.banvie.myapplication.data.remote.song;

import java.util.HashMap;
import java.util.Map;

public class SongParam {
    public Integer per_page;
    public Integer page;

    public SongParam() { }
    public SongParam(Integer per_page, Integer page) {
        this.page = page;
        this.per_page = per_page;
    }

    public Map<String, Integer> getQueryMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("per_page", per_page);
        map.put("page", page);
        return map;
    }
}
