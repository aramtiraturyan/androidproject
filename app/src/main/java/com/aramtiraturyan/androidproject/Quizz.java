package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quizz extends AppCompatActivity {

    private RadioGroup rg_choices;
    private RadioButton rb_selected;
    private RadioButton rb_choice1;
    private RadioButton rb_choice2;
    private RadioButton rb_choice3;
    private TextView tv_question;

    private int currentQuestionIndex;
    private ArrayList<QuizzQuestions> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        this.initialize();
    }

    private void initialize(){
        rg_choices = findViewById(R.id.quizz_radio_group);
        rb_choice1 = findViewById(R.id.quizz_radio1);
        rb_choice2 = findViewById(R.id.quizz_radio2);
        rb_choice3 = findViewById(R.id.quizz_radio3);
        tv_question = findViewById(R.id.quizz_question);
        Button b_submit = findViewById(R.id.quizz_submit);

        currentQuestionIndex = 0;
        questions = new ArrayList<>();

        questions.add(new QuizzQuestions("Who owns the Android platform?", "Open Handset Alliance", "Dalvik", "Oracle", "a"));
        questions.add(new QuizzQuestions("What was the first phone released that ran the Android OS?", "Google gPhone", "T-Mobile G1", "HTC Hero", "b"));
        questions.add(new QuizzQuestions("When did Google purchase Android?", "2007", "2005", "2008", "b"));
        questions.add(new QuizzQuestions("Which one is not a nickname of a version of Andriod?", "Cupcake", "Muffin", "Gingerbread", "b"));
        questions.add(new QuizzQuestions("What operating system is used as the base of the Android stack?", "Java", "Linux", "Windows", "b"));
        questions.add(new QuizzQuestions("What does the .apk extension stand for?", "Application Package", "Application Proprietary Kit", "Android Package", "a"));
        questions.add(new QuizzQuestions("What is contained within the manifest.xml file?", "The permissions the app requires", "The list of strings used in the app", "The source code", "a"));
        questions.add(new QuizzQuestions("An activity can be thought of as corresponding to what?", "A Java project", "A Java class", "A method call", "b"));
        questions.add(new QuizzQuestions("The XML file that contains all the text that your application uses.", "Text.xml", "String.java", "Strings.xml", "c"));
        questions.add(new QuizzQuestions("Which one is the first search engine in internet?", "Google", "Archie", "Altavista", "b"));
        questions.add(new QuizzQuestions("Number of bit used by the IPv6 address", "32 bit", "64 bit", "128 bit", "c"));
        questions.add(new QuizzQuestions("Which one is the first web browser invented in 1990?", "Nexus", "Mozilla", "Internet Explorer", "a"));
        questions.add(new QuizzQuestions("First computer virus is known as", "Rabbit", "Creeper Virus", "Elk Cloner", "b"));
        questions.add(new QuizzQuestions("Which language is used exclusively for artificial intelligence?", "Python", "Java", "Prolog", "c"));
        questions.add(new QuizzQuestions("Firewall is used for:", "Security", "Authentification", "Monitoring", "a"));
        questions.add(new QuizzQuestions("Which one is not an OS", "Mac", "C", "Linux", "b"));
        questions.add(new QuizzQuestions("Which one is NOT a DB management software?", "MySQL", "Oracle", "COBOL", "c"));
        questions.add(new QuizzQuestions("Mac OS is developed by:", "IBM", "Apple", "Microsoft", "b"));
        questions.add(new QuizzQuestions("Hard Disk was first introduced in 1956 by:", "Dell", "Apple", "IBM", "c"));
        questions.add(new QuizzQuestions("Which Protocol is used to receive e-mail?", "SMTP", "POP 3", "FTP", "b"));

        this.displayQuestion( currentQuestionIndex);

        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(this.answerIsRight()){
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();

                    advance();
                }else {
                    Toast.makeText(getApplicationContext(),"The answer is wrong.", Toast.LENGTH_LONG).show();
                }


            }

            private boolean answerIsRight(){
                String answer = "x";
                int id = rg_choices.getCheckedRadioButtonId();
                rb_selected = findViewById(id);
                if (rb_selected == rb_choice1) answer = "a";
                if (rb_selected == rb_choice2) answer = "b";
                if (rb_selected == rb_choice3) answer = "c";
                return questions.get(currentQuestionIndex).isCorrectAnswer(answer);

            }

        });

    }

    private void displayQuestion(int index){
        tv_question.setText(questions.get(currentQuestionIndex).getQuestionText());
        rb_choice1.setText(questions.get(currentQuestionIndex).getChoice1());
        rb_choice2.setText(questions.get(currentQuestionIndex).getChoice2());
        rb_choice3.setText(questions.get(currentQuestionIndex).getChoice3());
        rg_choices.clearCheck();

    }

    private void advance(){
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
        displayQuestion(currentQuestionIndex);

    }

}
