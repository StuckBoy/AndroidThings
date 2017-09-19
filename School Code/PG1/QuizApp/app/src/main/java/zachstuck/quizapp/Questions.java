package zachstuck.quizapp;

/**
 * Created by Zachary Stuck on 1/26/2017.
 */

public class Questions {
    private int stringID;
    private boolean answer;

    public int getStringID() {
        return stringID;
    }

    public void setStringID(int stringID) {
        stringID = stringID;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        answer = answer;
    }

    public Questions (int i, boolean b) {
        stringID = i;
        answer = b;
    }
}
