package zachstuck.photoviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.UUID;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class BreedPagerActivity extends FragmentActivity {

    public static final String EX_BREED_ID = "zachstuck.photoviewer.breedintent.breed_id";
    private ViewPager myViewPager;
    private Breeds[] myBreeds;
    private static final String BPA = "BREED_PAGER_ACTIVITY";

    public static Intent newIntent(Context packageContext, UUID breedId) {
        Intent intent = new Intent(packageContext,BreedPagerActivity.class);
        intent.putExtra(EX_BREED_ID, breedId);
        return intent;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        Log.d(BPA, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_pager);
        final UUID breedId = (UUID) getIntent().getSerializableExtra(EX_BREED_ID);
        myViewPager = (ViewPager) findViewById(R.id.activity_breed_pager_view_pager);
        myBreeds = Kennel.get(this).getBreeds();
        FragmentManager fragmentManager = getSupportFragmentManager();
        myViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Breeds breeds = myBreeds[position];
                return BreedFragment.newInstance(breeds.getbId());
            }

            @Override
            public int getCount() {
                return myBreeds.length;
            }
        });
    }
}
