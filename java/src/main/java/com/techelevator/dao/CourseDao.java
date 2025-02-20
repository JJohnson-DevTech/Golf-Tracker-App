package com.techelevator.dao;


import com.techelevator.model.Courses;

import java.util.List;

public interface CourseDao {

    Courses getCourseById(int courseId);

    List<Courses> getCourses();

    List<Courses> getCoursesByState(String state);

    List<Courses> getCoursesByCity(String city);

    List<Courses> getCoursesByName(String courseName);

    Courses getCourseYardage(int courseId);

    Courses getCoursePar(int courseId);

    Courses getCourseHoles(int courseId);

    Courses getClubNameByCourse(int courseId);

    Courses getCourseByAddress(String address);

    Courses createCourse(Courses course);
}
