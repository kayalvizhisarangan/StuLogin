
package com.example.stulogin.model;

import lombok.Data;

@Data
public class EventModel {
    private String id;
    private String name;
    private Integer rNo;
    private String eventName;
    private String eventLocation;
    private String eventDate;
    private String facultyId;
    private String eventDescription;
}