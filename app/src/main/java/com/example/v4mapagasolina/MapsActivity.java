package com.example.v4mapagasolina;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        conn= new ConexionSQLiteHelper(getApplicationContext(),"bd_estudiantes",null,1);
        getLocalizacion();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(11.011295180937296, -74.23872657069808);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Ubicacion Actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10));

        SQLiteDatabase db=conn.getReadableDatabase();
        //Estudiante estudiante=null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+Constantes.TABLA_ESTUDIANTE,null);
        while(cursor.moveToNext()){
           LatLng lugar = new LatLng(Double.parseDouble(cursor.getString(5)), Double.parseDouble(cursor.getString(6)));
           // LatLng lugar = new LatLng(11.3+i, 11.6+i);
           // mMap.addMarker(new MarkerOptions().position(lugar).title(cursor.getString(1)));
            mMap.addMarker(new MarkerOptions().position(lugar).title(cursor.getString(1)));
           // mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gasolina)).anchor(0.0f,1.0f).position(lugar).title(cursor.getString(1)));
            // i++;
        }

    }
    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

}