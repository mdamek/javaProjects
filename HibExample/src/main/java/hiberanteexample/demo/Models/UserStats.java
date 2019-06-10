package hiberanteexample.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class UserStats {
    @JsonIgnore
    public Integer Surveys;
    @JsonIgnore
    public List<SurveyStats> getAllStatsSurvey() {
        return AllStatsSurvey;
    }

    public void setAllStatsSurvey(List<SurveyStats> allStatsSurvey) {
        AllStatsSurvey = allStatsSurvey;
    }

    public List<SurveyStats> AllStatsSurvey;

    public Integer getSurveys() {
        return Surveys;
    }

    public void setSurveys(Integer surveys) {
        Surveys = surveys;
    }


}
