package hiberanteexample.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SurveyPerUser {
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getNumberOfSurveys() {
        return NumberOfSurveys;
    }

    public void setNumberOfSurveys(Integer numberOfSurveys) {
        NumberOfSurveys = numberOfSurveys;
    }

    @JsonIgnore
    public String Name;
    @JsonIgnore
    public Integer NumberOfSurveys;
}
