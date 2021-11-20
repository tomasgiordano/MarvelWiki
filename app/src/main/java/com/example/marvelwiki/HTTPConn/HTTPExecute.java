package com.example.marvelwiki.HTTPConn;

import android.os.Handler;
import android.os.Message;

import com.example.marvelwiki.Configurations.Config;
import com.example.marvelwiki.Entities.MarvelCharacter;
import com.example.marvelwiki.Entities.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HTTPExecute extends Thread{

    public Handler handler;
    public Config _config;
    public String search;

    public HTTPExecute(Handler handler, Config config,String search) {
        this.handler = handler;
        this._config = config;
        this.search = search;
    }

    @Override
    public void run(){
        HTTPConnection con = new  HTTPConnection();

        String json = con.getHttpResponse(_config.GetListCharacters(search));

        Message mensaje = new Message();
        try {
            mensaje.obj = getListCharacters(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.handler.sendMessage(mensaje);
    }

    private List<MarvelCharacter> getListCharacters(String json) throws JSONException {
        List<MarvelCharacter> characters = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("results");

        for(int i=0; i < jsonArray.length(); i++) {

            MarvelCharacter character = new MarvelCharacter();

            JSONObject marvelObject = jsonArray.getJSONObject(i);

            String extension = marvelObject.getJSONObject("thumbnail").getString("extension");

            character.setName(marvelObject.getString("name"));
            character.setImageUrl(_config.BuildUrlWithoutParameters(marvelObject.getJSONObject("thumbnail").getString("path")+"/portrait_fantastic"+"."+extension));
            character.setDetail(marvelObject.getString("description"));
            character.setId(marvelObject.getInt("id"));

            JSONArray jsonArrayItems = marvelObject.getJSONArray("urls");

            for(int j=0; j < jsonArrayItems.length(); j++){

                Url url = new Url();
                JSONObject urlsObject = jsonArrayItems.getJSONObject(j);

                url.setType(urlsObject.getString("type"));
                url.setUrl(urlsObject.getString("url"));

                character.addUrl(url);
            }

            characters.add(character);
        }

        return characters;
    }
}
