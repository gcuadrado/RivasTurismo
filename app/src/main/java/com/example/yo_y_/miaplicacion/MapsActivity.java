package com.example.yo_y_.miaplicacion;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Se configura la vista satélite por defecto
        mMap.setMapType(mMap.MAP_TYPE_SATELLITE);

        // Se crean objetos LatLng de cada Punto de Interés con sus coordenadas
        LatLng cristoDeRivas = new LatLng(40.380878, -3.517814);
        final LatLng trincherasA3 = new LatLng(40.334000, -3.544014);
        LatLng laguna = new LatLng(40.321539, -3.511784);

        //Se configura la ubicación del mapa por defecto al abrir la app (latitud, longitud y nivel de zoom)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.351906,-3.535733), 13));

        //Se crean marcadores en el mapa por cada uno de los puntos de interés, añadiendo posición, título etc...
        mMap.addMarker(new MarkerOptions().position(cristoDeRivas).title("Cristo de Rivas"));
        Marker trincherasA3d=mMap.addMarker(new MarkerOptions().position(trincherasA3).title("Trincheras de la Guerra Civil"));
        trincherasA3d.setTag("trincherasA3");
        mMap.addMarker(new MarkerOptions().position(laguna).title("Laguna del Campillo"));

        //Se configura qué hacer al pulsar en los marcadores
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                    String paginaDescripcion="com.example.yo_y_.miaplicacion."+marker.getTag().toString();
                    try{
                    Class descripcionMarker=Class.forName(paginaDescripcion);
                    Intent intent = new Intent(MapsActivity.this,descripcionMarker);
                    startActivity(intent);}
                    catch (ClassNotFoundException ignored){
                    }

            }
        });

    }

}
