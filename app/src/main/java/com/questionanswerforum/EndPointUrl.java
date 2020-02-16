package com.questionanswerforum;

import com.questionanswerforum.Pojo.CommentsPojo;
import com.questionanswerforum.Pojo.GetAnswersPojo;
import com.questionanswerforum.Pojo.MyAnswersPojo;
import com.questionanswerforum.Pojo.QuestionsPojo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPointUrl {
    @GET("QueAnsForum/registration.php?")
    Call<ResponseData> register(
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("emailid") String emailid,
            @Query("uname") String uname,
            @Query("pwd") String pwd,
            @Query("utype") String utype
    );

    @GET("QueAnsForum/login.php?")
    Call<ResponseData> studentlogin(
            @Query("uname") String uname,
            @Query("pwd") String pwd
    );

    @GET("QueAnsForum/post_comment.php?")
    Call<ResponseData> add_comment(
            @Query("id") String id,
            @Query("comment") String comment
    );

    @GET("/QueAnsForum/post_question.php?")
    Call<ResponseData> postqstn(

            @Query("subject") String subject,
            @Query("question") String question,
            @Query("post_by") String post_by,
            @Query("tags") String tags
    );

    @GET("/QueAnsForum/getAllQuestions.php?")
    Call<List<QuestionsPojo>> getAllQuestions(@Query("uname") String uname);

    @GET("QueAnsForum/post_answer.php")
    Call<ResponseData> post_answer(

            @Query("qid") String qid,
            @Query("answer") String answer,
            @Query("answer_by") String answer_by
    );

    @GET("QueAnsForum/getAnswer.php")
    Call<List<GetAnswersPojo>> getAnswers(@Query("id") String id);

    @GET("/QueAnsForum/getMyQuestions.php?")
    Call<List<MyAnswersPojo>> getMyQuestions1(@Query("uname") String uname);


    @GET("/QueAnsForum/admin_login.php?")
    Call<ResponseData> admin_login(
            @Query("uname") String uname,
            @Query("pwd") String pwd
    );

    @GET("/QueAnsForum/students_login.php?")
    Call<ResponseData> students_login(

            @Query("uname") String uname,
            @Query("pwd") String pwd
    );

    @GET("/QueAnsForum/teachers_login.php?")
    Call<ResponseData> teachers_login(

            @Query("uname") String uname,
            @Query("pwd") String pwd
    );

    @GET("QueAnsForum/get_comment.php")
    Call<List<CommentsPojo>> getComments(@Query("id") String id);

}