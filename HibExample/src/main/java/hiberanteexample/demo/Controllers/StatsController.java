package hiberanteexample.demo.Controllers;

import hiberanteexample.demo.Components.ISurveysManager;
import hiberanteexample.demo.Models.GlobalStats;
import hiberanteexample.demo.Models.UserStats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private ISurveysManager surveysManager;

    public StatsController(ISurveysManager surveysManager) {
        this.surveysManager = surveysManager;
    }

    @GetMapping(path = "/{id}")
    public UserStats getUserStats(@PathVariable long id){
        return surveysManager.GetUserStats( id );
    }

    @GetMapping
    public GlobalStats getGlobalStats(){
        return surveysManager.GetGlobalStats();
    }
}
