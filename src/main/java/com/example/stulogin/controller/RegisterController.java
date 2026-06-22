package com.example.stulogin.controller;

import com.example.stulogin.model.RegisterModel;
import com.example.stulogin.service.RegisterService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/regStudent")
public class RegisterController {
    private final RegisterService registerService;
    public RegisterController(RegisterService registerService)
    {
        this.registerService = registerService;
    }
    @PostMapping("/")
    public RegisterModel SaveStuDet(@RequestBody RegisterModel registerModel){
        return registerService.SaveStu(registerModel);
    }
}
