package io.swagger.services;

import io.swagger.DAO.UserDAO;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDAO findByToken(String token) {
        //look for token in database and return corresponding user
        return null;
    }



}
