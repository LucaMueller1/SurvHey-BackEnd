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

    public Survey addSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

}
