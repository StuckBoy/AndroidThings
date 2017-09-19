package zachstuck.photoviewer;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.UUID;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class BreedFragment extends Fragment {

    public static final String ARG_BREED_ID = "breed_id";
    public static final String BF = "BREED_FRAGMENT";
    private Breeds breed;
    private Button backButt;
    private ImageView picFrame;

    public static BreedFragment newInstance(UUID breedId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BREED_ID,breedId);
        BreedFragment fragment = new BreedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(BF, "onCreate");
        super.onCreate(savedInstanceState);
        UUID breedId = (UUID) getArguments().getSerializable(ARG_BREED_ID);
        breed = Kennel.get(getActivity()).getBreed(breedId);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewer, container, false);
        return v;
    }
}
