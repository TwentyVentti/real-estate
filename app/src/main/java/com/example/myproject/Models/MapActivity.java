package com.example.myproject.Models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.myproject.JsonParsermap;
import com.example.myproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
//import com.skyfishjy.library.RippleBackground;

public class MapActivity extends AppCompatActivity {

    //private RippleBackground rippleBg;
    Spinner spType;
    Button btfind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        spType = findViewById(R.id.sp_type);
        btfind = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        final String[] placeTypeList = {"restaurant", "bar", "hotel","atm"};
        //grocery,hospital,parking

        String[] placeNameList = {"Restaurant", "Bar", "Hotel","Atm"};
        //Grocery,Hospital,Parking

        spType.setAdapter(new ArrayAdapter<>(MapActivity.this,
                android.R.layout.simple_spinner_dropdown_item, placeNameList));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        }

        btfind.setOnClickListener(new View.OnClickListener() {
            @Override
            // https://maps.googleapis.com/maps/api/place/nearbysearch/json?types=Bar&sensor=true&key=AIzaSyCFeVEs1n24pffvedWoXsAaRxcVjPqNes8
            public void onClick(View v) {
                int i = spType.getSelectedItemPosition();
                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                        "?location=" + currentLat + "," + currentLong +
                        "&radius=5000" +
                        "&types=" + placeTypeList[i] +
                        "&sensor=true" +
                        "&key=" + getResources().getString(R.string.google_map_key);

                new PlaceTask().execute(url);


            }
        });
    }

    private void getCurrentLocation() {

        //Log.d("flag1","before curr");
        //CancellationTokenSource cts = new CancellationTokenSource();

        @SuppressLint("MissingPermission")
        Task<Location> task = fusedLocationProviderClient.getLastLocation();

        //Task<Location> task = fusedLocationProviderClient.getCurrentLocation(1,cts.getToken());

        //Log.d("flag1","after curr");
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location!=null)
                {
                    currentLat=location.getLatitude();

                    //String temp = Double.toString(currentLat);
                    //Log.d("location",temp);

                    currentLong=location.getLongitude();

                    //Log.d("location",Double.toString(currentLong));
                    // D/location: 37.4219983
                    // D/location: -122.084
                    // -35.2743989,149.1156678

                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map=googleMap;
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(currentLat,currentLong),10
                            ));
                        }
                    });

                }

            }
        });
        {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                getCurrentLocation();
        }
    }

    private class PlaceTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string) throws IOException {

        URL url = new URL(string);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.connect();
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line = "";

        while ((line = reader.readLine()) != null){

            builder.append(line);
        }

        String data = builder.toString();
        reader.close();
        return data;
    }

    private class  ParserTask extends AsyncTask<String,Integer, List<HashMap<String,String >>>{

        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            JsonParsermap jsonParsermap = new JsonParsermap();
            List<HashMap<String,String>> mapList = null;
            JSONObject object = null;
            try {
                object = new JSONObject(strings[0]);
                mapList = jsonParsermap.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            map.clear();

            for (int i=0; i<hashMaps.size(); i++)
            {
                HashMap<String,String> hashMapList = hashMaps.get(i);

                double lat = Double.parseDouble(hashMapList.get("lat"));
                double lng = Double.parseDouble(hashMapList.get("lng"));
                String name = hashMapList.get("name");
                LatLng latLng = new LatLng(lat,lng);
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                options.title(name);
                map.addMarker(options);

            }
        }
    }
}





//btnFind.setOnClickListener(new View.OnClickListener() {

//            @Override

  //          public void onClick(View v) {

    //            LatLng currentMarkerLocation = mMap.getCameraPosition().target;

      //          rippleBg.startRippleAnimation();

        //        new Handler().postDelayed(new Runnable() {

          //          @Override
            //        public void run() {

              //          rippleBg.stopRippleAnimation();

                //        startActivity(new Intent(MapActivity.this, NewActivity.class));

                  //      finish();

                    //}
                //}, 3000);

            //}
       // });
   // }
