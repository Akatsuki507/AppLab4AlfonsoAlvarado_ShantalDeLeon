package com.example.applab5alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;
public class sign_in extends AppCompatActivity {

    EditText nombre, cedula ,correo, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.Inicializar();
    }
    private void Inicializar(){
        nombre=(EditText)findViewById(R.id.nombre_registro);
        cedula=(EditText)findViewById(R.id.cedula_registro);
        correo=(EditText)findViewById(R.id.correo_registro);
        contraseña=(EditText)findViewById(R.id.contraseña_registro);
    }

    public void Registro(View view) {
        if(Validar()) {
            Toast.makeText(this, "Registro Completo", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(this, MainActivity.class);
            startActivity(login);
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
