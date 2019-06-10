package hiberanteexample.demo.Controllers;

import hiberanteexample.demo.Components.ISurveysManager;
import hiberanteexample.demo.Models.Survey;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {
    private ISurveysManager surveysManager;

    public SurveyController(ISurveysManager surveysManager) {
        this.surveysManager = surveysManager;
    }

    @PostMapping
    public Survey addNewSurvey(@RequestBody String newSurvey, @RequestHeader String userId){
        JSONObject survey = new JSONObject(newSurvey);
        return surveysManager.AddSurvey(survey.getString( "title" ), survey.getString( "question" ), Long.parseLong( userId ));
    }

    @GetMapping(path = "/user/{id}")
    public List<Survey> getSurveysFromUser(@PathVariable long id){
        return surveysManager.GetSurveysFromUser( id );
    }

    @GetMapping
    public List<Survey> getAllSurveys(){
        return surveysManager.GetAllSurveys();
    }

    @DeleteMapping(path = "/{id}")
    public Object deleteSurvey(@PathVariable long id){
        surveysManager.DeleteSurvey( id );
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{id}")
    public Survey getSurvey(@PathVariable long id){
        return surveysManager.GetSurvey(id);
    }
}
