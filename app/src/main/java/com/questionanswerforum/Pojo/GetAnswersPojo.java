package com.questionanswerforum.Pojo;

import com.google.gson.annotations.SerializedName;

public class GetAnswersPojo {
    @SerializedName("id")
    private
    String id;

    @SerializedName("answer")
    private
    String answer;

    @SerializedName("answer_by")
    private
    String answer_by;

    public GetAnswersPojo(String answer, String answer_by,String id) {
        this.setAnswer(answer);
        this.setAnswer_by(answer_by);
        this.setId(id);

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer_by() {
        return answer_by;
    }

    public void setAnswer_by(String answer_by) {
        this.answer_by = answer_by;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
