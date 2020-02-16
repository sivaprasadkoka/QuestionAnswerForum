package com.questionanswerforum.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.questionanswerforum.EndPointUrl;
import com.questionanswerforum.Pojo.QuestionsPojo;
import com.questionanswerforum.R;
import com.questionanswerforum.RetrofitInstance;
import com.questionanswerforum.Utils;
import com.questionanswerforum.adapters.ListOfQuestionsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfQuestionsActivity extends AppCompatActivity {
    List<QuestionsPojo> a1;
    RecyclerView recyclerView;
    ListOfQuestionsAdapter recyclerAdapter;
    SharedPreferences sharedPreferences;
    String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_parent);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname=sharedPreferences.getString("user_name","");

        getSupportActionBar().setTitle("List Of Questions");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        a1 = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL
                ,false);recyclerView.setLayoutManager(linearLayoutManager);


        recyclerAdapter = new ListOfQuestionsAdapter(ListOfQuestionsActivity.this,a1);
        recyclerView.setAdapter(recyclerAdapter);

        EndPointUrl apiService = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<QuestionsPojo>> call = apiService.getAllQuestions(uname);
        call.enqueue(new Callback<List<QuestionsPojo>>() {
            @Override
            public void onResponse(Call<List<QuestionsPojo>> call, Response<List<QuestionsPojo>> response) {
                a1 = response.body();
                Log.d("TAG","Response = "+a1);
                recyclerAdapter.setMovieList(a1);
            }

            @Override
            public void onFailure(Call<List<QuestionsPojo>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
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


