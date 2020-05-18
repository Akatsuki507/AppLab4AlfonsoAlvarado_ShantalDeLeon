package com.example.applab4alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class welcome extends AppCompatActivity {
    user usuario;
    Button Btn3;

    public welcome() throws JSONException {
    }

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
        TextView tvnombre = (TextView)findViewById(R.id.Nombre);
        tvnombre.setText(usuario.nombre);
        TextView tvcorreo = (TextView)findViewById(R.id.Correo);
        tvcorreo.setText(usuario.email );
        TextView tvcedula = (TextView)findViewById(R.id.identity);
        tvcedula.setText(usuario.cedula );
        TextView tvrol = (TextView)findViewById(R.id.Rol);
        tvrol.setText(usuario.rol );
        if(usuario.rol.equals("Usuario Administrador")){
            Btn3 =(Button)findViewById(R.id.Registrar);
            Btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),sign_in.class);
                    startActivity(i);
                }
            });
        }



        Log.i("USUARIO", "USUARIO: " + usuario + "EMAIL: " + usuario.cedula + "----" + "NOMBRE: " + usuario.nombre + usuario.pass + usuario.rol);

    }




}

