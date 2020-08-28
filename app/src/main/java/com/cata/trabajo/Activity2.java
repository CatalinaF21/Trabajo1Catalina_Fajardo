package com.cata.trabajo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity2 extends AppCompatActivity {
//definiendo variables
    private TextView editNombre;
    private TextView editFecha;
    private TextView editTelefono;
    private TextView editEmail;
    private TextView editDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        editar();

       //asignando variables
        editNombre          = (TextView) findViewById(R.id.tvNombre);
        editFecha           = (TextView) findViewById(R.id.tvFechaNacimiento);
        editTelefono        = (TextView)  findViewById(R.id.tvTelefono);
        editEmail           = (TextView) findViewById(R.id.tvEmail);
        editDescripcion     = (TextView) findViewById(R.id.tvDescripcion);

        //Obtencion de parametros a entregados del activity principal
        Bundle parametros           = getIntent().getExtras();
        String nombre               = parametros.getString(getResources().getString(R.string.pnombre));
        String fecha                = parametros.getString(getResources().getString(R.string.pfechanacimiento));
        String telefono             = parametros.getString(getResources().getString(R.string.ptelefono));
        String email                = parametros.getString(getResources().getString(R.string.pemail));
        String descripcion          = parametros.getString(getResources().getString(R.string.pdescripcion));

        //enlace del view a la variable
        TextView tvNombre           = (TextView) findViewById(R.id.tvNombre);
        TextView tvFechaNacimiento  = (TextView) findViewById(R.id.tvFechaNacimiento);
        TextView tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        TextView tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);

        //asignación de datos recibidos a los textview
        tvNombre.setText(nombre);
        tvFechaNacimiento.setText(fecha);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);
    }
    //accion del boton editar para regresar al activity principal y definir datos a regresar
    public  void editar (){
        Button editar = (Button) findViewById(R.id.bteditar);
        editar.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, MainActivity.class);
                intent.putExtra(getResources().getString(R.string.penombre), editNombre.getText().toString());
                intent.putExtra(getResources().getString(R.string.pefechanacimiento), editFecha.getText().toString());
                intent.putExtra(getResources().getString(R.string.petelefono), editTelefono.getText().toString());
                intent.putExtra(getResources().getString(R.string.peemail), editEmail.getText().toString());
                intent.putExtra(getResources().getString(R.string.pedescripcion), editDescripcion.getText().toString());
                startActivity(intent);
            }
        });
    }
    //Levantar el activity principal al darle felcha atras desde el 2 activity
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Activity2.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
