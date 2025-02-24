package com.techelevator.controller;

import com.techelevator.dao.JdbcCourseDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Courses;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/courses")
public class CourseController {

    private final JdbcCourseDao jdbcCourseDao;

    public CourseController(JdbcCourseDao jdbcCourseDao) {
        this.jdbcCourseDao = jdbcCourseDao;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Courses> getAllCourses(){
        return jdbcCourseDao.getCourses();
    }

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<Courses> getCourseById(@PathVariable int courseId){
        if(courseId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Courses course = jdbcCourseDao.getCourseById(courseId);
        if(course == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //just learned this, in order to keep the order of returning status codes you can
        //return the response entity along with the status and the body of whatever
        //object/data you're needing
        return ResponseEntity.ok(course);
    }

    @PostMapping()
    public ResponseEntity<Courses> addNewCourse(@RequestBody Courses course) {
        if (course == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            jdbcCourseDao.createCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(course);
        } catch (Exception e) {
            throw new DaoException("Issue with controller method 'addNewCourse'");
        }
    }

    @GetMapping("/courses/{id}/par")
    public ResponseEntity<Courses> getCoursePar(int courseId){
        if(courseId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Courses course = jdbcCourseDao.getCoursePar(courseId);
        if(course == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(course);
    }




}
