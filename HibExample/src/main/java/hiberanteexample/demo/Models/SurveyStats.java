package hiberanteexample.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SurveyStats {
    @JsonIgnore
    public String Title;
    @JsonIgnore
    public Integer Answers;
    @JsonIgnore
    public Double MeanOfRating;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getAnswers() {
        return Answers;
    }

    public void setAnswers(Integer answers) {
        Answers = answers;
    }

    public Double getMeanOfRating() {
        return MeanOfRating;
    }

    public void setMeanOfRating(Double meanOfRating) {
        MeanOfRating = meanOfRating;
    }
}
