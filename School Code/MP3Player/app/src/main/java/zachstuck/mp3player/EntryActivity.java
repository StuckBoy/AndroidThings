package zachstuck.mp3player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EntryActivity extends AppCompatActivity {

    public static final String URL = "URL_ADDRESS";

    private Button search_button;
    private EditText URL_Field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        search_button = (Button) findViewById(R.id.Search_Button);
        search_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent url_intent = new Intent(EntryActivity.this, ScraperActivity.class);
                String url = URL_Field.getText().toString();
                url_intent.putExtra(URL, url);
                startActivity(url_intent);
            }
        });
        URL_Field = (EditText) findViewById(R.id.URL_Field);
    }
}
