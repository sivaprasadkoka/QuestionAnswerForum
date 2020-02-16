package com.questionanswerforum.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.questionanswerforum.Pojo.GetAnswersPojo;
import com.questionanswerforum.R;
import com.questionanswerforum.activities.AddCommentActivity;
import com.questionanswerforum.activities.ViewCommentActivity;

import java.util.List;

public class GetAnswersAdapter extends RecyclerView.Adapter<GetAnswersAdapter.MyviewHolder> {

    Context context;
    List<GetAnswersPojo> a1;

    public GetAnswersAdapter(Context context, List<GetAnswersPojo> movieList) {
        this.context = context;
        this.a1 = movieList;
    }

    public void setMovieList(List<GetAnswersPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.getanswers_child, parent, false);
        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int position) {
        holder.tv_answer.setText("Answer Is:" + a1.get(position).getAnswer());
        holder.tv_answerby.setText("Posted By   :" + a1.get(position).getAnswer_by());
        holder.tv_answer1by.setText("Answer By   :" + a1.get(position).getAnswer_by());


        holder.btn_add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, AddCommentActivity.class);
                intent.putExtra("id",a1.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.btn_view_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ViewCommentActivity.class);
                intent.putExtra("id",a1.get(position).getId());
                context.startActivity(intent);

            }
        });





    }
    @Override
    public int getItemCount() {
        if (a1 != null) {
            return a1.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_answer,tv_answerby,tv_answer1by;
        Button btn_add_comment,btn_view_comment;

        public MyviewHolder(View itemView) {
            super(itemView);

            tv_answer = (TextView) itemView.findViewById(R.id.tv_answer);
            tv_answerby = (TextView) itemView.findViewById(R.id.tv_answerby);

            tv_answer1by = (TextView) itemView.findViewById(R.id.tv_answer1by);

            btn_add_comment = (Button) itemView.findViewById(R.id.btn_add_comment);
            btn_view_comment = (Button) itemView.findViewById(R.id.btn_view_comment);


        }
    }
}