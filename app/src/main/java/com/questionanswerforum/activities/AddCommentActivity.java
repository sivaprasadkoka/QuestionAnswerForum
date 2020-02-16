package com.questionanswerforum.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCommentActivity extends AppCompatActivity {
    EditText et_comment;
    Button btn_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        et_comment=(EditText)findViewById(R.id.et_comment);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData(getIntent().getStringExtra("id"),et_comment.getText().toString());
            }
        });
    }
    ProgressDialog progressDialog;

    private void submitData(String id,String comment){
        progressDialog = new ProgressDialog(AddCommentActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.add_comment(id,comment);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                Toast.makeText(AddCommentActivity.this,"Comment is added successfully.",Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddCommentActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
