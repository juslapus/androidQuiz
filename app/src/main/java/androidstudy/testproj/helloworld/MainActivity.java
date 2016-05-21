package androidstudy.testproj.helloworld;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageView mNextButton;
    private ImageView mPreviousButton;
    private TextView mQuestion;

    private static final String TAG = "QuizActivity";

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;
    private boolean mIsCorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }

        mQuestion = (TextView) findViewById(R.id.question_textView);
        int questionID = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestion.setText(questionID);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
                                           ;
                                           @Override
                                           public void onClick(View v) {
                                               checkAnswer(true);
                                           }
                                       });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            ;
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageView) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            ;
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        mPreviousButton = (ImageView) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            ;
            @Override
            public void onClick(View v) {
                previousQuestion();
            }
        });
}

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public void nextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        refreshText();
    }

    public void previousQuestion() {
        mCurrentIndex = (mCurrentIndex - 1);
        if (mCurrentIndex < 0) {
            mCurrentIndex = mQuestionBank.length - 1;
        }
        refreshText();
    }

    public void refreshText() {
        int questionID = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestion.setText(questionID);
    }

    public boolean checkAnswer(boolean pAnswer) {
        boolean answerKey = mQuestionBank[mCurrentIndex].isAnswerTrue();
        mIsCorrect = (answerKey == pAnswer);
        fireToast();
        return mIsCorrect;
    }

    public void fireToast() {
        if (mIsCorrect) {
            Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
