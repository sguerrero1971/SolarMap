package com.example.sguerrero.solarmap;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static android.app.ProgressDialog.show;


public class MapsActivity extends Activity
        implements LocationListener,OnMarkerDragListener {

    GoogleMap googleMap;
    LocationManager locationManager;
    String locationProvider;
    boolean markerClicked;




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //set the layout
        setContentView(R.layout.activity_maps);


        //initialize the map
        if (this.googleMap == null) {

            this.googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

            if (this.googleMap != null) {

                this.initializeMap();
            }
        }


        //initialize the locaiton manager
        this.initializeLocationManager();

        //Animate Map to My location and build 2 markers for Load and Dump
        Location location = locationManager.getLastKnownLocation(locationProvider);
        CameraPosition campos = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
                    .zoom(17.8f)
                    .build();
        CameraUpdate camupdate = CameraUpdateFactory.newCameraPosition(campos);
        this.googleMap.animateCamera(camupdate);

            LatLng fillMarker = new LatLng(location.getLatitude(), location.getLongitude());
            LatLng dumpMarker = new LatLng(location.getLatitude(), location.getLongitude());

        Marker loadmarker  = googleMap.addMarker(new MarkerOptions()
                    .position(fillMarker)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("Load")
                    .draggable(true));

        Marker dumpmarker= googleMap.addMarker(new MarkerOptions()
                    .position(dumpMarker)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("Dump")
                    .draggable(true));

/*        CircleOptions filloptions = new CircleOptions()
                    .center(fillMarker)
                    .radius(100)
                    .fillColor(HUE_GREEN)
                    .strokeColor(Color.TRANSPARENT)
                    .strokeWidth(2);

        CircleOptions dumpoptions = new CircleOptions()
                .center(dumpMarker)
                .radius(100)
                .fillColor(HUE_RED)
                .strokeColor(Color.TRANSPARENT)
                .strokeWidth(2);

        Circle fillcircle = this.googleMap.addCircle(filloptions);
        Circle dumpcircle = this.googleMap.addCircle(dumpoptions);*/



    }

    @Override
    protected void onResume() {

        super.onResume();

        Log.i("called", "Activity --> onResume");

        this.locationManager.requestLocationUpdates(this.locationProvider, 400, 1, this);
    }

    @Override
    protected void onPause() {

        super.onPause();

        Log.i("called", "Activity --> onPause");

        this.locationManager.removeUpdates(this);
    }


    //----------------------------------------
    //	Summary: For initializing the map
    //----------------------------------------
    private void initializeMap() {

        //set map type
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        this.googleMap.setOnMarkerDragListener(this);
        this.googleMap.setMyLocationEnabled(true);

        markerClicked = false;

        //TODO: other map initialization as needed
    }

    //-------------------------------------------
    //	Summary: initialize location manager
    //-------------------------------------------
    private void initializeLocationManager() {

        //get the location manager
        this.locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


        //define the location manager criteria
        Criteria criteria = new Criteria();

        this.locationProvider = locationManager.getBestProvider(criteria, false);

        Location location = locationManager.getLastKnownLocation(locationProvider);


        //initialize the location
        if(location != null) {

            onLocationChanged(location);
        }
    }

    //------------------------------------------
    //	Summary: Location Listener  methods
    //------------------------------------------

    @Override
    public void onProviderDisabled(String arg0) {

        Log.i("called", "onProviderDisabled");
    }

    @Override
    public void onProviderEnabled(String arg0) {

        Log.i("called", "onProviderEnabled");
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

        Log.i("called", "onStatusChanged");
    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        SetGeoFenceDialogFragment alert=new SetGeoFenceDialogFragment();
        alert.show(getFragmentManager(),"SetGeoFenceDialogFragment");

    }


    @Override
    public void onMarkerDragStart(Marker marker) {

    }
}

