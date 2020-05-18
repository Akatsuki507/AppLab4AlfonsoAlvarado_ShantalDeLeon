package com.example.applab4alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;


import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;


public class welcome extends AppCompatActivity {
    TextView tvnombre;
    TextView tvcorreo;
    TextView tvcedula;
    TextView tvrol;

    user usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        loadData();
        Inicializar();
        welcome();


    }


    private void Inicializar(){
        tvnombre = (TextView)findViewById(R.id.Nombre);
        tvcorreo = (TextView)findViewById(R.id.Correo);
        tvcedula = (TextView)findViewById(R.id.identity);
        tvrol = (TextView)findViewById(R.id.Rol);
        tvnombre.setText(usuario.nombre);
        tvcorreo.setText(usuario.email );
        tvcedula.setText(usuario.cedula );
        tvrol.setText(usuario.rol );
    }


    public void loadData(){
        SharedPreferences sharepreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharepreferences.getString("current_user", null);
        Type type = new TypeToken<user>(){}.getType();
        usuario = gson.fromJson(json, type);



        Log.e("USUARIOS", "==============================================USUARIO ACTUAL====================================================");
        Log.e("USUARIO", "| USUARIO: " + usuario + "| EMAIL: " + usuario.email + " | NOMBRE: " + usuario.nombre + "| PASS: " + usuario.pass + "| ROL: " +  usuario.rol);
        Log.e("USUARIOS", "==============================================USUARIO ACTUAL====================================================");
    }

    public void welcome(){
        if(usuario.rol.equals("Usuario Administrador")){
            Toast toast1 =Toast.makeText(getApplicationContext(),
                    "BIENVIENIDO ADMINISTRADOR", Toast.LENGTH_SHORT);
            toast1.show();
        }else{
            Toast toast1 =Toast.makeText(getApplicationContext(),
                    "BIENVIENIDO USUARIO MORTAL", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }




}

