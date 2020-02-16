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

import com.questionanswerforum.Pojo.CommentsPojo;
import com.questionanswerforum.Pojo.GetAnswersPojo;
import com.questionanswerforum.R;
import com.questionanswerforum.activities.AddCommentActivity;

import java.util.List;

public class ViewCommentsAdapter extends RecyclerView.Adapter<ViewCommentsAdapter.MyviewHolder> {

    Context context;
    List<CommentsPojo> a1;

    public ViewCommentsAdapter(Context context, List<CommentsPojo> movieList) {
        this.context = context;
        this.a1 = movieList;
    }

    public void setMovieList(List<CommentsPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_comments, parent, false);
        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int position) {
        holder.tv_comment.setText("Comment : " + a1.get(position).comment);
    }
    @Override
    public int getItemCount() {
        if (a1 != null) {
            return a1.size();
        }
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_comment;
        public MyviewHolder(View itemView) {
            super(itemView);
            tv_comment = (TextView) itemView.findViewById(R.id.tv_comment);
        }
    }
}