package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.controllers.LibroBD;
import com.example.myapplication.controllers.SelectListener;
import com.example.myapplication.models.Libro;

import java.util.ArrayList;
import java.util.List;

public class ListarLibrosActivity extends AppCompatActivity implements SelectListener {

    ListView listView;
    ArrayList<String> nombresLibros;
    ArrayList<Integer> idsLibros;
    LibroBD libroBD;
    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar_libros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
    }

    private void init(){
        context = getApplicationContext();
        libroBD = new LibroBD(context, "librosBD.db", null, 1);
        listView = findViewById(R.id.listaLibros);
        llenarListView();
    }

    private void llenarListView() {
        nombresLibros = new ArrayList<String>();
        idsLibros = new ArrayList<Integer>();

        List<Libro> listaLibros = libroBD.lista();

        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libro = listaLibros.get(i);
            nombresLibros.add(libro.getTitulo());
            idsLibros.add(libro.getId());
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1,
                        nombresLibros);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Libro libro = libroBD.elemento(idsLibros.get(i));
                Bundle bolsa = new Bundle();
                bolsa.putInt("id", libro.getId());
                bolsa.putString("titulo", libro.getTitulo());
                bolsa.putString("subtitulo", libro.getSubt());
                bolsa.putString("autor", libro.getAutor());
                bolsa.putString("isbn", libro.getIsbn());
                bolsa.putInt("anio_publicacion", libro.getAnioPublicacion());
                bolsa.putDouble("precio", libro.getPrecio());

                Intent intent = new Intent(context, GestionarLibroActivity.class);
                intent.putExtras(bolsa);
                startActivity(intent);
            }
        }
        );
    }

    @Override
    protected void onResume(){
        super.onResume();
        llenarListView();
    }
    @Override
    public void onItemClicked(String titulo) {

    }
}