package zachstuck.fragphotogallery;

import android.support.v4.app.Fragment;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class BreedListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BreedListFragment();
    }
}
