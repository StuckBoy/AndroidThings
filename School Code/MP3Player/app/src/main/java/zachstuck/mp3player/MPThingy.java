package zachstuck.mp3player;

import android.media.MediaPlayer;

/**
 * Created by Zachary Stuck on 4/27/2017.
 */

public class MPThingy {
    private static MediaPlayer mp;

    private MPThingy() {
        mp = new MediaPlayer();
    }

    public static MediaPlayer getMediaPlayer(){
        if (mp == null){
            new MPThingy();
        }
        return mp;
    }

    public static void breakdown(){
        mp.release();
    }
}
