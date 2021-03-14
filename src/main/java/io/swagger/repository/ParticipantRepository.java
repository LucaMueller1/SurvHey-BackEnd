package io.swagger.repository;

import io.swagger.model.AuthKey;
import io.swagger.model.Participant;
import io.swagger.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Participant findById(long id);

    Participant findByCookieID(String cookieID);

    List<Participant> findAllByIpAddress(String IP);
}
