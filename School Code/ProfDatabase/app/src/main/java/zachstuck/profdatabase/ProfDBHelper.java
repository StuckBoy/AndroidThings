package zachstuck.profdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import zachstuck.profdatabase.DBScheme.ProfTable;

/**
 * Created by Zachary Stuck on 3/23/2017.
 */

public class ProfDBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "proffesors.db";

    public ProfDBHelper (Context context) {
        super (context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL("create table"+ProfTable.NAME+"("+" _id integer primary key autoincrement, "+ProfTable.Rauru.ID+
                ", "+ProfTable.Rauru.NAME+", "+ProfTable.Rauru.EMAIL+", "+ProfTable.Rauru.DEPT+", "+ProfTable.Rauru.OFFICE+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void remove (Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
