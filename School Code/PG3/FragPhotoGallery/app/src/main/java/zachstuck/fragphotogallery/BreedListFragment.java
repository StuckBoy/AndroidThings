package zachstuck.fragphotogallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.List;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class BreedListFragment extends Fragment {

    private RecyclerView mBreedRecyclerView;
    private BreedAdapter mAdapter;

    public static final String BLF = "BREED_LIST_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(BLF, "Creating breed list fragment");
        View view = inflater.inflate(R.layout.fragment_breed_list, container, false);
        mBreedRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mBreedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        Kennel kennel = Kennel.get(getActivity());
        List<Breeds> breeds = kennel.getBreeds();
        if(mAdapter == null) {
            mAdapter = new BreedAdapter(breeds);
            mBreedRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class BreedHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Breeds mBreed;
        private TextView mTitleTextView;

        public BreedHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_breed_text_view);
        }

        public void bindBreed (Breeds breed) {
            mBreed = breed;
            mTitleTextView.setText(mBreed.getName());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),mBreed.getName()+ " Clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = BreedPagerActivity.newIntent(getActivity(), mBreed.getmId());
            startActivity(intent);
        }
    }

    private class BreedAdapter extends RecyclerView.Adapter<BreedHolder> {
        private List<Breeds> mBreeds;

        public BreedAdapter(List<Breeds> breeds) {
            mBreeds = breeds;
        }

        @Override
        public BreedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_breed, parent, false);
            return new BreedHolder(view);
        }

        @Override
        public void onBindViewHolder(BreedHolder holder, int position) {
            Breeds breed = mBreeds.get(position);
            holder.bindBreed(breed);
        }

        @Override
        public int getItemCount() {
            return mBreeds.size();
        }
    }
}
