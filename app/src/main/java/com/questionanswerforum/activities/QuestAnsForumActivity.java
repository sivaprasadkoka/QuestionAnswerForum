package com.questionanswerforum.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.questionanswerforum.R;

public class QuestAnsForumActivity extends AppCompatActivity {
    Button btn_postqstn,btn_viewans,btn_ansaquestion,btn_myqstnans;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questansforum);

        getSupportActionBar().setTitle("Question and Answers Forum");


        btn_postqstn=(Button)findViewById(R.id.btn_postqstn);
        //btn_viewans=(Button)findViewById(R.id.btn_viewans);
        btn_ansaquestion=(Button)findViewById(R.id.btn_ansaquestion);
        btn_myqstnans=(Button)findViewById(R.id.btn_myqstnans);

        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        btn_postqstn.setTypeface(fontstyle);
        //btn_viewans.setTypeface(fontstyle);
        btn_ansaquestion.setTypeface(fontstyle);
        btn_myqstnans.setTypeface(fontstyle);


        btn_postqstn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuestAnsForumActivity.this, PostQuestionActivity.class);
                startActivity(intent);
            }
        });

        btn_ansaquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuestAnsForumActivity.this, ListOfQuestionsActivity.class);
                startActivity(intent);

            }
        });

       /* btn_viewans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuestAnsForumActivity.this, GetAnswersActivity.class);
                startActivity(intent);

            }
        });*/
        btn_myqstnans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuestAnsForumActivity.this, MyAnswersActivity.class);
                startActivity(intent);

            }
        });

    }
}
