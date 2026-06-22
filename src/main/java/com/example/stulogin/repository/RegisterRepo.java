package com.example.stulogin.repository;

import com.example.stulogin.model.RegisterModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepo extends MongoRepository<RegisterModel,String> {
    RegisterModel findByEmail(String email);
}
