package com.techelevator.services;

import com.techelevator.dao.JdbcCourseDao;
import com.techelevator.model.Courses;
import org.springframework.web.client.RestTemplate;


public class CourseService {

    private static final String API_BASE_URL = "https://api.golfcourseapi.com/v1/";

    private final RestTemplate restTemplate = new RestTemplate();

    private final JdbcCourseDao jdbcCourseDao;

    public CourseService(JdbcCourseDao jdbcCourseDao) {
        this.jdbcCourseDao = jdbcCourseDao;
    }

    public Courses[] getCourseById(int courseId){
        return restTemplate.getForObject(API_BASE_URL +  "courses/" + courseId, Courses[].class);
    }

    public Courses[] getCourseByName(String courseName){
        courseName = courseName.replace(" ", "%20");
        return restTemplate.getForObject(API_BASE_URL + "courses?course_name=" + courseName, Courses[].class);
    }

    public Courses[] getCourseByClubName(String clubName){
        clubName = clubName.replace(" ", "%20");
        return restTemplate.getForObject(API_BASE_URL + "courses?club_name=" + clubName, Courses[].class);
    }

    public void insertApiCoursesToDb(){
        Courses[] courses = restTemplate.getForObject(API_BASE_URL + "courses", Courses[].class);
            if(courses != null){
                for(Courses apiCourse: courses){
                    jdbcCourseDao.createCourse(apiCourse);
                }
            }
    }

}
