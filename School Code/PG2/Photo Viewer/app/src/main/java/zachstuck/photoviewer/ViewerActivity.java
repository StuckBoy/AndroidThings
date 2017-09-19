package zachstuck.photoviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Zachary Stuck on 2/14/2017.
 */

public class ViewerActivity extends AppCompatActivity {

    public static final String VA = "VIEWER";
    public static final String PICTURE = "Tag_Num";

    private ImageView myImageView;
    private long tagNum;
    private Button backButt;
    private int[] Animals = new int[] {
            R.drawable.pigeon, R.drawable.calico, R.drawable.catdog, R.drawable.cheshire,
            R.drawable.corgi, R.drawable.greyhound, R.drawable.husky, R.drawable.labrador,
            R.drawable.mainecoon, R.drawable.pomeranian, R.drawable.siamese
    };

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        Log.d(VA, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        myImageView = (ImageView) findViewById(R.id.PictureFrame);
        Intent viewIntent = getIntent();
        tagNum = viewIntent.getIntExtra(PICTURE, 0);
        Log.d(VA, String.format("tagNum = %d", tagNum));
        myImageView.setBackgroundResource(Animals[(int)tagNum]);
        backButt = (Button) findViewById(R.id.back_button);
        backButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(ViewerActivity.this, MainActivity.class);
                backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backIntent);
            }
        });
    }

    public void onStart() {
        Log.d(VA, "onStart");
        super.onStart();
    }

    @Override
    public void onRestart() {
        Log.d(VA, "onRestart");
        super.onRestart();
    }

    @Override
    public void onPause() {
        Log.d(VA, "onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d(VA, "onResume");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        Log.d(VA, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onStop() {
        Log.d(VA, "onStop");
        super.onStop();
    }
}
