package com.example.stulogin.service;

import com.example.stulogin.model.EventModel;
import com.example.stulogin.model.FacultyModel;
import com.example.stulogin.model.LoginModel;
import com.example.stulogin.repository.FacultyRepo;
import com.example.stulogin.repository.RegisterRepo;
import com.example.stulogin.model.RegisterModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
public class FacultyService {

    private final FacultyRepo facultyRepo;
    private final RegisterRepo registerRepo;
    private final RestTemplate restTemplate;

    public FacultyService(FacultyRepo facultyRepo, RegisterRepo registerRepo, RestTemplate restTemplate) {
        this.facultyRepo = facultyRepo;
        this.registerRepo = registerRepo;
        this.restTemplate = restTemplate;
    }

    /** Register a new faculty member */
    public FacultyModel registerFaculty(FacultyModel faculty) {
        return facultyRepo.save(faculty);
    }

    /**
     * Faculty login: validate credentials, then return a map containing
     * faculty info + all events (across all students) so the dashboard
     * can display everything.
     */
    public Map<String, Object> loginFaculty(LoginModel login) {
        FacultyModel faculty = facultyRepo.findByEmail(login.getEmail());
        if (faculty == null || !faculty.getPassword().equals(login.getPassword())) {
            return null;
        }

        // Fetch all events from StuEvent microservice
        ResponseEntity<List<EventModel>> response = restTemplate.exchange(
                "http://localhost:8081/events/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EventModel>>() {}
        );

        List<EventModel> allEvents = response.getStatusCode().is2xxSuccessful()
                ? response.getBody()
                : List.of();

        // Fetch all registered students
        List<RegisterModel> allStudents = registerRepo.findAll();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("faculty", faculty);
        result.put("events", allEvents);
        result.put("students", allStudents);
        return result;
    }

    /** Get all students (faculty view) */
    public List<RegisterModel> getAllStudents() {
        return registerRepo.findAll();
    }

    /** Get all events (faculty view) */
    public List<EventModel> getAllEvents() {
        ResponseEntity<List<EventModel>> response = restTemplate.exchange(
                "http://localhost:8081/events/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EventModel>>() {}
        );
        return response.getStatusCode().is2xxSuccessful() ? response.getBody() : List.of();
    }
}
