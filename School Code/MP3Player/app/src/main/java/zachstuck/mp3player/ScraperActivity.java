package zachstuck.mp3player;

import android.content.Intent;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zachary Stuck on 4/11/2017.
 */

public class ScraperActivity extends AppCompatActivity {

    public static final String URL = "URL_ADDRESS";
    public static final String SN = "SONG_NAME";
    public static final String LA = "LIST_ACTIVITY";

    private String urlAddr;
    private ArrayList<String> songList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView myListView;
    private MediaPlayer mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LA, "Generating list");
        mp = MPThingy.getMediaPlayer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        myListView = (ListView) findViewById(R.id.listview);
        Intent listIntent = getIntent();
        urlAddr = listIntent.getStringExtra(URL);
        if (!urlAddr.contains("http://")) urlAddr = "http://" + urlAddr;
        if (urlAddr.substring(urlAddr.length()-1) != "/") {
            urlAddr = urlAddr+"/";
        }
        adapter = new ArrayAdapter<String>(this, R.layout.listview_item_layout, R.id.textView, songList);
        new parseStuff().execute();
        try{
            Thread.sleep(500);
        }
        catch(Exception s){}
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mp.isPlaying()){
                    System.out.println("Found running song, stopping now...");
                    //mp.stop();
                }
                String selectedSong = (String) (myListView.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(), "Now playing: " + selectedSong, Toast.LENGTH_SHORT).show();
                Intent songintent = new Intent(ScraperActivity.this, PlayerActivity.class);
                songintent.putExtra(SN, selectedSong);
                songintent.putExtra(URL, urlAddr);
                startActivity(songintent);
            }
        });
    }

    @Override
    protected void onDestroy(){
        Log.d(LA, "Cleaning up mess.");
        MPThingy.breakdown();
        super.onDestroy();
    }

    public void printList() {
        for (String s : songList) {
            System.out.println(s);
        }
        adapter.notifyDataSetChanged();
    }

    private class parseStuff extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Document doc;
            int count = 0;
            try {
                doc = Jsoup.connect(urlAddr).get();
                for (Element mp3 : doc.select("a[href]")) {
                    String fName = mp3.text();
                    if (fName.contains(".mp3")) count++;
                }
                System.out.println("Found "+ count + " mp3's!");
                for (Element mp3 : doc.select("a[href]")) {
                    String fName = mp3.text();
                    if (fName.contains(".mp3")) {
                        fName =  fName.substring(0, fName.lastIndexOf("."));
                        adapter.add(fName);
                    }
                }
            }
            catch(Exception e ) {
                Log.d(LA, "IOException caught while attempting to connect.");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            printList();
         }
    }
}
