package com.techelevator.dao;


import com.techelevator.model.CoursesDb;

import java.util.List;

public interface CourseDao {

    List<CoursesDb> getCourses();

    List<CoursesDb> getCoursesByState(String stateAb);

    List<CoursesDb> getCoursesByCity(String city);

    List<CoursesDb> getCoursesByName(String courseName);

    CoursesDb getCourseYardage(int courseId);

    CoursesDb getCoursePar(int courseId);

    CoursesDb getCourseHoles(int courseId);

}
