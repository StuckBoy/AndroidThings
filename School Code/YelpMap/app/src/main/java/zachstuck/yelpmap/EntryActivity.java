package zachstuck.yelpmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Zachary Stuck on 4/10/2017.
 */

public class EntryActivity extends AppCompatActivity {

    public static final String EV = "ENTRYVIEW";
    public static final String LAT = "lat_num";
    public static final String LON = "long_num";

    private Button search_button;
    private EditText latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(EV, "App Start!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        search_button = (Button) findViewById(R.id.search_button);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(EntryActivity.this, MapsActivity.class);
                String lat = latitude.getText().toString();
                String lon = longitude.getText().toString();
                searchIntent.putExtra(LAT, lat);
                searchIntent.putExtra(LON, lon);
                startActivity(searchIntent);
            }
        });
    }

}
