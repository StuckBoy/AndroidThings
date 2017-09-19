package zachstuck.mp3player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.InputStream;

/**
 * Created by Zachary Stuck on 4/11/2017.
 */

public class PlayerActivity extends AppCompatActivity {
    public static final String PA = "PLAYER_ACTIVITY";
    public static final String SN = "SONG_NAME";
    public static final String URL = "URL_ADDRESS";

    private MediaPlayer jukebox;
    private TextView songTitle;
    private ImageView albumCover;
    private String title;
    private String url;
    private ProgressBar progress;
    private Handler mHandler = new Handler();

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        Log.d(PA, "Building Jukebox");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        songTitle = (TextView) findViewById(R.id.player_song_title);
        albumCover = (ImageView) findViewById(R.id.albumImage);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        Intent fetchIntent = getIntent();
        title = fetchIntent.getStringExtra(SN);
        url = fetchIntent.getStringExtra(URL);
        songTitle.setText(title);
        if (title.contains(" ")) {
            title = title.replaceAll(" ", "%20");
        }
        System.out.println(url+title);
        new DownloadImageTask().execute(url+title+".jpg");

    }
    @Override
    protected void onResume() {
        super.onResume();
        jukebox = MPThingy.getMediaPlayer();
        System.out.println(url+title+".mp3");
        if(jukebox.isPlaying()) {
            jukebox.stop();
            jukebox.reset();
            System.out.println("Stopped playing song.");
        }
        else System.out.println("Nothing's playing.");
        try{
            jukebox.setDataSource(url+title+".mp3");
           /* jukebox.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                    mp.start();
                }
            }); */
            jukebox.prepare();
            Log.d(PA, "Beginning playback");
            jukebox.start();
            System.out.println(jukebox.getDuration() / 1000);
        }
        catch(Exception j) {
            j.printStackTrace();
        }
        jukebox.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                jukebox.stop();
            }
        });
        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(jukebox != null) {
                    int currentPos = jukebox.getCurrentPosition() / 1000;
                    progress.setProgress(currentPos);
                    progress.setMax(jukebox.getDuration() / 1000);
                }
                mHandler.postDelayed(this, 1000);
            }
        });
    }

    // show The Image in a ImageView
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap image = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                image = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return image;
        }

        protected void onPostExecute(Bitmap result) {
            albumCover.setImageBitmap(result);
        }
    }
}
