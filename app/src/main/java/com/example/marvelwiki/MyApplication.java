package com.example.marvelwiki;

import android.app.Application;

import com.example.marvelwiki.Entities.MarvelCharacter;

import java.util.ArrayList;
import java.util.Arrays;

public class MyApplication extends Application {
    private ArrayList<MarvelCharacter> characterList = new ArrayList<MarvelCharacter>();

    public MyApplication(){

    }

    public void setCharacterList(ArrayList<MarvelCharacter> characters)
    {
        this.characterList = characters;
    }

    public ArrayList<MarvelCharacter> getCharacterList()
    {
        return characterList;
    }
}
