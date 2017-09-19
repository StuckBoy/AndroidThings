package zachstuck.fragphotogallery;

import android.content.Context;
import android.text.style.UpdateAppearance;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class Kennel {

    private static Kennel UPaws;
    private List<Breeds> mBreeds;
    private String[] bNames = new String[] {
        "Calico", "CatDog", "Cheshire", "Corgi", "Greyhound", "Husky",
            "Labrador", "Maine Coone", "Pomeranian", "Siamese"
    };

    private int[] bInts = new int[] {
            R.drawable.calico, R.drawable.catdog, R.drawable.cheshire, R.drawable.corgi,
            R.drawable.greyhound, R.drawable.husky, R.drawable.labrador,
            R.drawable.mainecoon, R.drawable.pomeranian, R.drawable.siamese
    };

    public static Kennel get(Context context) {
        if(UPaws == null) {
            UPaws = new Kennel(context);
        }
        return UPaws;
    }

    public List<Breeds> getBreeds() {
        return mBreeds;
    }

    public Breeds getBreed(UUID Id) {
        for(Breeds breed: mBreeds){
            if(breed.getmId().equals(Id)) {
                return breed;
            }
        }
        return null;
    }

    private Kennel(Context context) {
        mBreeds = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Breeds breed = new Breeds();
            breed.setName(bNames[i]);
            breed.setBStringId(bInts[i]);
            mBreeds.add(breed);
        }
    }
}
