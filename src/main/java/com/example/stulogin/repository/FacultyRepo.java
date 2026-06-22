package com.example.stulogin.repository;

import com.example.stulogin.model.FacultyModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepo extends MongoRepository<FacultyModel, String> {
    FacultyModel findByEmail(String email);
    FacultyModel findByFacultyId(String facultyId);
}
