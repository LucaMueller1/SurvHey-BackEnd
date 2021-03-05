package io.swagger.repository;

import io.swagger.model.AnswerOption;
import io.swagger.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {

    List<AnswerOption> findAllBySurvey_Id(Long surveyId);

}
