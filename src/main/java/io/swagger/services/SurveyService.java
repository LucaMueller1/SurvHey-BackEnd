package io.swagger.services;

import io.swagger.model.AnswerOption;
import io.swagger.model.Survey;
import io.swagger.model.SurveyJSON;
import io.swagger.repository.AnswerOptionRepository;
import io.swagger.repository.SurveyRepository;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    public Survey findById(Long id) {
        Optional<Survey> survey = surveyRepository.findById(id);

        if(survey.isPresent()) {
            return survey.get();
        } else {
            return null;
        }

    }

    public Survey findByUserEmail(String userEmail) {
        //Survey survey = repository.findByEmail(userEmail);
        return null;
    }

    public Survey addSurvey(Survey survey) {
        Survey createdSurvey = surveyRepository.save(survey);
        surveyRepository.flush();
        return createdSurvey;
    }

    public SurveyJSON getSurveyJSONById(Long id) {
        Optional<Survey> survey = surveyRepository.findById(id);
        if(!survey.isPresent()) {
            return null;
        }

        List<AnswerOption> options = answerOptionRepository.findAllBySurvey_Id(survey.get().getId());
        return new SurveyJSON(survey.get().getId(), survey.get().getName(), survey.get().getQuestionText(), survey.get().getMode(), survey.get().getUser(), options);

    }



}
