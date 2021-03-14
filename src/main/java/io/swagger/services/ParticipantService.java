package io.swagger.services;

import io.swagger.model.Participant;
import io.swagger.repository.ParticipantRepository;
import io.swagger.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {


    @Autowired
    private ParticipantRepository participantRepository;

    public Participant getParticipantByID(long id){
       return participantRepository.findById(id);
    }

    public Participant getByCookieID(String cookie){
        return participantRepository.findByCookieID(cookie);

    }

    public List<Participant> getByIP(String IP){

        return participantRepository.findAllByIpAddress(IP);

    }

    public Participant createOrUpdateParticipant(Participant participant){
        return participantRepository.save(participant);
    }
}
