package io.swagger.repository;

import io.swagger.model.AuthKey;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthKeyRepository extends JpaRepository<AuthKey, String> {


}
