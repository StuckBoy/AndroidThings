package zachstuck.photoviewer;

import java.util.UUID;

/**
 * Created by Zachary Stuck on 2/14/2017.
 */

public class Breeds {
    private int breedStringId;
    private UUID bId;

    public int getBreedStringId(){
        return breedStringId;
    }

    public Breeds(int b) {
        breedStringId = b;
        bId = UUID.randomUUID();
    }

    public UUID getbId() {
        return bId;
    }
}
