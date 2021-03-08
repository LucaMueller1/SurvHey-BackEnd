package io.swagger.services;

import io.swagger.model.Submission;
import io.swagger.model.Survey;
import io.swagger.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public Submission findByID(long ID){
        return submissionRepository.findById(ID);
    }

    public Submission addOrUpdateSubmission(Submission submission){
        return submissionRepository.save(submission);
    }

    public List<Submission> findAllbySurveyID(long ID){
        return submissionRepository.findBysurveyId(ID);
    }

    public boolean didAlreadyParticipate(Survey survey, String ipAddress){
        List<Submission> list = submissionRepository.findAllBySurveyIdAndIpAddress(survey.getId(), ipAddress);

        return list.size() != 0;
    }

}
