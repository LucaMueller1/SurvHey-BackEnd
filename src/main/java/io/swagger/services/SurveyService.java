package io.swagger.services;

import io.swagger.DAO.Answer_OptionDAO;
import io.swagger.DAO.SurveyDAO;
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

    @Autowired
    private ModelMapper modelMapper;

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

    public Survey addSurvey(SurveyDAO survey) {
        SurveyDAO createdSurvey = repository.save(survey);
        repository.flush();

        return convertToEntity(createdSurvey);
    }

    public List<Answer_OptionDAO> toAnswerOptionDAO(List<AnswerOption> list) {
        List<Answer_OptionDAO> rList = new ArrayList<>();

        for(AnswerOption option: list) {
            rList.add(new Answer_OptionDAO(option.getId(), null, option.getContent().toString()));
        }
        return rList;
    }

    private Survey convertToEntity(SurveyDAO surveyDTO) {
        Survey survey = modelMapper.map(surveyDTO, Survey.class);
        return survey;
    }

    private SurveyDAO convertToDTO(Survey survey) {
        SurveyDAO surveyDTO = modelMapper.map(survey, SurveyDAO.class);
        return surveyDTO;
    }

}
