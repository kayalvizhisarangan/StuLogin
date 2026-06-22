package com.example.stulogin.controller;

import com.example.stulogin.model.EventModel;
import com.example.stulogin.model.FacultyModel;
import com.example.stulogin.model.LoginModel;
import com.example.stulogin.model.RegisterModel;
import com.example.stulogin.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    /** POST /faculty/register - Register a new faculty member */
    @PostMapping("/register")
    public FacultyModel register(@RequestBody FacultyModel faculty) {
        return facultyService.registerFaculty(faculty);
    }

    /**
     * POST /faculty/login
     * Returns: { faculty: {...}, events: [...], students: [...] }
     * Returns 401 if credentials are wrong.
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginModel login) {
        Map<String, Object> result = facultyService.loginFaculty(login);
        if (result == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.UNAUTHORIZED, "Invalid credentials"
            );
        }
        return result;
    }

    /** GET /faculty/students - All registered students */
    @GetMapping("/students")
    public List<RegisterModel> getAllStudents() {
        return facultyService.getAllStudents();
    }

    /** GET /faculty/events - All events across all students */
    @GetMapping("/events")
    public List<EventModel> getAllEvents() {
        return facultyService.getAllEvents();
    }
}
