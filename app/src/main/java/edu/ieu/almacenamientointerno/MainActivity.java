package edu.ieu.almacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

    private void abrir() {
    }

    private void guardar() {
    }
}