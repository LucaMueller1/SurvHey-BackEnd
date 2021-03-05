package io.swagger.services;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void createUser(User user) {
        repository.save(user);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }

}
