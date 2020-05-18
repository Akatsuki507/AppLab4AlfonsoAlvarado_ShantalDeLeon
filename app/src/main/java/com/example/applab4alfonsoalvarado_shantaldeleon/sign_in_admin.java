
package com.example.applab4alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.regex.Pattern;



public class sign_in_admin extends AppCompatActivity {
    ArrayList<user> users;
    ArrayList<user> users_archive;
    EditText nombre, cedula ,correo, contraseña;
    Button Btn, Btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_admin);
        loadArchiveData();
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
            boolean origen = true;

            loadArchiveData();
            user nuevo_usuario = new user(Correo,Contraseña,Nombre,Cedula, "usuario", origen);
            users_archive.add(nuevo_usuario);
            saveArchiveData();
            Toast.makeText(this, "Registro Completo", Toast.LENGTH_SHORT).show();
            Intent welcome = new Intent(this, welcome.class);
            startActivity(welcome);
        }
    }

    public void saveArchiveData(){
        try
        {
            Gson gson = new Gson();
            String json = gson.toJson(users_archive);
            OutputStreamWriter fout = new OutputStreamWriter(
                    openFileOutput("usuarios_archive.txt", Context.MODE_PRIVATE));
            fout.write(json);
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
    }

    public void loadArchiveData(){
        Gson gson = new Gson();
        try
        {
            BufferedReader fin = new BufferedReader( new InputStreamReader(
                    openFileInput("usuarios_archive.txt")));
            String json = fin.readLine();
            Type type = new TypeToken<ArrayList<user>>(){}.getType();
            users_archive = gson.fromJson(json, type);
            fin.close();
            if(users_archive == null){
                users_archive = new ArrayList<>();
            }
            Log.e("USUARIOS", "==============================================LISTA DE USUARIOS DE ARCHIVO====================================================");
            Log.e("USUARIOS", "CANTIDAD DE USUARIOS: " + users_archive.size());
            for (int counter = 0; counter < users_archive.size(); counter++) {
                Log.e("USUARIOS", "| EMAIL: " + users_archive.get(counter).email + "| NOMBRE: " + users_archive.get(counter).nombre + "| ROL: " + users_archive.get(counter).rol);
            }
            Log.e("USUARIOS", "==============================================LISTA DE USUARIOS DE ARCHIVO====================================================");
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
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
