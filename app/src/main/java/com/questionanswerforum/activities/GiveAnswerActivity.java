package com.questionanswerforum.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.questionanswerforum.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiveAnswerActivity extends AppCompatActivity {
    TextView tv_qstn_id,tv_postby;
    EditText et_answer;
    String sub,qstn,qustn_ID,post_by;
    Button btn_submit;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveanswer);

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
        qstn=i.getStringExtra("qstn");
        sub=i.getStringExtra("sub_name");
        qustn_ID=i.getStringExtra("qstn_id");
        tv_qstn_id.setText("Subject Title   ::"+sub);
        tv_postby.setText("Question     ::"+qstn);


    }

    private void submitData() {
        String ans = et_answer.getText().toString();

        progressDialog = new ProgressDialog(GiveAnswerActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.post_answer(qustn_ID,ans,sharedPreferences.getString("user_name","def"));
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(GiveAnswerActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(GiveAnswerActivity.this, QuestAnsForumActivity.class));

                } else {
                    Toast.makeText(GiveAnswerActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GiveAnswerActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
