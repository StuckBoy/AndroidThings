package zachstuck.photoviewer;

import android.content.Context;
import java.util.UUID;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class Kennel {

    private static Kennel dogKennel;
    private Breeds[] breeds;

    private Kennel(Context context) {
            breeds = new Breeds[] {
                    new Breeds(R.string.Calico), new Breeds(R.string.CatDog), new Breeds(R.string.Cheshire), new Breeds(R.string.Corgi),
                    new Breeds(R.string.Greyhound), new Breeds(R.string.Husky), new Breeds(R.string.Labrador),
                    new Breeds(R.string.MaineCoone), new Breeds(R.string.Pomeranian), new Breeds(R.string.Siamese)
            };
    }

    public static Kennel get(Context context) {
        if(dogKennel == null) dogKennel = new Kennel(context);
        return dogKennel;
    }

    public Breeds[] getBreeds() { return breeds; }

    public Breeds getBreed (UUID id) {
        for (Breeds breed: breeds){
            if(breed.getbId().equals(id)) return breed;
        }
        return null;
    }
}
