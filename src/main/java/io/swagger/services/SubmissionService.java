package io.swagger.services;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import io.swagger.model.*;
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

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private GeoLocationService geoLocationService;

    public Submission findByID(long ID){
        return submissionRepository.findById(ID);
    }

    public Submission addOrUpdateSubmission(Submission submission){
        return submissionRepository.save(submission);
    }

    public List<Submission> findAllBySurveyID(Long id){
        return submissionRepository.findAllBySurveyId(id);
    }

    public boolean didAlreadyParticipate(Survey survey, String ipAddress){
        List<Submission> list = submissionRepository.findAllBySurveyIdAndIpAddress(survey.getId(), ipAddress);

        return list.size() != 0;
    }

    public Analysis getAnalysis(Survey survey) {
        List<Submission> submissions = submissionRepository.findAllBySurveyId(survey.getId());
        List<String> ipList = submissions.stream().map(Submission::getIpAddress).collect(Collectors.toList());

        List<GeoIP> locations = null;
        try {
            locations = geoLocationService.getLocations(ipList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }

        List<String> countries = locations.stream().map(GeoIP::getCountry).collect(Collectors.toList());
        Map<String, Long> countedCountries = countries.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for(String item : countedCountries.keySet()) {
            System.out.println(item + " count: " + countedCountries.get(item));
        }

        Long sum = countedCountries.values().stream().reduce(0L, Long::sum);

        Analysis analysis = new Analysis(0L, survey.getId(), sum, countedCountries);

        return analysis;
    }

    public SurveyResult getResults(Survey survey) {
        return null;
    }

}
