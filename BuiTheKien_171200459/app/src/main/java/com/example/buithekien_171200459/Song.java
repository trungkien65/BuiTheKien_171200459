package com.example.buithekien_171200459;

public class Song {
    private int id;
    private String name_song;
    private String name_singer;
    private String time;

    public Song() {
    }

    public Song(int id, String name_song, String name_singer, String time) {
        this.id = id;
        this.name_song = name_song;
        this.name_singer = name_singer;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public Song setId(int id) {
        this.id = id;
        return this;
    }

    public String getName_song() {
        return name_song;
    }

    public Song setName_song(String name_song) {
        this.name_song = name_song;
        return this;
    }

    public String getName_singer() {
        return name_singer;
    }

    public Song setName_singer(String name_singer) {
        this.name_singer = name_singer;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Song setTime(String time) {
        this.time = time;
        return this;
    }
}
