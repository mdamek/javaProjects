package hiberanteexample.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AnswerPerSurvey {
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getNumberOfAnswers() {
        return NumberOfAnswers;
    }

    public void setNumberOfAnswers(Integer numberOfAnswers) {
        NumberOfAnswers = numberOfAnswers;
    }

    @JsonIgnore
    public String Title;
    @JsonIgnore
    public Integer NumberOfAnswers;
}
