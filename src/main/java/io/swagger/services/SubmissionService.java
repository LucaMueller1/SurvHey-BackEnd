package io.swagger.services;

import io.swagger.model.*;
import io.swagger.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Submission> findAllBySurveyID(Long id){
        return submissionRepository.findAllBySurveyId(id);
    }

    public boolean didAlreadyParticipate(Survey survey, String ipAddress){
        List<Submission> list = submissionRepository.findAllBySurveyIdAndIpAddress(survey.getId(), ipAddress);

        return list.size() != 0;
    }

    public Analysis getAnalysis(Survey survey) {
        return null;
    }

    public SurveyResult getResults(Survey survey) {
        SurveyResult result = new SurveyResult();

        result.surveyId(survey.getId()); // Set SurveyResult survey id by given survey

        List<Submission> submissions = findAllBySurveyID(survey.getId()); // Get all submissions
        HashMap<String, Integer> frequency = new HashMap();

        for (Submission submission : submissions) { // for each submission
            List<AnswerOption> choices = submission.getChoices(); // get all submitted choices from the submission
            for (AnswerOption choice : choices) {   // for each submitted choice
                frequency.putIfAbsent(choice.getContent(), 0);  // if answer option is not counted yet, add key to frequency hash map
                frequency.put(choice.getContent(), frequency.get(choice.getContent()) + 1); // update counter for answer option
            }
        }
        result.setChoices(frequency);
        return result;
    }

}
