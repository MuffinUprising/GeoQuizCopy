package com.example.casey.geoquiz;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;
import android.content.Intent;

public class QuizActivity extends Activity {

    //life cycle log
    private static final String TAG = "QuizActivity";
    //key index string
    private static final String KEY_INDEX = "index";

    //local variables
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    //question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };


    //set cheater to false
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    //update question method
    private void updateQuestion() {
//        Log.d(TAG, "Updating question text for question #" + mCurrentIndex, new Exception());
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    //check answer method
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        //in mIsCheater, bring up judgement toast
        if (mIsCheater) {
            messageResId = R.string.judgement_toast;

        } else {
            //if true is correct, display correct toast..
            if(userPressedTrue == answerIsTrue) {
                messageResId =R.string.correct_toast;

                //if true is incorrect, display incorrect toast
            } else {
                messageResId =R.string.incorrect_toast;
            }
        }

        //make toast
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


    @TargetApi(11)
    @Override
    //onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setSubtitle("Bodies of Water");
        }

        //question text view
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        //true button
        mTrueButton = (Button)findViewById(R.id.true_button);
        //listener
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });

        //false button
        mFalseButton = (Button)findViewById(R.id.false_button);
        //listener
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);

            }
        });

        //next button
        mNextButton = (Button)findViewById(R.id.next_button);
        //listener
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        //save state for orientation change
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        //cheat button
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent telling the ActivityManager to start CheatActivity
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                //put and extra on intent
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(i, 0);
            }
        });

        updateQuestion();
    }

    //override onSaveInstance State class
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    //override onStart class
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    //override onPause class
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    //override onResume class
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    //override onStop class
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    //override onDestroy class
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    //On create options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    //on options item selected
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
