package com.example.marvelwiki.Entities;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class MarvelCharacter {
    private int Id;
    private String Name;
    private String ImageUrl;
    private String Detail;
    private ArrayList<Url> Urls;

    public MarvelCharacter(){
        Urls = new ArrayList<>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public int getId() { return this.Id; }

    public void setId(int id){
        this.Id = id;
    }

    public String getDetail(){ return this.Detail; }

    public void setDetail(String detail){ this.Detail = detail; }

    public ArrayList<com.example.marvelwiki.Entities.Url> getUrl() {
        return Urls;
    }

    public void setUrl(ArrayList<com.example.marvelwiki.Entities.Url> url) {
        Urls = url;
    }

    public void addUrl(Url url){
        this.Urls.add(url);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDetailUrl(){
        Url auxUrl = new Url();
        auxUrl = this.Urls.stream().filter((n) -> n.getType().equals("detail")).findFirst().orElse(new Url());
        return auxUrl.getUrl();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getComicLinkUrl(){
        Url auxUrl = new Url();
        auxUrl = this.Urls.stream().filter((n) -> n.getType().equals("comiclink")).findFirst().orElse(new Url());
        return auxUrl.getUrl();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getWikiUrl(){
        Url auxUrl = new Url();
        auxUrl = this.Urls.stream().filter((n) -> n.getType().equals("wiki")).findFirst().orElse(new Url());
        return auxUrl.getUrl();
    }
}
