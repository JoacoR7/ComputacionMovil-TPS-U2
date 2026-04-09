package com.example.menucontextual;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    Button agregarBtn;
    EditText agregarTxt;
    ArrayList<String> listado;
    ArrayAdapter<String> adapter;
    int listPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listado = new ArrayList<>();
        lista = findViewById(R.id.lista);
        agregarBtn = findViewById(R.id.agregarBtn);
        agregarTxt = findViewById(R.id.agregarTxt);

        registerForContextMenu(lista);

        agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agregarTxt.getText().toString().isEmpty()) {
                    agregarTxt.setError("");
                } else {
                    if(agregarBtn.getText().toString().equals("Agregar")) {
                        listado.add(agregarTxt.getText().toString());
                        llenarLista();
                        agregarTxt.setText("");
                        agregarTxt.requestFocus();
                    } else {
                        listado.set(listPosition, agregarTxt.getText().toString());
                        llenarLista();
                        agregarTxt.setText("");
                        agregarTxt.requestFocus();
                        agregarBtn.setText("Agregar");
                    }
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.lista) {
            listPosition = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            menu.setHeaderTitle(lista.getAdapter().getItem(listPosition).toString());
            this.getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.opcionEditar) {
            agregarTxt.setText(adapter.getItem(listPosition));
            agregarTxt.setSelection(agregarTxt.getText().length());
            agregarBtn.setText("Modificar");
        }
        if(item.getItemId() == R.id.opcionEliminar) {
            msg("¿Desea eliminar este registro?");
        }
        return super.onContextItemSelected(item);
    }

    private void msg(String message) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Menú contextual");
        dialogo.setMessage(message);
        dialogo.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listado.remove(listPosition);
                llenarLista();
                Toast.makeText(MainActivity.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
            }
        });
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogo.show();
    }

    public void llenarLista() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listado);
        lista.setAdapter(adapter);
    }
}