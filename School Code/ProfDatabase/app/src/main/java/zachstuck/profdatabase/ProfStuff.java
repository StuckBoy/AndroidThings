package zachstuck.profdatabase;

/**
 * Created by Zachary Stuck on 3/23/2017.
 */

public class ProfStuff {

    private String mID, mName, mDept, mOffice, mMail;
    private int mDBID;

    public int getDBID() { return mDBID; }
    public void setDBID(int DBID) { mDBID = DBID; }

    public String getID() { return mID; }
    public void setID(String ID) { mID = ID; }

    public String getName() { return mName; }
    public void setName(String name) { mName = name; }

    public String getDept() { return mDept; }
    public void setDept(String dept) { mDept = dept;}

    public String getOffice() { return mOffice; }
    public void setOffice(String office) { mOffice = office;}

    public String getMail() { return mMail; }
    public void setMail(String mail) { mMail = mail; }

    public ProfStuff(String ID, String NAME, String DEPT, String OFFICE, String MAIL, int DBID) {
        mID = ID;
        mName = NAME;
        mDept = DEPT;
        mOffice = OFFICE;
        mMail = MAIL;
        mDBID = DBID;
    }
}
