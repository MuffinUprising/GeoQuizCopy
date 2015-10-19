package com.example.casey.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by casey on 9/14/15.
 */
public class CheatActivity extends Activity {
    //local variables
    public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geo_quiz.answer_shown";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    //set answer shown result with intent and extra
    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        //Use extra for intent
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        //instantiate mAnswerTextView and mShowAnswer
        mAnswerTextView = (TextView)findViewById(R.id.showAnswerButton);
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);

        setAnswerShownResult(false);

        //listener for mShowAnswer
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if true set mAnswerTextView to true_button text
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                //else, false button text
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }
}
