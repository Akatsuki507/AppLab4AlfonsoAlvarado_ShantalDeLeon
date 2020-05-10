package com.example.applab4alfonsoalvarado_shantaldeleon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login_activity extends AppCompatActivity {

    private EditText User,pass;
    private Button Btn,Btn2;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        User = (EditText)findViewById(R.id.Usuario);
        pass = (EditText)findViewById(R.id.Contraseña);
        Btn = (Button)findViewById(R.id.Boton);
        Btn2 =(Button)findViewById(R.id.Registrar);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USR = User.getText().toString();
                String PASS = pass.getText().toString();
                if (USR.equals("nombre@utp.ac.pa") && PASS.equals("12345")){

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
}
