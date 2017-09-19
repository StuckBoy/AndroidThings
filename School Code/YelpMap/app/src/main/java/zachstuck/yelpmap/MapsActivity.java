package zachstuck.yelpmap;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, RetrieveFeedTask.AsyncResponse {

    private GoogleMap mMap;
    private String latitude, longitude;
    private double latNum, lonNum;
    private String[] nameLatLon;

    public static final String MA = "MAP_ACTIVITY";
    public static final String LAT = "lat_num";
    public static final String LON = "long_num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(MA, "Creating Map!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent coordIntent = getIntent();
        latitude = coordIntent.getStringExtra(LAT);
        longitude = coordIntent.getStringExtra(LON);
        try { latNum = Double.parseDouble(latitude);
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        try { lonNum = Double.parseDouble(longitude);
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Marquette, Michigan.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        new RetrieveFeedTask(this).execute(latNum, lonNum);
        // Add a marker on location and move the camera
        LatLng marquette = new LatLng(latNum, lonNum);
        mMap.addMarker(new MarkerOptions().position(marquette).title("Your Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        float zoomLvl = 14;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marquette, zoomLvl));
    }

    @Override
    public void processFinish(String[] nLL) {
        nameLatLon = new String[nLL.length];
        nameLatLon = nLL;
        double latD = 0.0;
        double lonD = 0.0;

        for (int i=0; i < nameLatLon.length; i++){
            String[] split = nLL[i].split(",");
            try { latD = Double.parseDouble(split[1]);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }
            try { lonD = Double.parseDouble(split[2]);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }
            LatLng point = new LatLng(latD, lonD);
            mMap.addMarker(new MarkerOptions().position(point).title(split[0]));
        }
    }

    @Override
    public void onDestroy() {
        Log.d(MA, "Destroying map");
        super.onDestroy();
    }
}

class RetrieveFeedTask extends AsyncTask<Double, Void, String[]> {

    private double latNum, lonNum;
    private String[] nameLatLon;

    public interface AsyncResponse {
        void processFinish(String[] output);
    }

    public AsyncResponse delegate = null;

    public RetrieveFeedTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(String[] result) {
        delegate.processFinish(result);
    }

    @Override
    protected String[] doInBackground(Double... params) {
        latNum = params[0].doubleValue();
        lonNum = params[1].doubleValue();
        System.out.println("Beggining Yelp stuff.");
        try{
            URL obj = new URL("https://api.yelp.com/v3/businesses/search?latitude="+latNum+"&longitude="+lonNum+"&radius=8046&categories=hotels");
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            System.out.println("Successfully connected.");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer cZZEjIHHufd63oI6q0Xf-rKpHFiNn4UdlkfxGPE_YI7aOkZvdoUzMb8XVxQ_Ta0qKlqSdFbQOXXM8lQpqD0lJKjh42x7Dnp2BGqvX4HZ1IdvoxyS-zNFw3dvCfUpWHYx");
            int responseCode = connection.getResponseCode();
            System.out.println("GET response code : " + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String yelpResponse = "";
                while(true) {
                    int c = in.read();
                    if (c == -1) { break; }
                    yelpResponse += (char) c;
                }
                in.close();
                JSONObject yelpStuff = new JSONObject(yelpResponse);
                Iterator<String> t = yelpStuff.keys();
                while(t.hasNext()) {
                    String key = t.next();
                    System.out.println(key);
                }
                System.out.println(yelpStuff.getInt("total") + " total");
                nameLatLon = new String[yelpStuff.getInt("total")]; //should be yelpStuff.getInt("total") but that gives more than 50
                JSONArray b = yelpStuff.getJSONArray("businesses");
                System.out.println(b.length() + " array size");
                for(int i=0; i < b.length(); i++) {
                    JSONObject restaurant = b.getJSONObject(i);
                    String rName = restaurant.getString("name");
                    JSONObject coord = restaurant.getJSONObject("coordinates");
                    String rLat = coord.getString("latitude");
                    String rLon = coord.getString("longitude");
                    nameLatLon[i] = rName + "," + rLat + "," + rLon;
                }
                for(int a = 0; a < nameLatLon.length; a++) {
                    System.out.println(nameLatLon[a]);
                }
            }
        }catch (Exception e){
            System.out.println("Something broke.");
            e.printStackTrace();
        }
        return nameLatLon;
    }
}


