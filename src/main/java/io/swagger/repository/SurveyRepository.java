package io.swagger.repository;

import io.swagger.DAO.SurveyDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<SurveyDAO, Long> {

    SurveyDAO findByEmail(String userEmail);

}
