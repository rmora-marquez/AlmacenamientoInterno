package edu.ieu.almacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText etNombre;
    private EditText etContenido;
    private Button btnGuardar;
    private Button btnAbrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etContenido = findViewById(R.id.et_contenido);
        etNombre = findViewById(R.id.et_nombre);

        btnAbrir = findViewById(R.id.btn_abrir);
        btnGuardar = findViewById(R.id.btn_guardar);

        btnGuardar.setOnClickListener( view -> {
            guardar();
        });
        btnAbrir.setOnClickListener( view -> {
            abrir();
        });
    }

    private void guardar() {
        try{
            String nombre = etNombre.getText().toString()
                    .replace("/","-");
            if(nombre.equals("")){
                Toast.makeText(this, "Nombre no puede ser vacio",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            OutputStreamWriter writer = new OutputStreamWriter(
                    openFileOutput(nombre, Activity.MODE_PRIVATE));
            writer.write(etContenido.getText().toString());
            writer.flush();
            writer.close();
        }catch(IOException ex){
            ex.printStackTrace();
            Toast.makeText(this, "No fue posible guardar el archivo",
                    Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Los datos fueron guardados",
                Toast.LENGTH_SHORT).show();
    }

    private void abrir() {
        String nombre = etNombre.getText().toString()
                .replace("/","-");
        String[] listArchivos = fileList();
        Integer posicion = null;
        for(int i = 0; i < listArchivos.length; i++){
            if(listArchivos[i].equals(nombre)){
                posicion = i;
                break;
            }
        }
        if(posicion == null){
            Toast.makeText(this, "No se encontro el archivo con nombre " +
                    nombre, Toast.LENGTH_SHORT).show();
        }else{
            try{
                InputStreamReader reader = new InputStreamReader(
                        openFileInput(nombre));
                BufferedReader br = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();
                String line;
                while ( (line = br.readLine() ) != null ){
                    builder.append(line);
                    builder.append("\n");
                }
                br.close();
                reader.close();
                etContenido.setText( builder.toString() );
            }catch (IOException ex){
                ex.printStackTrace();
                Toast.makeText(this, "No fue posible leer el archivo " +
                        nombre, Toast.LENGTH_SHORT).show();
            }
        }
    }
}