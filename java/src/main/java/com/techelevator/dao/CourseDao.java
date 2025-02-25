package com.techelevator.dao;


import com.techelevator.model.Courses;
import com.techelevator.model.FavoriteCourse;

import java.util.List;

public interface CourseDao {

    Courses getCourseById(int courseId);

    List<Courses> getCourses();

    List<Courses> getCoursesByState(String state);

    List<Courses> getCoursesByCity(String city);

    List<Courses> getCoursesByName(String courseName);

    Courses getCoursePar(int courseId);

    Courses getClubNameByCourse(int courseId);

    Courses getCourseByAddress(String address);

    Courses createCourse(Courses course);

    boolean courseExists(int courseId);

    FavoriteCourse addNewFavoriteCourse(int userId, int courseId);
}
