package com.questionanswerforum.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.questionanswerforum.R;
import com.questionanswerforum.Utils;

public class StudentDashBoardActivity extends AppCompatActivity {
    Button btn_postqstn,btn_viewans,btn_ansaquestion,btn_myqstnans,btn_logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questansforum);

        getSupportActionBar().setTitle("Student Dashboard");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btn_postqstn=(Button)findViewById(R.id.btn_postqstn);
        //btn_viewans=(Button)findViewById(R.id.btn_viewans);
        btn_ansaquestion=(Button)findViewById(R.id.btn_ansaquestion);
        btn_myqstnans=(Button)findViewById(R.id.btn_myqstnans);
        btn_logout=(Button)findViewById(R.id.btn_logout);

        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        btn_postqstn.setTypeface(fontstyle);
        //btn_viewans.setTypeface(fontstyle);
        btn_ansaquestion.setTypeface(fontstyle);
        btn_myqstnans.setTypeface(fontstyle);


        btn_postqstn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentDashBoardActivity.this, PostQuestionActivity.class);
                startActivity(intent);
            }
        });

        btn_ansaquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentDashBoardActivity.this, ListOfQuestionsActivity.class);
                startActivity(intent);

            }
        });


        btn_myqstnans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentDashBoardActivity.this, MyAnswersActivity.class);
                startActivity(intent);

            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentDashBoardActivity.this, FirstScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                Intent admin = new Intent(getApplicationContext(), FirstScreenActivity.class);
                startActivity(admin);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
