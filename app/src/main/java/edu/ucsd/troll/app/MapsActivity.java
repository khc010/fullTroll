package edu.ucsd.troll.app;

/**
 * Created by shalomabitan on 5/23/14.
 */


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import android.app.Activity;
import java.util.ArrayList;

import org.w3c.dom.Document;

import android.annotation.SuppressLint;//**
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;//**
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.PolylineOptions;
//import com.hawk.mapsv2demo.R;


//public class MapsActivity extends FragmentActivity implements
//        GooglePlayServicesClient.ConnectionCallbacks,
//        GooglePlayServicesClient.OnConnectionFailedListener,
//        LocationListener{
//
//    ArrayList<Marker> cartMarkers = new ArrayList<Marker>();
//    ArrayList<LatLng> cartLatLongs = new ArrayList<LatLng>();
//    double myLatitude, myLongitude;
//    private GoogleMap map;
//
//    LocationClient mLocationClient;
//    Location mCurrentLocation;
//    // Milliseconds per second
//    private static final int MILLISECONDS_PER_SECOND = 1000;
//    // Update frequency in seconds
//    public static final int UPDATE_INTERVAL_IN_SECONDS = 5;
//    // Update frequency in milliseconds
//    private static final long UPDATE_INTERVAL =
//            MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;
//    // The fastest update frequency, in seconds
//    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;
//    // A fast frequency ceiling in milliseconds
//    private static final long FASTEST_INTERVAL =
//            MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;
//    LocationRequest mLocationRequest;
//    boolean mUpdatesRequested;
//    SharedPreferences mPrefs;
//    Editor mEditor;
//
//    int currentMapZoom;
//    LatLng myLatLng; // current position coordinates
//    double myBearing;
//    double myAngle;  // camera angle
//    Math myMath;
//    LatLng Warren = new LatLng(32.880966, -117.234450); //For testing
//
//    public final static String MODE_WALKING = "walking";
//
//
//    protected LocationManager locationManager;
//    protected LocationListener locationListener;
//    protected Context context;
//    String lat;
//    String provider;
//    protected String latitude,longitude;
//    protected boolean gps_enabled,network_enabled;
//    GMapDirection mapDirections;
//    @SuppressLint("NewApi")
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.maps_layout);
//        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
//        map.setMyLocationEnabled(true);	//Turns on the location layer
//
////		map.setOnMarkerClickListener(OnMarkerClickListener);
//
////		map.getUiSettings().setMyLocationButtonEnabled(true);// not showing button
////		map.getUiSettings().setZoomControlsEnabled(false);	//turns off zoom
//
////		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
////		map.setMapType(GoogleMap.MAP_TYPE_NONE);
////		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
////		map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//
//        // Open the shared preferences
//        mPrefs = getSharedPreferences("SharedPreferences",
//                Context.MODE_PRIVATE);
//        // Get a SharedPreferences editor
//        mEditor = mPrefs.edit();
//        /*
//         * Create a new location client, using the enclosing class to
//         * handle callbacks.
//         */
//        mLocationClient = new LocationClient(this, this, this);
//        // Start with updates turned off
//        mUpdatesRequested = false;
//
//        // Create the LocationRequest object
//        mLocationRequest = LocationRequest.create();
//        // Use high accuracy
//        mLocationRequest.setPriority(
//                LocationRequest.PRIORITY_HIGH_ACCURACY);
//        // Set the update interval to 5 seconds
//        mLocationRequest.setInterval(UPDATE_INTERVAL);
//        // Set the fastest update interval to 1 second
//        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
//
//        //this is for testing purposes:
////        mLocationClient.requestLocationUpdates(mLocationRequest,
////        (com.google.android.gms.location.LocationListener) this);
//
////        mCurrentLocation = mLocationClient.getLastLocation();	//first time: get current location
//
////		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
////		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//
//        LatLng UCSD = new LatLng(32.881271, -117.2389000);//879271,2289000
//        LatLng Center = new LatLng(32.878053, -117.237218);
//        LatLng socialScience = new LatLng(32.883822, -117.240748);
//        LatLng SOM = new LatLng(32.875852, -117.237578);
//        LatLng RevellePlaza = new LatLng(32.874851, -117.241217);
//        cartLatLongs.add(Warren);
//        cartLatLongs.add(Center);
//        cartLatLongs.add(socialScience);
//        cartLatLongs.add(SOM);
//        cartLatLongs.add(RevellePlaza);
//        cartLatLongs.add(UCSD);
//        currentMapZoom = 16;
//
//        //query the database for open or closed
//
//        Marker ucsd = map.addMarker(new MarkerOptions().position(UCSD)
//                .visible(false).title("UCSD"));
//        //Log.d("Main","MO: before warrHallMarker");
//        Marker warrenHallMarker = map.addMarker(new MarkerOptions().position(Warren)
//                .title("Warren").snippet("Welcome to warren coffee cart"));
//        Marker centerHallMarker = map.addMarker(new MarkerOptions().position(Center)
//                .title("Center"));
//        Marker socialScienceMarker = map.addMarker(new MarkerOptions().position(socialScience)
//                .title("Social Sceinces"));
//        Marker SOMMarker = map.addMarker(new MarkerOptions().position(SOM)
//                .title("SOM"));
//        Marker revellePlazaMarker = map.addMarker(new MarkerOptions().position(RevellePlaza)
//                .title("Revelle Plaza"));
//		/* Add markers to the ArrayList */
//        cartMarkers.add(warrenHallMarker);
//        cartMarkers.add(centerHallMarker);
//        cartMarkers.add(socialScienceMarker);
//        cartMarkers.add(SOMMarker);
//        cartMarkers.add(revellePlazaMarker);
//
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(UCSD, currentMapZoom));
//
//        if(android.os.Build.VERSION.SDK_INT > 9)
//        {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//
////		mapDirections = new GMapDirection();
////		myLatLng = UCSD;//**
////		Document doc = mapDirections.getDocument(myLatLng, Warren, MODE_WALKING);
////		int duration = mapDirections.getDurationValue(doc);
////		String distance = mapDirections.getDistanceText(doc);
////		String start_address = mapDirections.getStartAddress(doc);
////		String copy_right = mapDirections.getCopyRights(doc);
////		ArrayList<LatLng> directionPoint = mapDirections.getDirection(doc);
////		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
////		for(int i=0, _i=directionPoint.size();i<_i;++i)
////		{
////			/*For shits and giggles */
////			rectLine.add(directionPoint.get(i));
////		}
////		map.addPolyline(rectLine);
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment()).commit();
//        }
//    }
//
////	@Override
////	public boolean onCreateOptionsMenu(Menu menu) {
////
////		// Inflate the menu; this adds items to the action bar if it is present.
////		getMenuInflater().inflate(R.menu.main, menu);
////		return true;
////	}
////
////	@Override
////	public boolean onOptionsItemSelected(MenuItem item) {
////		// Handle action bar item clicks here. The action bar will
////		// automatically handle clicks on the Home/Up button, so long
////		// as you specify a parent activity in AndroidManifest.xml.
////		int id = item.getItemId();
////		if (id == R.id.action_settings) {
////			return true;
////		}
////		return super.onOptionsItemSelected(item);
////	}
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_maps, container,
//                    false);
//            return rootView;
//        }
//    }
//
//    /*
//     * Called when the Activity becomes visible.
//     */
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // Connect the client.
//        mLocationClient.connect();
//    }
//
//    /*
//     * Called when the Activity is no longer visible.
//     */
//    @Override
//    protected void onStop() {
//        // Disconnecting the client invalidates it.
//        // If the client is connected
//        if (mLocationClient.isConnected()) {
//            /*
//             * Remove location updates for a listener.
//             * The current Activity is the listener, so
//             * the argument is "this".
//             */
//            mLocationClient.removeLocationUpdates(
//                    (com.google.android.gms.location.LocationListener)this);
//        }
//        /*
//         * After disconnect() is called, the client is
//         * considered "dead".
//         */
//        mLocationClient.disconnect();
//        super.onStop();
//    }
//
//    @Override
//    protected void onPause() {
//        // Save the current setting for updates
//        mEditor.putBoolean("KEY_UPDATES_ON", mUpdatesRequested);
//        mEditor.commit();
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        /*
//         * Get any previous setting for location updates
//         * Gets "false" if an error occurs
//         */
//        super.onResume();
//        if (mPrefs.contains("KEY_UPDATES_ON")) {
//            mUpdatesRequested =
//                    mPrefs.getBoolean("KEY_UPDATES_ON", false);
//
//            // Otherwise, turn off location updates
//        } else {
//            mEditor.putBoolean("KEY_UPDATES_ON", false);
//            mEditor.commit();
//        }
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        //
//        myLatitude = mCurrentLocation.getLatitude();//(location.getLatitude());
//        myLongitude = mCurrentLocation.getLongitude();//(location.getLongitude());
////    	LatLng prevLatLng = new LatLng(myLatLng.latitude, myLatLng.longitude);
////    	myBearing = myMath.atan2(myMath.cos(myLatLng.latitude)*myMath.sin(myLatitude)
////    			-myMath.sin(myLatLng.latitude)*myMath.cos(myLatitude)
////    			*myMath.cos(myLongitude - myLatLng.longitude),
////    			myMath.sin(myLongitude - myLatLng.longitude)*myMath.cos(myLongitude));
////    	ATAN2(COS(lat1)*SIN(lat2)-SIN(lat1)*COS(lat2)*COS(lon2-lon1), SIN(lon2-lon1)*COS(lat2))
//        myLatLng = new LatLng(myLatitude, myLongitude);
//
////    	myBearing = myBearing - 5;
////    	if(myBearing < 0)
////    		myBearing = 360;
////    	myAngle = myBearing/6;
//
////    	map.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));//this was for testing the GPS
////    	map.ani
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(myLatLng) // Sets the center of the map to
//                .zoom(currentMapZoom+1)                   // Sets the zoom
//                .bearing((float) myBearing) // Sets the orientation of the camera to east
//                .tilt((float)myAngle)    // Sets the tilt of the camera to 30 degrees
//                .build();    // Creates a CameraPosition from the builder
//        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//        Document doc = mapDirections.getDocument(myLatLng, Warren, MODE_WALKING);
//        int duration = mapDirections.getDurationValue(doc);
//        String distance = mapDirections.getDistanceText(doc);
//        String start_address = mapDirections.getStartAddress(doc);
//        String copy_right = mapDirections.getCopyRights(doc);
//        ArrayList<LatLng> directionPoint = mapDirections.getDirection(doc);
//        PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
//        for(int i=0, _i=directionPoint.size();i<_i;++i)
//        {
//            rectLine.add(directionPoint.get(i));
//        }
//        map.clear();
//        map.addMarker(new MarkerOptions().position(Warren)
//                .title("Warren"));
//        map.addPolyline(rectLine);
//
//        //.newLatLngZoom(myLatLng, currentMapZoom));
////    	Log.d("Main", " MO: Longitude: " + myLongitude + " Lat: " + myLatitude);
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        //
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//        //
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//        //
//
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//        //
//
//    }
//
//    /*
//     * Called by Location Services when the request to connect the
//     * client finishes successfully. At this point, you can
//     * request the current location or start periodic updates
//     */
//    @Override
//    public void onConnected(Bundle connectionHint) {
//        // Display the connection status
//        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
//        // If already requested, start periodic updates
//        if (mUpdatesRequested)
//        {
//            mLocationClient.requestLocationUpdates(mLocationRequest,
//                    (com.google.android.gms.location.LocationListener) this);
//        }
//    }
//
//
//
//    @Override
//    public void onDisconnected() {
//        // Display the connection status
//        Toast.makeText(this, "Disconnected. Please re-connect.",
//                Toast.LENGTH_SHORT).show();
//    }
//
////	private void setUpMapIfNeeded() {
////	    // Do a null check to confirm that we have not already instantiated the map.
////	    if (map == null) {
////	        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
////	                            .getMap();
////	        // Check if we were successful in obtaining the map.
////	        if (map != null) {
////	            // The Map is verified. It is now safe to manipulate the map.
////
////	        }
////	    }
////	}
//}
//
//
///*additional methods for which we will manipulate to draw custom icons*/
////
////private Marker melbourne = mMap.addMarker(new MarkerOptions()
////.position(MELBOURNE)
////.title("Melbourne")
////.snippet("Population: 4,137,400")
////.icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));



public class MapsActivity extends Activity {

    // Google Map
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_layout);

        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

}