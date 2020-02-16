package com.questionanswerforum.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class PostQuestionActivity extends AppCompatActivity {
    EditText et_subject,et_question,et_tags;
    Button btn_submit;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectqstn);

        getSupportActionBar().setTitle("Post Question");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_question=(EditText)findViewById(R.id.et_question);
        et_subject=(EditText)findViewById(R.id.et_subject);
        et_tags=(EditText)findViewById(R.id.et_tags);
        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);




        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
    }
    private void submitData(){
        String subject=et_subject.getText().toString();
        String question=et_question.getText().toString();

        String tags=et_tags.getText().toString();
        uname = sharedPreferences.getString("user_name", ""); // Second parameter is the default val

        progressDialog = new ProgressDialog(PostQuestionActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.postqstn(subject,question,uname,tags);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.body().status.equals("true")){
                    Toast.makeText(PostQuestionActivity.this,response.body().message,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), QuestAnsForumActivity.class));
                    finish();
                }else{
                    Toast.makeText(PostQuestionActivity.this,response.body().message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostQuestionActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    //add this method in your program
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
