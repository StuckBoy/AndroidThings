package zachstuck.fragphotogallery;

import java.util.UUID;

/**
 * Created by Zachary Stuck on 4/6/2017.
 */

public class Breeds {

    private UUID mId;
    private String mName;
    private int bStringId;

    public Breeds() {
        mId = UUID.randomUUID();
    }

    public UUID getmId() {
        return mId;
    }

    public void setName(String Title) {
        mName = Title;
    }

    public String getName() {
        return mName;
    }

    public void setBStringId(int stringId) {
        bStringId = stringId;
    }

    public int getBStringId() {
        return bStringId;
    }

    public void setmId(UUID Id) {
        mId = Id;
    }
}
