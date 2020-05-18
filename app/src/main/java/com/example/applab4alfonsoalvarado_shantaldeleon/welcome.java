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
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class welcome extends AppCompatActivity {
    ArrayList<user> users;
    ArrayList<user> users_archive;

    Button Btn2;
    user usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Inicializar();
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),sign_in_admin.class);
                startActivity(i);
            }
        });
        loadData();
    }

    private void Inicializar(){

        Btn2=(Button) findViewById(R.id.sign_in_admin);
    }

    public void loadData(){
        SharedPreferences sharepreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharepreferences.getString("current_user", null);
        Type type = new TypeToken<user>(){}.getType();
        usuario = gson.fromJson(json, type);

        Log.i("USUARIO", "USUARIO: " + usuario + "EMAIL: " + usuario.email + "----" + "NOMBRE: " + usuario.nombre + usuario.pass + usuario.rol);
    }


    public void verificar_rol(){
        if(usuario.rol.equals("ADMIN")){
            //que haga esa mamada
        }else{
            //No hagas esa mamada
        }
    }
}

