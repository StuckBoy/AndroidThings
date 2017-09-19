package zachstuck.photoviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class BreedActivity extends SingleFragmentActivity {

    public static final String EX_BREED_ID = "zachstuck.photoviewer.breed_id";

    public static Intent newIntent(Context packageContext, UUID breedId) {
        Intent intent = new Intent(packageContext,BreedActivity.class);
        intent.putExtra(EX_BREED_ID,breedId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID breedId = (UUID) getIntent().getSerializableExtra(EX_BREED_ID);
        return BreedFragment.newInstance(breedId);
    }
}
