package io.swagger.repository;

import io.swagger.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SurveyRepository extends JpaRepository<Survey, Long> {



}
