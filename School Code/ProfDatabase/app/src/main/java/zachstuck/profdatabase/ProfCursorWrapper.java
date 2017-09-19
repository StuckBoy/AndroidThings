package zachstuck.profdatabase;

import android.database.Cursor;
import android.database.CursorWrapper;
import zachstuck.profdatabase.DBScheme.ProfTable;

/**
 * Created by Zachary Stuck on 3/23/2017.
 */

public class ProfCursorWrapper extends CursorWrapper{

    public ProfCursorWrapper (Cursor cursor) {
        super (cursor);
    }

    public ProfStuff getStuff() {
        String ID = getString(getColumnIndex(ProfTable.Rauru.ID));
        String NAME = getString(getColumnIndex(ProfTable.Rauru.NAME));
        String EMAIL = getString(getColumnIndex(ProfTable.Rauru.EMAIL));
        String DEPT = getString(getColumnIndex(ProfTable.Rauru.DEPT));
        String OFFICE = getString(getColumnIndex(ProfTable.Rauru.OFFICE));
        int DBID = getInt(getColumnIndex("_id"));
        return new ProfStuff(ID, NAME, EMAIL, DEPT, OFFICE, DBID);
    }
}
