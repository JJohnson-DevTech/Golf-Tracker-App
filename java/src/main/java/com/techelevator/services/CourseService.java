package com.techelevator.services;

import com.techelevator.dao.JdbcCourseDao;
import com.techelevator.model.Courses;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class CourseService {

    private static final String API_BASE_URL = "https://api.golfcourseapi.com/v1/";

    private final RestTemplate restTemplate = new RestTemplate();

    private final JdbcCourseDao jdbcCourseDao;

    @Value( "${golfcourseapi.key}")
    private String apiKey;

    private int lastFetchedPage;

    @Autowired
    public CourseService(JdbcCourseDao jdbcCourseDao) {
        this.jdbcCourseDao = jdbcCourseDao;
    }

    @PostConstruct
    public void initialize() {
        loadLastFetchedPage();
        fetchAndStoreCourses();
    }

    public void fetchAndStoreCourses() {
        int pagesPerDay = 15;
        while(lastFetchedPage <= pagesPerDay) {
            String url = API_BASE_URL + "courses?page=" + lastFetchedPage;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", apiKey);
            HttpEntity<?> entity = new HttpEntity<String>(headers);

            try {
                ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    List<Map<String, Object>> courses = (List<Map<String, Object>>) response.getBody().get("courses");
                    for (Map<String, Object> course : courses) {
                        int courseId = (int) course.get("id");
                        String clubName = (String) course.get("club_name");
                        String courseName = (String) course.get("course_name");

                        Map<String, Object> location = (Map<String, Object>) course.get("location");
                        String address = (String) location.get("address");
                        String city = (String) location.get("city");
                        String state = (String) location.get("state");
                        String country = (String) location.get("country");

                        // Set defaults if yards/par/holes are not available in api\
                        int totalYards = course.containsKey("total_yards") ? (int) course.get("total_yards") : 0;
                        int par = course.containsKey("par") ? (int) course.get("par") : 72;
                        int holes = course.containsKey("holes") ? (int) course.get("holes") : 18;

                        // Skip course if it exists otherwise insert into PostgreSQL Database
                        if (!jdbcCourseDao.courseExists(courseId)) {
                            jdbcCourseDao.createCourse(new Courses(courseId, clubName, courseName, address, city, state, country, totalYards, par, holes));
                        }
                    }
                    lastFetchedPage++;
                    updateLastFetchedPage(lastFetchedPage);
                }
            } catch (HttpClientErrorException.TooManyRequests e) {
                System.out.println("Daily Rate limit reached for page " + lastFetchedPage + ". Add more courses to database tomorrow.");
                lastFetchedPage++;
                updateLastFetchedPage(lastFetchedPage);
                System.out.println("Rate limit error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error fetching courses: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void loadLastFetchedPage() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/runtime.properties")){
            properties.load(input);
            lastFetchedPage = Integer.parseInt(properties.getProperty("fetch.courses.lastPage"));
        } catch (IOException e) {
            lastFetchedPage = 1;
        }
    }

    private void updateLastFetchedPage(int page) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/runtime.properties")){
            properties.load(input);
            properties.setProperty("fetch.courses.lastPage", String.valueOf(page));
            try (FileOutputStream output = new FileOutputStream("src/main/resources/runtime.properties")){
                properties.store(output,null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}