package com.example.stulogin.service;

import com.example.stulogin.model.RegisterModel;
import com.example.stulogin.repository.RegisterRepo;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    public final RegisterRepo registerRepo;
    public RegisterService(RegisterRepo registerRepo) {
        this.registerRepo = registerRepo;
    }

    public RegisterModel SaveStu(RegisterModel registerModel)
    {
        return registerRepo.save(registerModel);
    }
}
