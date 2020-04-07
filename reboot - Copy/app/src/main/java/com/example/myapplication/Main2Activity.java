package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;

public class Main2Activity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private Button locationButton,gps;
    private TextView locationText;
    //Initializing the GoogleApiClient object
    private GoogleApiClient googleApiClient;
    public static final int LOCATION_REQUEST = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gps = findViewById(R.id.iii);
        locationButton = (Button) findViewById(R.id.location_btn);
        locationText = (TextView) findViewById(R.id.location_text);
        //Building a instance of Google Api Client
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build();
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }
        });
    }
    public void onStart() {
        super.onStart();
        // Initiating the GoogleApiClient Connection when the activity is visible
        googleApiClient.connect();
    }
    public void onStop() {
        super.onStop();
        //Disconnecting the GoogleApiClient when the activity goes invisible
        googleApiClient.disconnect();
    }
    /*
    This callback is invoked when the GoogleApiClient is successfully connected
    */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //We set a listener to our button only when the ApiClient is connected successfully
        locationButton.setOnClickListener(this);
    }
    //This callback is invoked when the user grants or rejects the location permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else
                    Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT);
                break;
        }
    }
    private void getCurrentLocation() {
        //Checking if the location permission is granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, LOCATION_REQUEST);
            return;
        }
        //Fetching location using FusedLOcationProviderAPI
        FusedLocationProviderApi fusedLocationApi = LocationServices.FusedLocationApi;
        Location location = fusedLocationApi.getLastLocation(googleApiClient);
        //In some rare cases Location obtained can be null
        if (location == null)
            locationText.setText("Not able to fetch location");
        else
            locationText.setText("Location co-ord are " + location.getLatitude() + "," + location.getLongitude());
    }
    //Callback invoked if the GoogleApiClient connection is suspended
    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection was suspended", Toast.LENGTH_SHORT);
    }
    //Callback invoked if the GoogleApiClient connection fails
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection failed", Toast.LENGTH_SHORT);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.location_btn:
                getCurrentLocation();
                break;
        }
    }
}