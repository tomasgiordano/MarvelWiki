package com.example.marvelwiki;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelwiki.Entities.MarvelCharacter;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder>{

    List<MarvelCharacter> characters;
    private CharacterAdapter.OnCharacterListener onCharacterListener;
    Handler handler;
    Context context;

    public CharacterAdapter(
            List<MarvelCharacter> characters,
            OnCharacterListener onCharacterListener,
            Handler handler,
            Context context){

        this.characters = characters;
        this.onCharacterListener = onCharacterListener;
        this.handler = handler;
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character,parent,false);
        return new CharacterViewHolder(v,onCharacterListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {

        MarvelCharacter p = this.characters.get(position);

        holder.name.setText(p.getName());
        new FetchImage(p.getImageUrl(),handler,context,holder).start();

        //holder.characterImage.setImageBitmap();
        //Picasso.get().load(p.getImageUrl()).into(holder.characterImage);
    }

    @Override
    public int getItemCount() {
        return this.characters.size();
    }

    public interface OnCharacterListener{
        void onCharacterClick(int position);
    }
}
