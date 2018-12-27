package com.example.erick.cardview.models;

public class Movie {

    public String name;
    public int poster;

    public Movie(){

    }

    public Movie(String name, int post){
        this.name = name;
        this.poster = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
