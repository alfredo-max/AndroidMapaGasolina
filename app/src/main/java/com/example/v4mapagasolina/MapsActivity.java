package com.example.v4mapagasolina;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private GoogleMap mMap;
    Location currenLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        conn= new ConexionSQLiteHelper(this,"bd_estudiantes",null ,1);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);


            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null){
                    currenLocation=location;
                    Toast.makeText(getApplicationContext(),currenLocation.getLatitude()
                            +""+currenLocation.getLongitude(),Toast.LENGTH_SHORT).show();

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);

                }
            }
        });





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

        LatLng latLng=new LatLng(currenLocation.getLatitude(),currenLocation.getLongitude());//ok



        MarkerOptions markerOptions= new MarkerOptions()
                .position(latLng)
                .title("Ubicacion Actual");


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
        googleMap.addMarker(markerOptions);


        SQLiteDatabase db=conn.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Constantes.TABLA_ESTUDIANTE,null);
        while(cursor.moveToNext()){
            LatLng lugar = new LatLng(Double.parseDouble(cursor.getString(5)), Double.parseDouble(cursor.getString(6)));
            //mMap.addMarker(new MarkerOptions().position(lugar).title(cursor.getString(1)));
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gasolina))
                    .anchor(0.5f,1.0f)
                    //mejor 0.5 1.0
                    .position(lugar)
                    .title(cursor.getString(1))
                    .snippet(cursor.getString(2)+","+cursor.getString(3))
                    .flat(true)

            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }


}

 /* SQLiteDatabase db=conn.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Constantes.TABLA_ESTUDIANTE,null);
        while(cursor.moveToNext()){
            LatLng lugar = new LatLng(Double.parseDouble(cursor.getString(5)), Double.parseDouble(cursor.getString(6)));
            // LatLng lugar = new LatLng(11.3+i, 11.6+i);
            // mMap.addMarker(new MarkerOptions().position(lugar).title(cursor.getString(1)));
            mMap.addMarker(new MarkerOptions().position(lugar).title(cursor.getString(1)));
            // mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gasolina)).anchor(0.0f,1.0f).position(lugar).title(cursor.getString(1)));
        }

      */
