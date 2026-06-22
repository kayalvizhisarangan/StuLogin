package com.example.stulogin.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Faculty")
@Data
public class FacultyModel {
    @Id
    private String id;
    private String name;
    private String facultyId;
    private String email;
    private String password;
    private String department;
}
