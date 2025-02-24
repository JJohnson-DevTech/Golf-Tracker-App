package com.techelevator.controller;

import com.techelevator.dao.JdbcCourseDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Courses;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
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
//   TODO check with team to make sure wont have a merge here

//    @PutMapping("/{courseId}")
//    public ResponseEntity<Courses> updateCourse(@PathVariable int courseId, @RequestBody Courses course) {
//        if(courseId <= 0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        if(course == null){
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//        try{
//            jdbcCourseDao.updateCourse(course);
//            return ResponseEntity.status(HttpStatus.OK).build();
//        } catch (Exception e){
//            throw new DaoException("issue with controller method 'updateCourse'");
//        }
//    }


    //TODO again make sure no merges happen

//    @DeleteMapping(path = "/{courseId}")
//    public ResponseEntity<Courses> deleteCourse(@PathVariable int courseId){
//        if(courseId <= 0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        try{
//            jdbcCourseDao.deleteCourse(courseId);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } catch (Exception e){
//            throw new DaoException("Issue with controller method 'deleteCourse'");
//        }
//    }



}
