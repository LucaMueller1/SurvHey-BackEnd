package io.swagger.services;

import io.swagger.model.Participant;
import io.swagger.repository.ParticipantRepository;
import io.swagger.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class ParticipantService {


    @Autowired
    private ParticipantRepository participantRepository;

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public Participant getParticipantByID(long id){
       return participantRepository.findById(id);
    }

    public List<Participant> getByCookieID(String cookie){
        return participantRepository.findAllByCookieID(cookie);
    }

    public Participant getByCookieIDAndByIP(String cookie, String IP){
        return participantRepository.findParticipantByIpAddressAndCookieID(IP,cookie);
    }

    public List<Participant> getByIP(String IP){
        return participantRepository.findAllByIpAddress(IP);
    }

    public Participant createOrUpdateParticipant(Participant participant){
        return participantRepository.save(participant);
    }

    public static String generateNewCookie() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
