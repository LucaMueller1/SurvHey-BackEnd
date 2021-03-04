package io.swagger.repository;

import io.swagger.DAO.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDAO, String> {



}
