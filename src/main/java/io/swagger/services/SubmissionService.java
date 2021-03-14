package io.swagger.services;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import io.swagger.model.*;
import io.swagger.model.*;
import io.swagger.repository.ParticipantRepository;
import io.swagger.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private GeoLocationService geoLocationService;

    @Autowired
    private ParticipantService participantService ;

    public Submission findByID(long ID){
        return submissionRepository.findById(ID);
    }



    public Submission addOrUpdateSubmission(Submission submission){
        return submissionRepository.save(submission);
    }

    public List<Submission> findAllBySurveyID(Long id){
        return submissionRepository.findAllBySurveyId(id);
    }

    public boolean didAlreadyParticipate(Participant participant, Survey survey){

        List<Submission> list = submissionRepository.findAllBySurveyIdAndParticipant(survey.getId(), participant);
        return list.size() !=0;
    }

    public Analysis getAnalysis(Survey survey) {
        List<Submission> submissions = submissionRepository.findAllBySurveyId(survey.getId());
        Iterator <Submission> subIterator= submissions.iterator();
        List<String> ipList = submissions.stream().map(Submission::getParticipant).map(Participant::getIpAddress).collect(Collectors.toList());


        List<GeoIP> locations = null;
        try {
            locations = geoLocationService.getLocations(ipList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }

        if(locations==null){
            return null;
        }
        List<String> countries = locations.stream().map(GeoIP::getCountry).collect(Collectors.toList());
        Map<String, Long> countedCountries = countries.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Long sum = countedCountries.values().stream().reduce(0L, Long::sum);

        Analysis analysis = new Analysis(0L, survey.getId(), sum, countedCountries);

        return analysis;
    }

    public SurveyResult getResults(Survey survey) {
        SurveyResult result = new SurveyResult();

        result.surveyId(survey.getId()); // Set SurveyResult survey id by given survey

        List<Submission> submissions = findAllBySurveyID(survey.getId()); // Get all submissions
        HashMap<String, Integer> frequency = new HashMap();

        for (Submission submission : submissions) { // for each submission
            List<AnswerOption> choices = submission.getChoices(); // get all submitted choices from the submission
            for (AnswerOption choice : choices) {   // for each submitted choice
                frequency.putIfAbsent(choice.getContent(), 0);  // if answer option is not counted yet, add key to frequency hash map
                frequency.put(choice.getContent(), frequency.get(choice.getContent()) + 1); // update counter for answer option
            }
        }
        result.setChoices(frequency);
        return result;
    }

}
