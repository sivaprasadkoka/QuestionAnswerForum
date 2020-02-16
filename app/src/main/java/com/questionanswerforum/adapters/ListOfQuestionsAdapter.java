package com.questionanswerforum.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.questionanswerforum.activities.GetAnswersActivity;
import com.questionanswerforum.activities.GiveAnswerActivity;
import com.questionanswerforum.Pojo.QuestionsPojo;
import com.questionanswerforum.R;

import java.util.List;

public class ListOfQuestionsAdapter extends RecyclerView.Adapter<ListOfQuestionsAdapter.MyviewHolder> {

    Context context;
    List<QuestionsPojo> a1;

    public ListOfQuestionsAdapter(Context context, List<QuestionsPojo> movieList) {
        this.context = context;
        this.a1 = movieList;
    }

    public void setMovieList(List<QuestionsPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listofquestions_child,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int position) {
        holder.tv_postedby.setText("Post By  :"+a1.get(position).getPost_by());
        holder.tv_subject.setText("Subject Title  :"+a1.get(position).getSubject());
        holder.tv_qstn.setText("Question  :"+a1.get(position).getQuestion());
        holder.tv_tags.setText("Tags  :"+a1.get(position).getTags());


        holder.btn_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, GiveAnswerActivity.class);
                intent.putExtra("qstn_id",a1.get(position).getId());
                intent.putExtra("sub_name",a1.get(position).getSubject());
                intent.putExtra("qstn",a1.get(position).getQuestion());
                intent.putExtra("tags",a1.get(position).getTags());
                intent.putExtra("post_by",a1.get(position).getPost_by());
                context.startActivity(intent);
            }
        });

        holder.btn_view_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, GetAnswersActivity.class);
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
        TextView tv_postedby,tv_subject,tv_qstn,tv_tags;
        Button btn_ans,btn_view_ans;

        public MyviewHolder(View itemView) {
            super(itemView);
            btn_ans=(Button)itemView.findViewById(R.id.btn_ans);
            btn_view_ans=(Button)itemView.findViewById(R.id.btn_view_ans);
            tv_postedby = (TextView) itemView.findViewById(R.id.tv_postedby);
            tv_subject = (TextView) itemView.findViewById(R.id.tv_subject);
            tv_qstn = (TextView) itemView.findViewById(R.id.tv_qstn);
            tv_tags = (TextView) itemView.findViewById(R.id.tv_tags);

        }
    }
}