package com.techelevator.controller;

import com.techelevator.dao.JdbcCourseDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Courses;
import org.apache.coyote.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

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
    @GetMapping()
    public List<Courses> getAllCourses(){
        return jdbcCourseDao.getCourses();
    }

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<Courses> getCourseById(@PathVariable int courseId){
        if(courseId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            Courses course = jdbcCourseDao.getCourseById(courseId);
            if(course == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(course);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'getCourseById'");
        }
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
            // Log the actual exception to help debug
            System.err.println("Error occurred in addNewCourse: " + e.getMessage());
            e.printStackTrace();  // This will print the stack trace to help you see where it is failing
            throw new DaoException("Issue with controller method 'addNewCourse': " + e.getMessage(), e);
        }
    }


    @GetMapping("/courses/{id}/par")
    public ResponseEntity<Courses> getCoursePar(int courseId){
        if(courseId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            Courses course = jdbcCourseDao.getCoursePar(courseId);
            if(course == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(course);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'getCoursePar'");
        }
    }




}
