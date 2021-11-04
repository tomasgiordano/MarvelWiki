package com.example.marvelwiki.Configurations;

public class Config implements IConfig{

    private String PublicKey = "c9e912b5d198a5629436ff17f8826d41";
    private String PrivateKey = "da37b4344d3287e1b403530ebad5b567ed985f23";
    private int TimeStamp = 1;
    private String Hash = "36d418c4e427fc15b039134ae399a4e1";


    public String BuildUrlWithoutParameters(String Url) {
        return Url+"?apikey="+PublicKey+"&hash="+Hash+"&ts="+TimeStamp;
    }

    public String BuildUrlWithParameters(String Url) {
        return Url+"&apikey="+PublicKey+"&hash="+Hash+"&ts="+TimeStamp;
    }

    public String GetListCharacters(String text){
        if(text.isEmpty())
            return BuildUrlWithoutParameters("https://gateway.marvel.com:443/v1/public/characters");
        else
            return BuildUrlWithParameters("https://gateway.marvel.com:443/v1/public/characters"+"?nameStartsWith="+text);
    }
}
