package com.example.marvelwiki.Entities;

public class MarvelCharacter {
    private String Name;
    private String ImageUrl;

    public MarvelCharacter(){ }

    public MarvelCharacter(String name, String imageUrl) {
        Name = name;
        ImageUrl = imageUrl;
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
}
