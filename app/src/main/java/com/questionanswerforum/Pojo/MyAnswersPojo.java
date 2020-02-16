package com.questionanswerforum.Pojo;


import com.google.gson.annotations.SerializedName;

public class MyAnswersPojo {

    @SerializedName("id")
    private
    String id;

    @SerializedName("post_by")
    private
    String post_by;

    @SerializedName("question")
    private
    String question;

    @SerializedName("subject")
    private
    String subject;

    @SerializedName("tags")
    private
    String tags;




    public MyAnswersPojo(String id, String post_by, String question, String subject,String tags) {
        this.setId(id);
        this.setPost_by(post_by);
        this.setQuestion(question);
        this.setSubject(subject);
        this.setTags(tags);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_by() {
        return post_by;
    }

    public void setPost_by(String post_by) {
        this.post_by = post_by;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
