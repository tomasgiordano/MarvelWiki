package com.example.marvelwiki;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.biometrics.BiometricManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.marvelwiki.Configurations.Config;
import com.example.marvelwiki.Entities.MarvelCharacter;
import com.example.marvelwiki.HTTPConn.HTTPExecute;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CharacterAdapter.OnCharacterListener,Handler.Callback {

    public Config _config;
    public Handler handler;
    MyApplication myApplication;
    public LinearLayout search;
    public ImageView ivMain;
    public LinearLayout editTextLayout;
    public EditText editText;
    public String searchText = "";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.my_toolbar));
        myApplication = (MyApplication) this.getApplication();
        _config = new Config();

        ivMain = findViewById(R.id.mainImage);
        editTextLayout = findViewById(R.id.layoutEditText);
        editText = findViewById(R.id.edtSearch);

        search = findViewById(R.id.search);

        handler = new Handler(this);

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(editTextLayout.getVisibility() == View.GONE) {
                    editTextLayout.setVisibility(View.VISIBLE);
                    editText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editTextLayout.setVisibility(View.GONE);
                } } });

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                //if (s != null && s.length() > 0) {
                    //final char lastKey = s.charAt(s.length()-1);
                    //searchText = searchText + String.valueOf(lastKey);
                    searchText = editText.getText().toString();
                    HTTPExecute myThread = new HTTPExecute(handler,_config,searchText);
                    myThread.start();
                //}
            }

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count,
                                          final int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(final Editable s) {
                // TODO Auto-generated method stub
            }
        });

        HTTPExecute myThread = new HTTPExecute(handler,_config,searchText);
        myThread.start();
    }

    @Override
    public void onCharacterClick(int position) {

    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {

        //if(myApplication.getCharacterList()!=null || !myApplication.getCharacterList().isEmpty())
        //{
            ArrayList<MarvelCharacter> lista = (ArrayList<MarvelCharacter>)  msg.obj;

            //if(myApplication.getCharacterList().isEmpty())
            myApplication.setCharacterList(lista);

            CharacterAdapter productosAdapter = new CharacterAdapter(myApplication.getCharacterList(),this,handler,MainActivity.this);
            RecyclerView rv = super.findViewById(R.id.rv);
            rv.setAdapter(productosAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            rv.setLayoutManager(linearLayoutManager);
        //}
        return false;
    }
}