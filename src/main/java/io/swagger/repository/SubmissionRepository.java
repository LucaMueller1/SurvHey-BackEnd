package io.swagger.repository;

import io.swagger.model.Participant;
import io.swagger.model.Submission;
import io.swagger.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

        Submission findById(long id);

        List<Submission> findAllBySurveyId(Long Id);

        List<Submission> findAllBySurveyIdAndParticipant(Long surveyID, Participant participant);


        List<Submission> findAllByParticipant(Participant participant);


}
