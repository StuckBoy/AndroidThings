package zachstuck.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class QuizActivity extends AppCompatActivity {

    private Button buttonA, buttonB, nextButton;
    private TextView questionView;
    private Questions[] list = new Questions[] {
            new Questions(R.string.question_text1, false),
            new Questions(R.string.question_text2, true),
            new Questions(R.string.question_text3, false),
            new Questions(R.string.question_text4, true),
            new Questions(R.string.question_text5, false)
    };
    private Response[] resList = new Response[] {
            new Response(R.string.q1_response1), new Response(R.string.q1_response2),
            new Response(R.string.q2_response1), new Response(R.string.q2_response2),
            new Response(R.string.q3_response1), new Response(R.string.q3_response2),
            new Response(R.string.q4_response1), new Response(R.string.q4_response2),
            new Response(R.string.q5_response1), new Response(R.string.q5_response2)
    };

    private int current = 0;
    private int resCurrent = 0;
    private int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionView = (TextView) findViewById(R.id.questionView);
        questionView.setText(list[current].getStringID());
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonA.setText(resList[current].getResStringID());
        buttonA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View f) {
                GradeAnswer(true);
            }
        });
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonB.setText(resList[current+1].getResStringID());
        buttonB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View f) {
                GradeAnswer(false);
            }
        });
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View f) {
                current = (current+1)%list.length;
                resCurrent = resCurrent+2;
                if (resCurrent > 8){ resCurrent = 0; }
                questionView.setText(list[current].getStringID());
                buttonA.setText(resList[resCurrent].getResStringID());
                buttonB.setText(resList[resCurrent+1].getResStringID());
            }
        });
    }

    public void GradeAnswer (boolean ans) {
        if (ans == list[current].isAnswer()) {
            score = score + 1;
        }
        if (current == 4){
            Toast.makeText(QuizActivity.this, "Your score is:\n " + score + " out of 5 correct", Toast.LENGTH_LONG).show();
            score = 0;
        }
    }
}
