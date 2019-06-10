package hiberanteexample.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class GlobalStats {
    public Integer getNumberOfSurveys() {
        return NumberOfSurveys;
    }

    public void setNumberOfSurveys(Integer numberOfSurveys) {
        NumberOfSurveys = numberOfSurveys;
    }

    public Double getMeanOfSurveysPerUser() {
        return MeanOfSurveysPerUser;
    }

    public void setMeanOfSurveysPerUser(Double meanOfSurveysPerUser) {
        MeanOfSurveysPerUser = meanOfSurveysPerUser;
    }

    public Double getMeanOfAnswersPerSurvey() {
        return MeanOfAnswersPerSurvey;
    }

    public void setMeanOfAnswersPerSurvey(Double meanOfAnswersPerSurvey) {
        MeanOfAnswersPerSurvey = meanOfAnswersPerSurvey;
    }

    public List<SurveyPerUser> getSurveysPerUser() {
        return SurveysPerUser;
    }

    public void setSurveysPerUser(List<SurveyPerUser> surveysPerUser) {
        SurveysPerUser = surveysPerUser;
    }

    public List<AnswerPerSurvey> getAnswersPerSurvey() {
        return AnswersPerSurvey;
    }

    public void setAnswersPerSurvey(List<AnswerPerSurvey> answersPerSurvey) {
        AnswersPerSurvey = answersPerSurvey;
    }

    @JsonIgnore
    public Integer NumberOfSurveys;
    @JsonIgnore
    public Double MeanOfSurveysPerUser;
    @JsonIgnore
    public Double MeanOfAnswersPerSurvey;
    @JsonIgnore
    public List<SurveyPerUser> SurveysPerUser;
    @JsonIgnore
    public List<AnswerPerSurvey> AnswersPerSurvey;
}
