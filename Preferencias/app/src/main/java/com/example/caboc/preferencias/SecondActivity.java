package com.example.caboc.preferencias;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        texto = (TextView) findViewById(R.id.txtBienvenida);

        // necesitamos el objeto de las preferencias y para saber cual es usamos la static del mainActivity con la ruta, de manera privada
        SharedPreferences mySharedPreferences = getSharedPreferences(MainActivity.PREFERENCIAS, Activity.MODE_PRIVATE);

        // recuperamos los campos introduciendo la clave, el , "" da igual no lo usa
        String nombre = mySharedPreferences.getString("nombre","");
        String fNacimiento = mySharedPreferences.getString("fNacimiento","");
        String dni = mySharedPreferences.getString("dni","");
        String sexo = mySharedPreferences.getString("sexo","");

        String mensaje = nombre + " con sexo " + sexo + " y dni " + dni +  " y fecha de nacimiento " + fNacimiento;

        texto.setText(mensaje);
    }
}
