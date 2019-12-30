package com.aramtiraturyan.androidproject;

import androidx.annotation.NonNull;

public class QuizzQuestions {

    private String questionText;
    private String choice1;
    private String choice2;
    private String choice3;

    private String correctAnswer;
    private boolean creditAlreadyGiven;

    public QuizzQuestions(String questionText, String choice1, String choice2, String choice3, String correctAnswer) {
        this.questionText = questionText;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.correctAnswer = correctAnswer;
        this.creditAlreadyGiven = false;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isCreditAlreadyGiven() {
        return creditAlreadyGiven;
    }

    public void setCreditAlreadyGiven(boolean creditAlreadyGiven) {
        this.creditAlreadyGiven = creditAlreadyGiven;
    }

    public boolean isCorrectAnswer(String selectedAnswer){
        return (selectedAnswer.equals(correctAnswer));
    }


    @NonNull
    @Override
    public String toString(){
        return questionText;
    }
}
