package io.swagger.services;

import io.swagger.DAO.SurveyDAO;
import io.swagger.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository repository;

    public SurveyDAO findById(Long id) {
        Optional<SurveyDAO> survey = repository.findById(id);

        if(survey.isPresent()) {
            return survey.get();
        } else {
            return null;
        }

    }

    public SurveyDAO findByUserEmail(String userEmail) {
        SurveyDAO survey = repository.findByEmail(userEmail);
        return survey;
    }

}
