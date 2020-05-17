package com.example.applab4alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class Login_activity extends AppCompatActivity {
    ArrayList<user> users;
    private EditText User,pass;
    private Button Btn,Btn2;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        loadData();
        User = (EditText)findViewById(R.id.Usuario);
        pass = (EditText)findViewById(R.id.Contraseña);
        Btn = (Button)findViewById(R.id.Boton);
        Btn2 =(Button)findViewById(R.id.Registrar);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USR = User.getText().toString();
                String PASS = pass.getText().toString();
                if (verificar(USR, PASS)){

                    Intent i = new Intent(getApplicationContext(),welcome.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña Erronea",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),sign_in.class);
                startActivity(i);
            }
        });

    }
    public void loadData(){
        SharedPreferences sharepreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharepreferences.getString("usuarios", null);
        Type type = new TypeToken<ArrayList<user>>(){}.getType();
        users = gson.fromJson(json, type);

        if(users == null){
            users = new ArrayList<>();
        }
        for (int counter = 0; counter < users.size(); counter++) {
            Log.i("USUARIO", users.get(counter).email);
        }
    }

    private boolean verificar(String email, String pass){
        for (int counter = 0; counter < users.size(); counter++) {
            if((users.get(counter).email.equals(email)) && (users.get(counter).pass.equals(pass))){
                return true;
            }
        }

        return false;
    }
}
