package com.questionanswerforum.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.questionanswerforum.R;

public class FirstScreenActivity extends AppCompatActivity {
    Button btn_admin,btn_student,btn_teacher;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstscreen);

        btn_admin=(Button)findViewById(R.id.btn_admin);
        btn_student=(Button)findViewById(R.id.btn_student);
        btn_teacher=(Button)findViewById(R.id.btn_teacher);

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstScreenActivity.this,AdminLoginActivity.class);
                startActivity(intent);

            }
        });

        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstScreenActivity.this,StudentLoginActivity.class);
                startActivity(intent);

            }
        });


        btn_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstScreenActivity.this,TeacherLoginActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.admin_login:
                Intent admin = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(admin);

                return true;
            case R.id.student_login:
                Intent student = new Intent(getApplicationContext(), StudentLoginActivity.class);
                startActivity(student);
                return true;
            case R.id.teacher_login:
                Intent teacher = new Intent(getApplicationContext(), TeacherLoginActivity.class);
                startActivity(teacher);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
