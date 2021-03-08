package io.swagger.services;

import io.swagger.model.AnswerOption;
import io.swagger.model.Survey;
import io.swagger.model.User;
import io.swagger.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    public Survey findById(Long id) {
        Optional<Survey> survey = surveyRepository.findById(id);

        if(survey.isPresent()) {
            return survey.get();
        } else {
            return null;
        }

    }

    public List<Survey> findByUser(User user) {
        return surveyRepository.findAllByUser(user);
    }

    public Survey addOrUpdateSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public void deleteSurvey(Survey survey) {
        surveyRepository.delete(survey);
    }

    //check if choice array contains only AnswerOptions that are referenced by the given survey
    public boolean validAnswerOptions(Survey survey, List<AnswerOption> choices) {
        List<Long> idListChoices = choices.stream().map(AnswerOption::getId).collect(Collectors.toList());
        List<Long> idListOptions = survey.getAnswerOptions().stream().map(AnswerOption::getId).collect(Collectors.toList());
        if(!(idListChoices.stream().distinct().count() == idListChoices.size())) {  //duplicate entries in choices
            return false;
        }
        return idListOptions.containsAll(idListChoices);
    }

}
