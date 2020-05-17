package com.example.applab4alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Console;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class sign_in extends AppCompatActivity {
    ArrayList<user> users;
    EditText nombre, cedula ,correo, contraseña;
    Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Btn = (Button)findViewById(R.id.Boton);
        Btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Registro();
            }
        });
        this.Inicializar();
    }
    private void Inicializar(){
        nombre=(EditText)findViewById(R.id.nombre_registro);
        cedula=(EditText)findViewById(R.id.cedula_registro);
        correo=(EditText)findViewById(R.id.correo_registro);
        contraseña=(EditText)findViewById(R.id.contraseña_registro);
    }

    public void Registro() {
        if(Validar()) {
            users = new ArrayList<>();

            String Nombre = nombre.getText().toString();
            String Cedula = cedula.getText().toString();
            String Correo = correo.getText().toString();
            String Contraseña = contraseña.getText().toString();

            loadData();
            user nuevo_usuario = new user(Correo,Contraseña,Nombre,Cedula, "usuario");
            users.add(nuevo_usuario);
            saveData();
            Toast.makeText(this, "Registro Completo", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(this, Login_activity.class);
            startActivity(login);
        }
    }

    private void saveData(){
        SharedPreferences sharepreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        editor.putString("usuarios", json);
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
        for (int counter = 0; counter < users.size(); counter++) {
            Log.i("USUARIO", users.get(counter).email);
        }
    }

    public boolean Validar() {

        boolean retorno = true;

        Pattern pattern = Patterns.EMAIL_ADDRESS;


        String Nombre = nombre.getText().toString();
        String Cedula = cedula.getText().toString();
        String Correo = correo.getText().toString();
        String Contraseña = contraseña.getText().toString();

        boolean email= pattern.matcher(Correo).matches();

        if (Nombre.isEmpty()) {
            nombre.setError("Falta Nombre");
            retorno = false;
        }
        if (Cedula.isEmpty()) {
            cedula.setError("Este Campo es requerido, Ingresar Nombre");
            retorno = false;
        }
        if (Correo.isEmpty()) {
            correo.setError(" Este Campo es requerido, Ingresar Correo");
            retorno = false;
        }
        if (Contraseña.isEmpty()) {
            contraseña.setError(" Este Campo es requerido, Ingresar  Contraseña");
            retorno = false;
        }

        if(!email && Correo != ""){
            correo.setError(" Este Correo es  inválido");
            retorno = false;
        }
        return retorno;
    }
}
