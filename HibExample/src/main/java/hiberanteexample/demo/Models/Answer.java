package hiberanteexample.demo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    public Long id;
    public Integer rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    public Survey survey;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    public User user;
    public Answer(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }





}
