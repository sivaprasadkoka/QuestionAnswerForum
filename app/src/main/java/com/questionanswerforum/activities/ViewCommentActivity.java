package com.questionanswerforum.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.questionanswerforum.EndPointUrl;
import com.questionanswerforum.Pojo.CommentsPojo;
import com.questionanswerforum.Pojo.GetAnswersPojo;
import com.questionanswerforum.R;
import com.questionanswerforum.RetrofitInstance;
import com.questionanswerforum.adapters.GetAnswersAdapter;
import com.questionanswerforum.adapters.ViewCommentsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCommentActivity extends AppCompatActivity {
    List<CommentsPojo> a1;
    RecyclerView recyclerView;
    ViewCommentsAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_parent);

        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Toast.makeText(getApplicationContext(),getIntent().getStringExtra("id"),Toast.LENGTH_LONG).show();
        a1 = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL
                ,false);recyclerView.setLayoutManager(linearLayoutManager);


        recyclerAdapter = new ViewCommentsAdapter(ViewCommentActivity.this,a1);
        recyclerView.setAdapter(recyclerAdapter);

        EndPointUrl apiService = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<CommentsPojo>> call = apiService.getComments(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<List<CommentsPojo>>() {
            @Override
            public void onResponse(Call<List<CommentsPojo>> call, Response<List<CommentsPojo>> response) {
                a1 = response.body();
                //Log.d("TAG","Response = "+a1);
                recyclerAdapter.setMovieList(a1);
            }

            @Override
            public void onFailure(Call<List<CommentsPojo>> call, Throwable t) {
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

