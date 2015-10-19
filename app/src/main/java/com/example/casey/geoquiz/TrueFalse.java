package com.example.casey.geoquiz;

/**
 * Created by casey on 9/10/15.
 */
public class TrueFalse {
    //local variables
    private int mQuestion;
    private boolean mTrueQuestion;

    //constructor
    public TrueFalse(int question, boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    //getters and setters
    public int getQuestion() { return mQuestion; }
    public void setQuestion(int question) { mQuestion = question; }

    public boolean isTrueQuestion() { return mTrueQuestion; }
    public void setTrueQuestion(boolean trueQuestion) { mTrueQuestion = trueQuestion; }
}
