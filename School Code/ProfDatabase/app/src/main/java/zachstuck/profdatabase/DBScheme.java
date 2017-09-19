package zachstuck.profdatabase;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zachary Stuck on 3/23/2017.
 */

public class DBScheme {

    public static final class ProfTable {

        public static final String NAME = "Professors";
        public static final class Rauru {

            public static final String ID = "ProfID";
            public static final String NAME = "ProfName";
            public static final String EMAIL = "ProfMail";
            public static final String DEPT = "ProfDept";
            public static final String OFFICE = "ProfOffice";
        }
    }
}
