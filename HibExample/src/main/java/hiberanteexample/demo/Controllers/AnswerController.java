package hiberanteexample.demo.Controllers;

import hiberanteexample.demo.Components.ISurveysManager;
import hiberanteexample.demo.Models.Answer;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    private ISurveysManager surveysManager;

    public AnswerController(ISurveysManager surveysManager) {
        this.surveysManager = surveysManager;
    }
    @PostMapping
    public Answer AddNewAnswer(@RequestBody String newAnswer, @RequestHeader String userId){
        JSONObject answer = new JSONObject(newAnswer);
        return surveysManager.AddAnswer( answer.getInt( "rating" ), answer.getLong( "id" ), Long.parseLong( userId ) );
    }

    @PutMapping(path = "/{id}")
    public Answer EditAnswer(@RequestBody String newAnswerValue, @PathVariable long id){
        JSONObject answer = new JSONObject(newAnswerValue);
        return surveysManager.EditAnswer(id, answer.getInt( "rating" ));
    }
}
