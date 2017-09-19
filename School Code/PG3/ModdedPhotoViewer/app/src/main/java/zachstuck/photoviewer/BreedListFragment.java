package zachstuck.photoviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class BreedListFragment extends Fragment {

    public static final String BLF = "BREED_LIST_FRAGMENT";
    private RecyclerView myRecyclerView;
    private BreedAdapter myAdapter;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        Log.d(BLF, "onCreateView");
        View view = inflater.inflate(R.layout.activity_main,containter,false);
        myRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_container);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        Kennel myKennel = Kennel.get(getActivity());
        Breeds[] breeds = myKennel.getBreeds();
        if (myAdapter == null) {
            myAdapter = new BreedAdapter(breeds);
        }
    }

    private class BreedHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Breeds myBreeds;

        public BreedHolder(View itemView) {
            super (itemView);
            itemView.setOnClickListener(this);

        }

        public void bindBreed(Breeds breeds) {
            myBreeds = breeds;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),myBreeds.getBreedStringId()+ " Clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = BreedPagerActivity.newIntent(getActivity(),myBreeds.getbId());
            startActivity(intent);
        }
    }

    private class BreedAdapter extends RecyclerView.Adapter<BreedHolder> {

        private Breeds[] mbreeds;

        public BreedAdapter(Breeds[] breeds) {
            mbreeds = breeds;
        }

        @Override
        public BreedHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.activity_main,parent,false);
            return new BreedHolder(view);
        }

        @Override
        public void onBindViewHolder (BreedHolder holder, int position) {
            Breeds breed = mbreeds[position];
            holder.bindBreed(breed);
        }

        @Override
        public int getItemCount() {
            return mbreeds.length;
        }
    }
}
