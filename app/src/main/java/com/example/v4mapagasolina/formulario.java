package com.example.v4mapagasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class formulario extends AppCompatActivity {

    EditText CampoCodigo,CampoNombre, CampoEmpresa,CampoDepartamento,CampoMunicipio,CampoLatitud,CampoLongitud;
    ConexionSQLiteHelper conn=null;
    int id=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
       // CampoCodigo= (EditText) findViewById(R.id.txtcodigo);
        CampoNombre= (EditText) findViewById(R.id.txtnombre3);
        CampoEmpresa = (EditText) findViewById(R.id.textempresa);
        CampoDepartamento = (EditText) findViewById(R.id.txtdepartamento);
        CampoMunicipio = (EditText) findViewById(R.id.txtmunicipio);

        CampoLongitud = (EditText) findViewById(R.id.txtlongitud);
        CampoLatitud = (EditText) findViewById(R.id.txtlatitud);

        conn= new ConexionSQLiteHelper(this,"bd_estudiantes",null ,1);
    }

    public boolean ValidarVacio(String nombre,String empresa,String departamento,String municipio, String latitud,String longitud){

        boolean vacio=false;
        if(empresa.isEmpty()){
            CampoEmpresa.setError("Este campo no puede quedar vacio");
            vacio=true;
        }
        if(nombre.isEmpty()){
            CampoNombre.setError("Este campo no puede quedar vacio");
            vacio=true;
        }
        if(departamento.isEmpty()){
            CampoDepartamento.setError("Este campo no puede quedar vacio");
            vacio=true;
        }
        if(municipio.isEmpty()){
            CampoMunicipio.setError("Este campo no puede quedar vacio");
            vacio=true;
        }

        if(longitud.isEmpty()){
            CampoLongitud.setError("Este campo no puede quedar vacio");
            vacio=true;
        }
        if(latitud.isEmpty()){
            CampoLatitud.setError("Este campo no puede quedar vacio");
            vacio=true;
        }
        return vacio;
    }

    public void onClick(View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnGuardar:
                // Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
                Registrar();
                break;
            case R.id.btnCancelar:
                cancelar();
                break;
            case R.id.btnListado:
                // Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
                miIntent= new Intent(this,listado.class);
                break;

            case R.id.btnVerMapa:
                // Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
                miIntent= new Intent(this,MapsActivity.class);
                break;
        }
        if(miIntent!=null){
            startActivity(miIntent);
        }

    }

    private void Registrar() {
        SQLiteDatabase db= conn.getWritableDatabase();
        ContentValues values= new ContentValues();
      //  String codigo=CampoCodigo.getText().toString();
        String nombre=CampoNombre.getText().toString();
        String empresa= CampoEmpresa.getText().toString();
        String departamento=CampoDepartamento.getText().toString();
        String municipio=CampoMunicipio.getText().toString();
        String latitud=CampoLatitud.getText().toString();
        String longitud=CampoLongitud.getText().toString();
      //  double latitud= Double.parseDouble(CampoLatitud.getText().toString());
        //double longitud= Double.parseDouble(CampoLongitud.getText().toString());

        if(!ValidarVacio(nombre,empresa,departamento,municipio,latitud,longitud)){
        values.put(Constantes.Campo_ID,String.valueOf(id));
        values.put(Constantes.Campo_nombre,nombre);
        values.put(Constantes.Campo_Empresa,empresa);
        values.put(Constantes.Campo_Departamento,departamento);
        values.put(Constantes.Campo_Municipio,municipio);
        double lat= Double.parseDouble(latitud);
        double lng= Double.parseDouble(longitud);
        values.put(Constantes.Campo_Latitud,lat);
        values.put(Constantes.Campo_Longitud,lng);

        id++;
        if(!buscarlongitud() || !buscarlatitud()){
        db.insert(Constantes.TABLA_ESTUDIANTE,Constantes.Campo_ID,values);
        Toast.makeText(getApplicationContext(),"ESTACION REGISTRADA",Toast.LENGTH_SHORT).show();
        }else{
           Toast.makeText(getApplicationContext(),"ESTACION YA ESTA REGISTRADA",Toast.LENGTH_SHORT).show();
          }
        }


        db.close();
    }

    public boolean buscarlongitud(){
        boolean encontrado=false;
        SQLiteDatabase db= conn.getReadableDatabase();
       // String[] parametros={CampoCodigo.getText().toString()};
        //String[] campos={Constantes.Campo_nombre,Constantes.Campo_programa};
        String[] parametros={CampoLongitud.getText().toString()};
        String[] campos={Constantes.Campo_Longitud,Constantes.Campo_nombre};


        //Cursor cursor= db.query(Constantes.TABLA_ESTUDIANTE,campos,Constantes.Campo_ID+"=?",parametros,null,null,null);
        Cursor cursor= db.query(Constantes.TABLA_ESTUDIANTE,campos,Constantes.Campo_Longitud+"=?",parametros,null,null,null);

        if(cursor.moveToFirst()){
            encontrado=true;
            cursor.close();
            cursor.close();
            return encontrado;
        }

        return encontrado;

    }
    public boolean buscarlatitud(){
        boolean encontrado=false;
        SQLiteDatabase db= conn.getReadableDatabase();
        // String[] parametros={CampoCodigo.getText().toString()};
        //String[] campos={Constantes.Campo_nombre,Constantes.Campo_programa};
        String[] parametros={CampoLatitud.getText().toString()};
        String[] campos={Constantes.Campo_Latitud,Constantes.Campo_nombre};


        //Cursor cursor= db.query(Constantes.TABLA_ESTUDIANTE,campos,Constantes.Campo_ID+"=?",parametros,null,null,null);
        Cursor cursor= db.query(Constantes.TABLA_ESTUDIANTE,campos,Constantes.Campo_Latitud+"=?",parametros,null,null,null);

        if(cursor.moveToFirst()){
            encontrado=true;
            cursor.close();
            cursor.close();
            return encontrado;
        }

        return encontrado;

    }

    public void cancelar (){

        CampoNombre.setText("");
        CampoEmpresa.setText("");
        CampoDepartamento.setText("");
        CampoMunicipio.setText("");
        CampoLatitud.setText("");
        CampoLongitud.setText("");




    }

}