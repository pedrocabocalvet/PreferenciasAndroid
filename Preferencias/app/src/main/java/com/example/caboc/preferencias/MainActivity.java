package com.example.caboc.preferencias;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // esta es la constante que asignaremos para las preferencias, es como la ruta, para encontrar el fichero preferencias, la hago static para poder cogerla desde el subbActivity
    public static final String PREFERENCIAS="mis preferencias";

    EditText editTextNombre;
    EditText editTextDNI;
    EditText editTextFNacimiento;
    RadioButton rbHombre;
    RadioButton rbMujer;

    String sexo;    // la usaremos para guardar el campo sexo ya que es un radiobutton

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sexo = "";

        editTextDNI = (EditText) findViewById(R.id.editTextDNI);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextFNacimiento = (EditText) findViewById(R.id.editTextNacimiento);

        rbHombre = (RadioButton) findViewById(R.id.radioButtonHombre);
        rbMujer = (RadioButton) findViewById(R.id.radioButtonMujer);

        // estos dos listeners son para saber si ha clickado en hombre o mujer y guardar un String segun sea el caso
        rbHombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexo = "Hombre";
            }
        });

        rbMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexo = "Mujer";
            }
        });


        boton = (Button) findViewById(R.id.buttonEnviar);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // si estan todos los campos rellenados podra seguir con el programa
                if(!editTextDNI.getText().toString().equals("") && !editTextFNacimiento.getText().toString().equals("") && !editTextNombre.getText().toString().equals("") && !sexo.equals("")) {

                    // aqui nos creamos el fichero preferencias con la constante creada como atributo y de forma privada
                    SharedPreferences mySharedPreferences = getSharedPreferences(PREFERENCIAS, Activity.MODE_PRIVATE);
                    // necesitamos un editor para poder escribir cosas
                    SharedPreferences.Editor editor = mySharedPreferences.edit();

                    // guardamos en el editor los datos que queremos conservar de modo clave valor
                    editor.putString("nombre",editTextNombre.getText().toString());
                    editor.putString("sexo",sexo);
                    editor.putString("fNacimiento",editTextFNacimiento.getText().toString());
                    editor.putString("dni",editTextDNI.getText().toString());

                    // aqui lo guardamos
                    editor.commit();

                    // este intent nos lleva al subactivity donde recuperaremos los datos de las preferencias
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(intent);
                }else{
                    // si no se rellenan todos los campos saldra este toast de error
                    Toast.makeText(MainActivity.this, "Te faltan campos por completar", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
