package hiberanteexample.demo.Controllers;

import com.fasterxml.jackson.core.json.JsonReadContext;
import hiberanteexample.demo.Components.ISurveysManager;
import hiberanteexample.demo.Models.Survey;
import hiberanteexample.demo.Models.User;
import hiberanteexample.demo.Models.UserStats;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Map;

@RestController
@RequestMapping({"/user"})
public class UserController {

    private ISurveysManager surveysManager;

    public UserController(ISurveysManager surveysManager){
        this.surveysManager = surveysManager;
    }

    @PostMapping
    public User addNewUser(@RequestBody String newUser){
        JSONObject user = new JSONObject(newUser);
        return surveysManager.AddUser( user.getString( "username" ) );
    }
}
