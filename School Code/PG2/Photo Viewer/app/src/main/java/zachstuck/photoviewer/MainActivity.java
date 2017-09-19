package zachstuck.photoviewer;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String DV = "DIRECTORYVIEW";
    public static final String PICTURE = "Tag_Num";
    public static final String DIRECTORY = "directory_name";

    private String direcName;
    private Button buttArray[] = new Button[10];
    private Breeds[] breeds = new Breeds[] {
            new Breeds(R.string.Calico), new Breeds(R.string.CatDog), new Breeds(R.string.Cheshire), new Breeds(R.string.Corgi),
            new Breeds(R.string.Greyhound), new Breeds(R.string.Husky), new Breeds(R.string.Labrador),
            new Breeds(R.string.MaineCoone), new Breeds(R.string.Pomeranian), new Breeds(R.string.Siamese)
    };

    private String reference = " ";
    private int refNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d (DV,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        if(savedInstanceState != null){
            direcName = savedInstanceState.getString(DIRECTORY, direcName);
        }*/

        //buttArray[0] = (Button) findViewById(R.id.A);
        //buttArray[0].setText(breeds[0].getBreedStringId());
        for(int i = 0; i < 10; i++) {
            final String buttId = "butt" + (i+1);
            int resId = getResources().getIdentifier(buttId, "id", getPackageName());
            buttArray[i] = ((Button) findViewById(resId));
            buttArray[i].setText(breeds[i].getBreedStringId());
            buttArray[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, 70);
            buttArray[i].setTag(i+1);
            buttArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int picNum = Integer.parseInt(v.getTag().toString());
                    Intent thisIntent = new Intent(MainActivity.this, ViewerActivity.class);
                    thisIntent.putExtra(PICTURE, picNum);
                    startActivity(thisIntent);
                }
            });
        }
    }

    @Override
    public void onStart() {
        Log.d (DV,"onStart");
        super.onStart();
    }
    @Override
    public void onRestart() {
        Log.d (DV,"onRestart");
        super.onRestart();
    }
    @Override
    public void onPause() {
        Log.d (DV,"onPause");
        super.onPause();
    }
    @Override
    public void onResume() {
        Log.d (DV,"onResume");
        super.onResume();
    }
    @Override
    public void onDestroy() {
        Log.d (DV,"onDestroy");
        super.onDestroy();
    }
    @Override
    public void onStop() {
        Log.d (DV,"onStop");
        super.onStop();
    }

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState) {
        Log.d (DV,"onSavedInstanceState");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(DIRECTORY, direcName);
    }

}
