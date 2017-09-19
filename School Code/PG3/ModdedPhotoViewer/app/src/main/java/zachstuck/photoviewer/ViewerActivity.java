package zachstuck.photoviewer;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.UUID;

/**
 * Created by Zachary Stuck on 2/14/2017.
 */

public class ViewerActivity extends SingleFragmentActivity {

    public static final String VA = "VIEWER";
    public static final String PICTURE = "Tag_Num";

    private long tagNum;
    private Button backButt;

    public static Intent newIntent(Context packageContext, UUID picId) {
        Intent intent = new Intent(packageContext, ViewerActivity.class);
        intent.putExtra(PICTURE,picId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID picId = (UUID) getIntent().getSerializableExtra(PICTURE);
        return ViewerFragment.newInstance(picId);
    }
    /*
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        Log.d(VA, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_viewer);
        UUID picId = (UUID) getIntent().getSerializableExtra(PICTURE);
        myImagePager = (ViewPager) findViewById(R.id.activity_main);
        Intent viewIntent = getIntent();
        tagNum = viewIntent.getIntExtra(PICTURE, 0);
        Log.d(VA, String.format("tagNum = %d", tagNum));
        myImagePager.setBackgroundResource(Animals[(int)tagNum]);
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
    }*/
}
