package com.example.stulogin.controller;

import com.example.stulogin.model.EventModel;
import com.example.stulogin.model.LoginModel;
import com.example.stulogin.service.LoginService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/StuLogin")
public class LoginController {
    public final LoginService Logservice;

    public LoginController(LoginService service) {
        this.Logservice = service;
    }

    @PostMapping("/")
    public List<EventModel> disEvent(@RequestBody LoginModel loginModel) {
        return Logservice.findStu(loginModel);
    }

    @PostMapping("/post1")
    public List<EventModel> disEvent1(@RequestBody LoginModel loginModel) {
        return Logservice.findStudent(loginModel);
    }
}