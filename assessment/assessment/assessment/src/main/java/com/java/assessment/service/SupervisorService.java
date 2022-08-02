package com.java.assessment.service;

import com.java.assessment.model.Supervisor;
import com.java.assessment.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorService {

    private static final String URL = "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers";

    @Autowired
    private SupervisorRepository supervisorRepository;

    public List<String> supervisors() throws URISyntaxException {
        URI uri = new URI(URL);
        RestTemplate restTemplate = new RestTemplate();
        Supervisor[] sup = restTemplate.getForObject(uri, Supervisor[].class);
        List<Supervisor> al = List.of(sup);

        //first name comparator
        Comparator<Supervisor> compareByFirstName = Comparator.comparing(Supervisor::getFirstName);

        //last name comparator
        Comparator<Supervisor> compareByLastName = Comparator.comparing(Supervisor::getLastName);

        Comparator<Supervisor> compareByJurisdiction = Comparator.comparing(Supervisor::getJurisdiction);

        //Compare by first name and then last name (multiple fields)
        Comparator<Supervisor> compareByFullNameAndJurisdiction = compareByJurisdiction.thenComparing(compareByLastName).thenComparing(compareByFirstName);
        //List<Supervisor> als= al.stream().sorted(compareByFullNameAndJurisdiction).collect(Collectors.toList());
        List<String> als=  al.stream().sorted(compareByFullNameAndJurisdiction)
                .filter(e->!(isNumeric(e.getJurisdiction())))
                .map(e-> {
                    return e.getJurisdiction()+"-"+e.getLastName()+","+e.getFirstName();
                }).collect(Collectors.toList());

        return als;

    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public void save(Supervisor supervisor) {
        supervisorRepository.save(supervisor);
    }
}
