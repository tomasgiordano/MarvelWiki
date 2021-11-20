package com.example.marvelwiki;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelwiki.Entities.MarvelCharacter;
import com.squareup.picasso.Picasso;

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

        Picasso.get()
                .load(p.getImageUrl())
                .resize(300, 450)
                .centerCrop()
                .into(holder.characterImage);

        holder.name.setText(p.getName());
    }

    private boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return hasImage;
    }

    @Override
    public int getItemCount() {
        return this.characters.size();
    }

    public interface OnCharacterListener{
        void onCharacterClick(int position);
    }
}
