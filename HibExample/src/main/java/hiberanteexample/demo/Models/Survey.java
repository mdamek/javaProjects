package hiberanteexample.demo.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    public Long id;
    public String title;
    public String question;
    public Survey(){}
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    public User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="survey")
    @JsonManagedReference
    public List<Answer> answers = new ArrayList<>(  );

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
