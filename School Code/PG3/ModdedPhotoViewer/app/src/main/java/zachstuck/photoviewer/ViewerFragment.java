package zachstuck.photoviewer;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.UUID;

/**
 * Created by Zachary Stuck on 3/2/2017.
 */

public class ViewerFragment extends Fragment{

    public static final String VF = "VIEWER";
    public static final String PICTURE = "Tag_Num";

    private ImageView picFrame;
    private Button backButt;
    private ViewerFragment imgFragment;
    private Breeds breeds;

    public static ViewerFragment newInstance (UUID picId) {
        Bundle args = new Bundle();
        args.putSerializable(PICTURE,picId);
        ViewerFragment frag = new ViewerFragment();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID picId = (UUID) getArguments().getSerializable(PICTURE);
        breeds = Kennel.get(getActivity()).getBreed(picId);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewer,container,false);
        picFrame.setBackgroundResource(breeds.getBreedStringId());
        return v;
    }
    /*
    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == RE)
    }*/
}
