package com.example.marvelwiki;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView name;
    ImageView characterImage;

    CharacterAdapter.OnCharacterListener onCharacterListener;

    public CharacterViewHolder(@NonNull View itemView, CharacterAdapter.OnCharacterListener onCharacterListener) {
        super(itemView);

        name = itemView.findViewById(R.id.tvName);
        characterImage = itemView.findViewById(R.id.ivCharacter);

        this.onCharacterListener = onCharacterListener;
        //Click al objeto completo
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onCharacterListener.onCharacterClick(getAdapterPosition());
    }
}
