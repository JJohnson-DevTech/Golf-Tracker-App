package com.techelevator.services;

import com.techelevator.model.Courses;
import org.springframework.web.client.RestTemplate;

public class CourseService {

    private static final String API_BASE_URL = "https://golfcourseapi.com/v1/";

    private final RestTemplate restTemplate = new RestTemplate();

    public Courses[] listCourses() {
        return restTemplate.getForObject(API_BASE_URL + "/courses", Courses[].class);
    }

    public Courses[] getCourseById(int courseId){
        return restTemplate.getForObject(API_BASE_URL +  "/courses/" + courseId, Courses[].class);
    }
}
