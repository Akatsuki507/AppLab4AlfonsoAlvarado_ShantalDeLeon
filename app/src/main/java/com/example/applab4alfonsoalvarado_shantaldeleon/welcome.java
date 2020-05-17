package com.example.applab4alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class welcome extends AppCompatActivity {
    user usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        loadData();
    }

    public void loadData(){
        SharedPreferences sharepreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharepreferences.getString("current_user", null);
        Type type = new TypeToken<user>(){}.getType();
        usuario = gson.fromJson(json, type);

        Log.i("USUARIO", "USUARIO: " + usuario + "EMAIL: " + usuario.email + "----" + "NOMBRE: " + usuario.nombre);

    }
}

