package com.example.applab4alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;


import java.io.OutputStreamWriter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class welcome extends AppCompatActivity {
    ArrayList<user> users;
    ArrayList<user> users_archive;


    user usuario;


    public welcome() throws JSONException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Inicializar();


    }


    private void Inicializar(){


    }


    public void loadData(){
        SharedPreferences sharepreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharepreferences.getString("current_user", null);
        Type type = new TypeToken<user>(){}.getType();
        usuario = gson.fromJson(json, type);
        TextView tvnombre = (TextView)findViewById(R.id.Nombre);
        tvnombre.setText(usuario.nombre);
        TextView tvcorreo = (TextView)findViewById(R.id.Correo);
        tvcorreo.setText(usuario.email );
        TextView tvcedula = (TextView)findViewById(R.id.identity);
        tvcedula.setText(usuario.cedula );
        TextView tvrol = (TextView)findViewById(R.id.Rol);
        tvrol.setText(usuario.rol );




    }




}

