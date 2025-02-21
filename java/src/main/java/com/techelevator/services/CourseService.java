package com.techelevator.services;

import com.techelevator.dao.JdbcCourseDao;
import com.techelevator.model.Courses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    private static final String API_BASE_URL = "https://api.golfcourseapi.com/v1/";

    private final RestTemplate restTemplate = new RestTemplate();

    private final JdbcCourseDao jdbcCourseDao;

    @Value( "${golfcourseapi.key}")
    private String apiKey;

    public CourseService(JdbcCourseDao jdbcCourseDao) {
        this.jdbcCourseDao = jdbcCourseDao;
    }

    public void fetchAndStoreCourses() {
        String url = API_BASE_URL + "courses";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        HttpEntity<?> entity = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            List<Map<String, Object>> courses = (List<Map<String, Object>>) response.getBody().get("courses");
            saveCoursesToDatabase(courses);
        }
    }

    private void saveCoursesToDatabase(List<Map<String, Object>> courses) {
        for (Map<String, Object> course : courses) {
            int courseId = (int) course.get("id");
            String clubName = (String) course.get("club_name");
            String courseName = (String) course.get("course_name");

            Map<String, Object> location = (Map<String, Object>) course.get("location");
            String address = (String) location.get("address");
            String city = (String) location.get("city");
            String state = (String) location.get("state");
            String country = (String) location.get("country");

            // Insert into PostgreSQL
            jdbcCourseDao.createCourse(new Courses(courseId, clubName, courseName, address, city, state, 0, country, 0, 0));
        }
    }
}