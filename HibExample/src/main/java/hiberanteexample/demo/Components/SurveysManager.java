package hiberanteexample.demo.Components;

import hiberanteexample.demo.Models.*;
import hiberanteexample.demo.Repositories.AnswerRepository;
import hiberanteexample.demo.Repositories.SurveyRepository;
import hiberanteexample.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveysManager implements ISurveysManager {
    private AnswerRepository answerRepository;
    private SurveyRepository surveyRepository;
    private UserRepository userRepository;


    public SurveysManager(AnswerRepository answerRepository, SurveyRepository surveyRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
    }

    public User AddUser(String userName){
        User user = new User();
        user.name = userName;
        return userRepository.save( user );
    }

    public Survey AddSurvey(String title, String question, Long userId) {
        User user = userRepository.findById( userId ).orElse( null );
        Survey survey = new Survey();
        survey.question = question;
        survey.title = title;
        survey.user = user;
        return surveyRepository.save( survey );
    }

    @Override
    public List<Survey> GetSurveysFromUser(Long userId) {
        User user = userRepository.findById( userId ).orElse( null );
        if(user == null)
            throw new EntityExistsException( "There is no that user" );
        return user.surveys;
    }

    @Override
    public List<Survey> GetAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public void DeleteSurvey(Long id) {
        Survey survey = surveyRepository.findById( id ).orElse( null );
        if(survey == null)
            throw new EntityExistsException( "There is no that survey" );
        surveyRepository.delete( survey );
    }

    @Override
    public Answer AddAnswer(Integer rating, Long id, Long userId) {
        Survey survey = surveyRepository.findById( id ).orElse( null );
        if(survey == null)
            throw new EntityExistsException( "There is no that survey" );
        User user = userRepository.findById( userId ).orElse( null );
        if(user == null)
            throw new EntityExistsException( "There is no that user" );
        Answer answer = new Answer();
        answer.rating = rating;
        answer.user = user;
        answer.survey = survey;
        return answerRepository.save( answer );
    }

    @Override
    public Survey GetSurvey(Long id) {
        Survey survey = surveyRepository.findById( id ).orElse( null );
        if(survey == null)
            throw new EntityExistsException( "There is no that survey" );
        return survey;
    }

    @Override
    public Answer EditAnswer(Long id, Integer rating) {
        Answer answer = answerRepository.findById( id ).orElse( null );
        answer.rating = rating;
        return answerRepository.save( answer );
    }

    @Override
    public UserStats GetUserStats(Long id) {
        User user = userRepository.findById( id ).orElse( null );
        if(user == null)
            throw new EntityExistsException( "There is no that user" );
        UserStats userStats = new UserStats();
        userStats.Surveys = user.surveys.size();
        List<SurveyStats> surveysStats = new ArrayList<>(  );
        for ( Survey survey:user.surveys ) {
            Integer numberOfAnswers = survey.answers.size();
            Double sum = 0.0;
            for ( int i = 0; i < survey.answers.size(); i++ ){
                sum = sum + survey.answers.get( i ).rating;
            }
            Double mean = sum/survey.answers.size();
            SurveyStats surveyStats = new SurveyStats();
            surveyStats.Answers = numberOfAnswers;
            surveyStats.MeanOfRating = mean;
            surveyStats.Title = survey.title;
            surveysStats.add( surveyStats );
        }
        userStats.setAllStatsSurvey( surveysStats );
        return userStats;
    }

    @Override
    public GlobalStats GetGlobalStats() {
        GlobalStats globalStats = new GlobalStats();
        globalStats.MeanOfAnswersPerSurvey = CalculateMeanOfAnswersPerSurvey();
        globalStats.MeanOfSurveysPerUser = CalculateMeanOfSurveysPerUser();
        globalStats.NumberOfSurveys = CalculateNumberOfAllSurveys();
        globalStats.SurveysPerUser = CalculateSurveysPerUser();
        globalStats.AnswersPerSurvey = CalculateAnswersPerSurvey();
        return globalStats;
    }

    private Double CalculateMeanOfAnswersPerSurvey(){
        List<Survey> surveys = surveyRepository.findAll();
        Double sum = 0.0;
        for ( int i = 0; i < surveys.size(); i++ ){
            Integer answers = surveys.get( i ).answers.size();
            sum = sum + answers;
        }
        return sum/surveys.size();
    }

    private Double CalculateMeanOfSurveysPerUser(){
        List<User> users = userRepository.findAll();
        Double sum = 0.0;
        for ( int i = 0; i < users.size(); i++ ){
            Integer surveys = users.get( i ).surveys.size();
            sum = sum + surveys;
        }
        return sum/users.size();
    }

    private Integer CalculateNumberOfAllSurveys(){
        List<User> users = userRepository.findAll();
        Integer sum = 0;
        for ( int i = 0; i < users.size(); i++ ){
            sum = sum + users.get( i ).surveys.size();
        }
        return sum;
    }

    private List<SurveyPerUser> CalculateSurveysPerUser(){
        List<SurveyPerUser> surveyPerUsersRanking = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for ( int i = 0; i < users.size(); i++ ){
            SurveyPerUser surveyPerUser = new SurveyPerUser();
            surveyPerUser.Name = users.get( i ).name;
            surveyPerUser.NumberOfSurveys = users.get( i ).surveys.size();
            surveyPerUsersRanking.add( surveyPerUser );
        }

        return surveyPerUsersRanking.stream().sorted( Comparator.comparing( o -> o.NumberOfSurveys ) ).collect( Collectors.toList());
    }

    private List<AnswerPerSurvey> CalculateAnswersPerSurvey(){
        List<AnswerPerSurvey> answerPerSurveyRanking = new ArrayList<>();
        List<Survey> surveys = surveyRepository.findAll();
        for ( int i = 0; i < surveys.size(); i++ ){
            AnswerPerSurvey answerPerSurvey = new AnswerPerSurvey();
            answerPerSurvey.Title = surveys.get( i ).title;
            answerPerSurvey.NumberOfAnswers = surveys.get( i ).answers.size();
            answerPerSurveyRanking.add( answerPerSurvey );
        }
        return answerPerSurveyRanking.stream().sorted( Comparator.comparing( o -> o.NumberOfAnswers ) ).collect( Collectors.toList());

    }
}
