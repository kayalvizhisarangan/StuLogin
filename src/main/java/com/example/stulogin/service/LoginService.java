package com.example.stulogin.service;

import com.example.stulogin.model.EventModel;
import com.example.stulogin.model.LoginModel;
import com.example.stulogin.model.RegisterModel;
import com.example.stulogin.repository.RegisterRepo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class LoginService {
    public final RegisterRepo registerRepo;
    private final RestTemplate restTemplate;

    public LoginService(RegisterRepo repo, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.registerRepo = repo;
    }

    public List<EventModel> findStu(LoginModel login) {
        RegisterModel stuDetails = registerRepo.findByEmail(login.getEmail());
        if (stuDetails == null) {
            return List.of();
        }
        if (stuDetails.getPassword().equals(login.getPassword())) {
            String StuEventUrl = "http://localhost:8081/events/number/" + stuDetails.getRNo();
            /*ResponseEntity EventModel[] response =
                    restTemplate.getForEntity(StuEventUrl,EventModel[].class);*/
            ResponseEntity<List<EventModel>> response =
                    restTemplate.exchange(
                            StuEventUrl,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<EventModel>>() {
                            }
                    );
            if (response.getStatusCode().is2xxSuccessful()) {
                //EventModel eventModel[]= response.getBody();
                return response.getBody();
            }
        }
        return List.of();
    }

    public List<EventModel> findStudent(LoginModel login) {
        RegisterModel stuDetails = registerRepo.findByEmail(login.getEmail());
        if (stuDetails == null) {
            return List.of();
        }
        ResponseEntity<List<EventModel>> response =
                restTemplate.exchange(
                        "http://localhost:8081/events/number/" + stuDetails.getRNo(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<EventModel>>() {}
                );
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return List.of();
    }
}
