package io.swagger.services;

import io.swagger.model.AnswerOption;
import io.swagger.model.Survey;
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
    private SurveyRepository repository;

    public Survey findById(Long id) {
        Optional<Survey> survey = repository.findById(id);

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
        Survey createdSurvey = repository.save(survey);
        repository.flush();

        return createdSurvey;
    }


}
