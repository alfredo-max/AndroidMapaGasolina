package com.example.v4mapagasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class listado extends AppCompatActivity {

    private ListView mListView;
    private List<Estudiante> mlista= new ArrayList<>();
    ListAdapter mAdapter;
    ConexionSQLiteHelper conn;
    private boolean creado=false;
    private Intent miIntent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        conn= new ConexionSQLiteHelper(getApplicationContext(),"bd_estudiantes",null,1);
        creado=true;

        mListView = findViewById(R.id.listView);
        consultarListaEstudiantes();
      /*  miIntent=new Intent(this, verestudiante.class);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String codigo=mlista.get(pos).getCodigo();
                String nombre=mlista.get(pos).getNombre();
                String programa=mlista.get(pos).getProgrma();

                miIntent.putExtra("codigo",codigo);
                miIntent.putExtra("nombre",nombre);
                miIntent.putExtra("programa",programa);


                startActivity(miIntent);
                // Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
            }
        });*/
        //mlista.add(new Estudiante(123,"mojica","123"));
        //mAdapter= new ListAdapter(listado.this,R.layout.item_row,mlista);
        //mListView.setAdapter(mAdapter);
    }
    private void consultarListaEstudiantes(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Estudiante estudiante=null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+Constantes.TABLA_ESTUDIANTE,null);
        while(cursor.moveToNext()){
            estudiante=new Estudiante();
            estudiante.setNombre(cursor.getString(1));
            estudiante.setLatitud(Double.parseDouble(cursor.getString(5)));
            estudiante.setLongitud(Double.parseDouble(cursor.getString(6)));
            // estudiante.setEmpresa(cursor.getString(2));
            mlista.add(estudiante);

        }
        //    mAdapter= new ListAdapter(listado.this,R.layout.item_row,mlista);
        //  mListView.setAdapter(mAdapter);
        obtenerlista();

    }

    private void obtenerlista() {
        mAdapter= new ListAdapter(listado.this,R.layout.item_row,mlista);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        mlista.clear();
        consultarListaEstudiantes();
    }
}