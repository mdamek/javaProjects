package hiberanteexample.demo.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    public Long id;
    public String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonManagedReference
    public List<Survey> surveys = new ArrayList<>(  );

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonManagedReference
    public List<Answer> answers = new ArrayList<>(  );
    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
