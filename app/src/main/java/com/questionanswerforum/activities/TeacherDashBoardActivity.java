package com.questionanswerforum.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.questionanswerforum.R;

public class TeacherDashBoardActivity extends AppCompatActivity {
    Button btn_ansaquestion,btn_logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        getSupportActionBar().setTitle("Teacher Dashboard");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_ansaquestion=(Button)findViewById(R.id.btn_ansaquestion);
        btn_logout=(Button)findViewById(R.id.btn_logout);
        btn_ansaquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherDashBoardActivity.this, ListOfQuestionsActivity.class);
                startActivity(intent);

            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherDashBoardActivity.this, FirstScreenActivity.class);
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
