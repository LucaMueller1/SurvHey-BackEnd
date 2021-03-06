package io.swagger.repository;

import io.swagger.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

        Submission findById(long id);
        List<Submission> findBysurveyId(long ID);


}
