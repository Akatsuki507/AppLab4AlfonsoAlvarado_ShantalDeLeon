package com.example.applab4alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Login_activity extends AppCompatActivity {
    ArrayList<user> users;
    private EditText User,pass;
    private Button Btn,Btn2;
    Spinner spinner2;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        loadData();
        User = (EditText)findViewById(R.id.Usuario);
        pass = (EditText)findViewById(R.id.Contraseña);
         final Spinner spinners = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                loadSpinnerRoles2());
        spinners.setAdapter(adapterList);

        Btn = (Button)findViewById(R.id.Boton);
        Btn2 =(Button)findViewById(R.id.Registrar);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USR = User.getText().toString();
                String PASS = pass.getText().toString();
                String ROL = spinners.getSelectedItem().toString();
                if (verificar(USR, PASS,ROL)){
                    if(ROL.equals("Usuario Corriente")) {
                        Intent i = new Intent(getApplicationContext(), welcome.class);
                        startActivity(i);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña Erronea",Toast.LENGTH_SHORT).show(); }
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
    public List<String> loadSpinnerRoles2() {
        List<String> Roles = new ArrayList<String>();
        Roles.add("Seleccione un Rol");
        Roles.add("Usuario Corriente");
        Roles.add("Usuario Administrador");
        return Roles;
    }

    private void saveCurrent_user(user usuario){
        SharedPreferences sharepreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        editor.putString("current_user", json);
        editor.apply();
        loadData();

        Log.i("ADVERTENCIA", users.get(0).email);
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
        Log.e("USUARIOS", "==============================================LISTA DE USUARIOS====================================================");
        Log.e("USUARIOS", "CANTIDAD DE USUARIOS: " + String.valueOf(users.size()));
        for (int counter = 0; counter < users.size(); counter++) {
            Log.e("USUARIOS", "| EMAIL: " + users.get(counter).email + "| NOMBRE: " + users.get(counter).nombre + "| ROL: " + users.get(counter).rol);
        }
        Log.e("USUARIOS", "==============================================LISTA DE USUARIOS====================================================");
    }

    private boolean verificar(String email, String pass,String rol ){
        for (int counter = 0; counter < users.size(); counter++) {
            if((users.get(counter).email.equals(email)) && (users.get(counter).pass.equals(pass)) && (users.get(counter).rol.equals(rol)) ){
                saveCurrent_user(users.get(counter));
                return true;
            }
        }

        return false;
    }
}
