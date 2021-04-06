package com.epam.courses.composition;

import java.io.Serializable;

public abstract class Music implements Serializable {
    private String singer;
    private String name;
    private Genre genre;
    private Double duration;

    public Music(String singer, String name, Genre genre, Double duration) {
        this.singer = singer;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public abstract void display(int i);
}
