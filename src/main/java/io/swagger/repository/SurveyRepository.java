package io.swagger.repository;

import io.swagger.model.Survey;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SurveyRepository extends JpaRepository<Survey, Long> {

    List<Survey> findAllByUser(User user);

}
