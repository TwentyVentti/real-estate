package com.example.myproject.ui.Greetings;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.example.myproject.Models.JsonParsermap;
import com.example.myproject.Models.JsonParsermap;
import com.example.myproject.R;
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

/**
 * @author Kanav Thareja  - u6995005
 */

public class GreetingsFragment extends Fragment {

    public GreetingsFragment() {};
    Spinner spType;
    Button btfind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;
    private final int ZOOMVALUE=15;
    private final int RADIUSVALUE=5000;
    private final int BEARINGVALUE=90;
    private final int TILTVALUE=40;
    private final int REQUESTCODEVALUE=44;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_map, container, false);

        spType = v.findViewById(R.id.sp_type);
        btfind = v.findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager() //Map component
                .findFragmentById(R.id.google_map);



        ;


        final String[] placeTypeList = {"restaurant", "bar", "hotel","atm"};
        //search parameters

        String[] placeNameList = {"Looking for a place to eat?", "Looking for a place to drink?", "Looking for a place to stay?","Looking for a place to withdraw money?"};
        //Search options for user


        /**An adapter dropdown stores all the possible options for the user to search as a dropdown**/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, placeNameList);
        spType.setAdapter(adapter); //for dropdown of options

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        /**
         * The activity first first asks the user for location permsissions to load the app.It then goes on
         * to create the map from the google API.
         **/

        if (ActivityCompat.checkSelfPermission(getActivity(),  // checking requesting permission
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        }

        /**
         *On the the basis of users selection in the dropdown it searches for the relevant places
         * (subject to constraints) on button click which with the help of the google api
         */
        btfind.setOnClickListener(new View.OnClickListener() { // on clicking "Find"
            @Override

            public void onClick(View v) {
                int i = spType.getSelectedItemPosition();
                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +  // string to search places
                        "?location=" + currentLat + "," + currentLong + //current location
                        "&radius=RADIUSVALUE" +    //Max area to search
                        "&types=" + placeTypeList[i] +  //attribute to search
                        "&sensor=true" +  //Indicates whether or not the geocoding request comes from a device with a location sensor
                        "&key=" + getResources().getString(R.string.google_map_key);  // Google key for maps api

                new PlaceTask().execute(url); //finding nearby places


            }
        });
        return v; // return view
    }



    private void getCurrentLocation() {


        @SuppressLint("MissingPermission")
        Task<Location> task = fusedLocationProviderClient.getLastLocation(); //to retrieve the device's last known location

        task.addOnSuccessListener(new OnSuccessListener<Location>() { //called when location found
            @Override
            public void onSuccess(Location location) {



                if (location!=null)
                {
                    currentLat=location.getLatitude();

                    currentLong=location.getLongitude();



                    /**It  retrieves the user's current location coordinate
                     * and displays it as a "Blue" marker with the text "I am here right now".
                     **/

                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {  //initializing the maps system
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map=googleMap;
                            LatLng latLng = new LatLng(currentLat, currentLong);
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng); //current location marker
                            markerOptions.title("I am here right now!"); //current location marker
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                            mCurrLocationMarker = map.addMarker(markerOptions);

                            //move map camera
                            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat, currentLat), 13));

                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(latLng)      // Sets the center of the map to location user
                                    .zoom(ZOOMVALUE)            // zoom in on location
                                    .bearing(BEARINGVALUE)                // Sets the orientation of the camera to east
                                    .tilt(TILTVALUE)                   // Sets the tilt of the camera
                                    .build();                   // Creates a CameraPosition from the builder
                            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                            //for maps ui
                            UiSettings uiSettings = map.getUiSettings();
                            uiSettings.setAllGesturesEnabled(true);
                            uiSettings.setMapToolbarEnabled(true);
                            uiSettings.setZoomControlsEnabled(true);
                            uiSettings.setCompassEnabled(true);





                        }


                    });

                }

            }
        });
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //permission check , If request is cancelled, the result is empty.
        if (requestCode == REQUESTCODEVALUE){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                getCurrentLocation();
        }
    }

    private class PlaceTask extends AsyncTask<String,Integer,String> {//downloading url in background

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

    /**
     * It downloads the url in the background.
     */

    private String downloadUrl(String string) throws IOException { //downloading the url for places

        URL url = new URL(string);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.connect(); // connecting to url
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

        /**
         *after background task is executed,it clears all previous markers and displays new ones
         * based on the search
         */

        @SuppressLint("MissingPermission")
        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) { //after background task is executed

            map.clear(); // clear all previous markers

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
                map.addMarker(options);// adding new relevant markers

            }
        }
    }


}
