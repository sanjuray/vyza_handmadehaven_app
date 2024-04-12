package com.example.handicrafts.profile;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.handicrafts.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class maps_activity extends FragmentActivity implements OnMapReadyCallback {
    Location currentlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code=101;
    GoogleMap googleMap;
   // FrameLayout map;
    View map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        map=findViewById(R.id.map);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        location();




    }

    private void location() {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},Request_code);
            return;
        }
        Task<Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    currentlocation=location;
                    SupportMapFragment fragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert fragment!=null;
                    fragment.getMapAsync(maps_activity.this);
                }

            }

        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        getgetLocationAddress(this, currentlocation.getLatitude(), currentlocation.getLatitude());
      LatLng latLng=new LatLng(currentlocation.getLatitude(),currentlocation.getLongitude());
      MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("set current location");
      googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
        googleMap.addMarker(markerOptions);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==Request_code){
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                location();
            }
        }
    }
    public  void getgetLocationAddress(maps_activity maps_activity,double lat,double lng){
        Geocoder geocoder;

        List<Address>addresses;

        geocoder = new Geocoder(maps_activity, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
             String address = addresses.get(0).getAddressLine(0);
           String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String pin=addresses.get(0).getPostalCode();




            //Here address set to your textview
        } catch (IOException e) {
            e.printStackTrace();
        } }
}