package com.example.myproject.ui.Greetings;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myproject.JsonParsermap;
//import com.example.myproject.Models.MapActivity;
import com.example.myproject.R;
import com.example.myproject.ui.HomePage.HomePageFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import java.util.Objects;

public class GreetingsFragment extends Fragment {

    public GreetingsFragment() {};
    Spinner spType;
    Button btfind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    Marker mCurrLocationMarker;

    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;
    private static final String TAG = "MapActivity";
    private static final float DEFAULT_ZOOM = 15f;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_map, container, false);

        spType = v.findViewById(R.id.sp_type);
        btfind = v.findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_map);




        //setContentView(R.layout.activity_map);
        //View v = inflater.inflate(R.layout.activity_map, container, false);


        //spType = findViewById(R.id.sp_type);
        //btfind = findViewById(R.id.bt_find);
        //supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
        //      .findFragmentById(R.id.google_map);


        final String[] placeTypeList = {"restaurant", "bar", "hotel","atm"};
        //grocery,hospital,parking

        String[] placeNameList = {"Looking for a place to eat?", "Looking for a place to drink?", "Looking for a place to stay?","Looking for a place to withdraw money?"};
        //Grocery,Hospital,Parking

        //spType.setAdapter(new ArrayAdapter<>(this,
        //      android.R.layout.simple_spinner_dropdown_item,
        //    placeNameList));


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, placeNameList);
        spType.setAdapter(adapter);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

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
        return v;
    }


    private void getCurrentLocation() {


        @SuppressLint("MissingPermission")
        Task<Location> task = fusedLocationProviderClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {


                if (location!=null)
                {
                    currentLat=location.getLatitude();

                    currentLong=location.getLongitude();





                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map=googleMap;
                            LatLng latLng = new LatLng(currentLat, currentLong);
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng);
                            markerOptions.title("I am here !");
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                            mCurrLocationMarker = map.addMarker(markerOptions);


                            //map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                            //        new LatLng(currentLat,currentLong),10
                            //));
                            //map.setMinZoomPreference(12.0f);
                            //map.setMaxZoomPreference(30.0f);




                        }


                    });

                }

            }
        });
    }


    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

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

    @SuppressLint("StaticFieldLeak")
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

                double lat = Double.parseDouble(Objects.requireNonNull(hashMapList.get("lat")));
                double lng = Double.parseDouble(Objects.requireNonNull(hashMapList.get("lng")));
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
