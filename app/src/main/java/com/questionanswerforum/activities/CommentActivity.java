package com.questionanswerforum.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {
    TextView tv_qstn_id,tv_postby;
    EditText et_answer;
    String sub,qstn,qustn_ID,post_by;
    Button btn_submit;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        getSupportActionBar().setTitle("Answer");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_qstn_id=(TextView)findViewById(R.id.tv_qstn_id);
        tv_postby=(TextView)findViewById(R.id.tv_postby);
        et_answer=(EditText) findViewById(R.id.et_answer);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        tv_qstn_id.setTypeface(fontstyle);
        tv_postby.setTypeface(fontstyle);
        et_answer.setTypeface(fontstyle);
        btn_submit.setTypeface(fontstyle);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();

            }
        });

        Intent i=getIntent();
        post_by=i.getStringExtra("post_by");
        qstn=i.getStringExtra("sub_name");
        sub=i.getStringExtra("qstn");
        qustn_ID=i.getStringExtra("qstn_id");
        tv_qstn_id.setText("Sbbject Name   ::"+sub);
        tv_postby.setText("Question     ::"+qstn);


    }

    private void submitData() {
        String ans = et_answer.getText().toString();

        progressDialog = new ProgressDialog(CommentActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.post_answer(qustn_ID,ans,post_by);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(CommentActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CommentActivity.this, QuestAnsForumActivity.class));

                } else {
                    Toast.makeText(CommentActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CommentActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
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
