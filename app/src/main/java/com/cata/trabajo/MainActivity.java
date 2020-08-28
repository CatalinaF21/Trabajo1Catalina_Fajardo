package com.cata.trabajo;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    //definiendo variables
        private int año;
        private int mes;
        private int dia;
        private EditText campoNombre;
        private EditText campoFecha;
        private EditText campoTelefono;
        private EditText campoEmail;
        private EditText campoDescripcion;
        private static final int TIPO_DIALOGO = 0;
        private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        siguiente();

        //asignando variables
        campoNombre         = (EditText) findViewById(R.id.etNombre);
        campoFecha          = (EditText) findViewById(R.id.etFechaNacimiento);
        campoTelefono       = (EditText) findViewById(R.id.etTelefono);
        campoEmail          = (EditText) findViewById(R.id.etEmail);
        campoDescripcion    = (EditText) findViewById(R.id.etDescripcion);
        //funcion de uso de calendario y selecciion de datos seleccionados en la composicion de fecha, al mes se suma 1; aplicación
        //por defecto enumera los meses de 0 a 11, siendo 0 enero y 11 diciembre
        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH)+1;
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                año=i;
                mes=i1+1;
                dia=i2;
                mostrarFecha();
            }
        };

    }
//Función de apertura del calendario al clic sobre el campo de fecha
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new DatePickerDialog(this,oyenteSelectorFecha,año,mes,dia);
        }
        return null;
  }

    public void mostrarcalendario(View control){
        showDialog(TIPO_DIALOGO);
    }
    public void mostrarFecha(){
        campoFecha.setText(año + "/"+ mes + "/"+ dia);
    }
//paso de información de activity principal a pantalla de confirmación, defincion de parametros a pasar
    public  void siguiente () {
        Button siguiente    = (Button) findViewById(R.id.btsiguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra(getResources().getString(R.string.pnombre), campoNombre.getText().toString());
                intent.putExtra(getResources().getString(R.string.pfechanacimiento), campoFecha.getText().toString());
                intent.putExtra(getResources().getString(R.string.ptelefono), campoTelefono.getText().toString());
                intent.putExtra(getResources().getString(R.string.pemail), campoEmail.getText().toString());
                intent.putExtra(getResources().getString(R.string.pdescripcion), campoDescripcion.getText().toString());
                startActivity(intent);
                finish();
            }
        });
//recibiendo parametros de actividad 2 para editar precargados en actividad principal
    Bundle parametros = getIntent().getExtras();
    if(parametros!=null){

        //Obtencion de parametros a entregados del activity 2
        String ednombre         = parametros.getString(getResources().getString(R.string.penombre));
        String edFecha          = parametros.getString(getResources().getString(R.string.pefechanacimiento));
        String edTelefono       = parametros.getString(getResources().getString(R.string.petelefono));
        String edEmail          = parametros.getString(getResources().getString(R.string.peemail));
        String edDescripcion    = parametros.getString(getResources().getString(R.string.pedescripcion));

        //enlace del view a la variable
        EditText tvNombre       = (EditText) findViewById(R.id.etNombre);
        EditText tvFecha        = (EditText) findViewById(R.id.etFechaNacimiento);
        EditText tvTelefono     = (EditText) findViewById(R.id.etTelefono);
        EditText tvEmail        = (EditText) findViewById(R.id.etEmail);
        EditText tvDescripcion  = (EditText) findViewById(R.id.etDescripcion);

        //asignación de datos recibidos a los edit text
        tvNombre.setText(ednombre);
        tvFecha.setText(edFecha);
        tvTelefono.setText(edTelefono);
        tvEmail.setText(edEmail);
        tvDescripcion.setText(edDescripcion);

    }
    }
}
