package hiberanteexample.demo.Components;

import hiberanteexample.demo.Models.*;

import java.util.List;

public interface ISurveysManager {
    User AddUser(String userName);
    Survey AddSurvey(String title, String question, Long userId);
    List<Survey> GetSurveysFromUser(Long userId);
    List<Survey> GetAllSurveys();
    void DeleteSurvey(Long id);
    Answer AddAnswer(Integer rating, Long id, Long userId);
    Survey GetSurvey(Long id);
    Answer EditAnswer(Long id, Integer rating);
    UserStats GetUserStats(Long id);
    GlobalStats GetGlobalStats();
}
