package zachstuck.fragphotogallery;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.UUID;

import static android.support.v4.content.res.ResourcesCompat.getDrawable;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class BreedFragment extends Fragment {

    public static final String ARG_BREED_ID = "breed_id";
    private Breeds mBreeds;
    private ImageView picFrame;
    private Button button;

    public static BreedFragment newInstance(UUID breedId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BREED_ID, breedId);
        BreedFragment fragment = new BreedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID breedId = (UUID) getArguments().getSerializable(ARG_BREED_ID);
        mBreeds = Kennel.get(getActivity()).getBreed(breedId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewer, container, false);
        //Add picture here
        //Setup Button
        button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                getActivity().onBackPressed();
                //startActivity(new Intent(a.getContext(), BreedListActivity.class));
            }
        });
        picFrame = (ImageView) v.findViewById(R.id.picture_frame);
        Drawable imgRes = getResources().getDrawable(mBreeds.getBStringId());
        picFrame.setImageDrawable(imgRes);
        return v;
    }
}
