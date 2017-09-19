package zachstuck.fragphotogallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.List;
import java.util.UUID;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class BreedPagerActivity extends FragmentActivity {

    public static final String EXTRA_BREED_ID = "zachstuck.breedintent.breed_id";
    public static final String BPA = "BREED_PAGER_ACTIVITY";
    private ViewPager mViewPager;
    private List<Breeds> mBreeds;

    public static Intent newIntent(Context packageContext, UUID breedId) {
        Intent intent = new Intent(packageContext, BreedPagerActivity.class);
        intent.putExtra(EXTRA_BREED_ID, breedId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(BPA, "Creating pager activity");
        setContentView(R.layout.activity_breed_pager);
        UUID breedId = (UUID) getIntent().getSerializableExtra(EXTRA_BREED_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_breed_pager_view_pager);
        mBreeds = Kennel.get(this).getBreeds();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Breeds breed = mBreeds.get(position);
                return BreedFragment.newInstance(breed.getmId());
            }

            @Override
            public int getCount() {
                return mBreeds.size();
            }
        });
        for(int i = 0; i < mBreeds.size(); i++) {
            if(mBreeds.get(i).getmId().equals(breedId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
