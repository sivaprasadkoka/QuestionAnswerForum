package com.questionanswerforum.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.questionanswerforum.EndPointUrl;
import com.questionanswerforum.R;
import com.questionanswerforum.ResponseData;
import com.questionanswerforum.RetrofitInstance;
import com.questionanswerforum.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherLoginActivity extends AppCompatActivity {
    TextView tv_username,tv_password,tv_forgetpwd,tv_newuser,tv_signup;
    EditText et_USERNAME,et_PWD;
    Button btn_add;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherlogin);
        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        getSupportActionBar().setTitle("Teacher Login");
        tv_username=(TextView)findViewById(R.id.tv_username);
        tv_password=(TextView)findViewById(R.id.tv_password);
        tv_forgetpwd=(TextView)findViewById(R.id.tv_forgetpwd);
        tv_newuser=(TextView)findViewById(R.id.tv_newuser);
        tv_signup=(TextView)findViewById(R.id.tv_signup);
        et_USERNAME=(EditText) findViewById(R.id.et_USERNAME);
        et_PWD=(EditText)findViewById(R.id.et_PWD);
        btn_add=(Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_USERNAME.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter your User Name",Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_PWD.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter your Password",Toast.LENGTH_LONG).show();
                    return;
                }

                submitData();

            }
        });

        Typeface basicdt=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        tv_username.setTypeface(basicdt);
        tv_password.setTypeface(basicdt);
        tv_forgetpwd.setTypeface(basicdt);
        tv_newuser.setTypeface(basicdt);
        tv_signup.setTypeface(basicdt);
        et_PWD.setTypeface(basicdt);
        btn_add.setTypeface(basicdt);

    }
    private void submitData(){
        String username=et_USERNAME.getText().toString();
        String password=et_PWD.getText().toString();

        progressDialog = new ProgressDialog(TeacherLoginActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.teachers_login(username,password);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.body().status.equals("true")){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String uname = et_USERNAME.getText().toString();
                    editor.putString("user_name",uname);
                    editor.commit();
                    progressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), TeacherDashBoardActivity.class));
                    finish();
                }else{
                    Toast.makeText(TeacherLoginActivity.this,response.body().message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TeacherLoginActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}