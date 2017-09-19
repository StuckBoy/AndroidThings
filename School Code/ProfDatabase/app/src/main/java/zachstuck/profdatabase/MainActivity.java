package zachstuck.profdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import zachstuck.profdatabase.DBScheme.ProfTable;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        ProfDBHelper.remove(mContext);
        mDatabase = new ProfDBHelper(mContext).getWritableDatabase();

    }

    private ProfCursorWrapper query(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(ProfTable.NAME,null,whereClause,whereArgs,null,null,null);
        return  new ProfCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(int wh) {
        ContentValues values = new ContentValues();
        switch (wh) {
            case 0:
                values.put(ProfTable.Rauru.ID, "01100001");
                values.put(ProfTable.Rauru.NAME, "Andy Poe");
                values.put(ProfTable.Rauru.EMAIL, "apoe@nmu.edu");
                values.put(ProfTable.Rauru.DEPT, "Computer Science");
                values.put(ProfTable.Rauru.OFFICE, "JXJ 2230");
                break;
            case 1:
                values.put(ProfTable.Rauru.ID, "01100010");
                values.put(ProfTable.Rauru.NAME, "Randy Appleton");
                values.put(ProfTable.Rauru.EMAIL, "rappleto@nmu.edu");
                values.put(ProfTable.Rauru.DEPT, "Computer Science");
                values.put(ProfTable.Rauru.OFFICE, "JXJ 2210");
                break;
            case 2:
                values.put(ProfTable.Rauru.ID, "01100011");
                values.put(ProfTable.Rauru.NAME, "Jeffrey Horn");
                values.put(ProfTable.Rauru.EMAIL, "jhorn@nmu.edu");
                values.put(ProfTable.Rauru.DEPT, "Computer Science");
                values.put(ProfTable.Rauru.OFFICE, "JXJ 2202");
                break;
            case 3:
                values.put(ProfTable.Rauru.ID, "01100100");
                values.put(ProfTable.Rauru.NAME, "John Sarkela");
                values.put(ProfTable.Rauru.EMAIL, "jsarkela@nmu.edu");
                values.put(ProfTable.Rauru.DEPT, "Computer Science");
                values.put(ProfTable.Rauru.OFFICE, "JXJ 2217");
                break;
        }
        return values;
    }
}
