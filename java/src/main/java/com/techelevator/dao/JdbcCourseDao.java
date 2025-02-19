package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Courses;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCourseDao implements CourseDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Courses> getCourses() {
        List<Courses> courses = new ArrayList<>();
        String sql = "SELECT club_name, course_name, address, yardage, par, holes FROM golf_courses;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Courses course = mapRowToCourse(results);
                courses.add(course);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCourses");
        }

        return courses;
    }

    @Override
    public List<Courses> getCoursesByState(String state) {
        if(state == null) throw new IllegalArgumentException("State cannot be null.");
        List<Courses> courses = new ArrayList<>();
        String sql = "SELECT club_name, course_name, address, yardage, par, holes FROM golf_courses " +
                "WHERE state_ab = LOWER(TRIM(?))";

        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, state);
            while(rowSet.next()){
                courses.add(mapRowToCourse(rowSet));
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCoursesByState");
        }
        return courses;
    }

    @Override
    public List<Courses> getCoursesByCity(String city) {
        if(city == null) throw new IllegalArgumentException("City cannot be null.");
        List<Courses> courses = new ArrayList<>();
        String sql = "SELECT club_name, course_name, address, yardage, par, holes FROM golf_courses " +
                "WHERE city = LOWER(TRIM(?))";

        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, city);
            while(rowSet.next()){
                courses.add(mapRowToCourse(rowSet));
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCoursesByCity");
        }

        return courses;
    }

    @Override
    public List<Courses> getCoursesByName(String courseName) {
        if(courseName == null) throw new IllegalArgumentException("Course Name cannot be null.");
        List<Courses> courses = new ArrayList<>();
        String sql = "SELECT club_name, course_name, address, yardage, par, holes FROM golf_courses " +
                "WHERE course_name ILIKE ?";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, courseName);
            while(rowSet.next()){
                courses.add(mapRowToCourse(rowSet));
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCoursesByName");
        }
        return courses;
    }

    @Override
    public Courses getCourseYardage(int courseId) {
        if(courseId == 0) throw new IllegalArgumentException("Course id Cannot be 0");
        Courses course = new Courses();
        String sql = "SELECT yardage FROM golf_courses WHERE id = ?";

        try{
            int yardage = jdbcTemplate.queryForObject(sql, int.class, courseId);
            course.setYardage(yardage);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCourseYardage");
        }
        return course;
    }

    @Override
    public Courses getCoursePar(int courseId) {
        if(courseId == 0) throw new IllegalArgumentException("Course Id cannot be 0.");

        Courses course = new Courses();
        String sql = "SELECT par FROM golf_courses WHERE id = ?";

        try{
            int par = jdbcTemplate.queryForObject(sql, int.class, courseId);
            course.setPar(par);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCoursePar");
        }
        //Returning course here will return the object as a whole if we want just the yardage/par then we should
        // just return the int value of par or yardage
        return course;
    }

    @Override
    public Courses getCourseHoles(int courseId) {
        if(courseId == 0) throw new IllegalArgumentException("Course Id cannot be 0.");

        Courses course = new Courses();
        String sql = "SELECT holes FROM golf_courses WHERE id = ?";

        try{
            int holes = jdbcTemplate.queryForObject(sql, int.class, courseId);
            course.setHoles(holes);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCourseHoles");
        }
        return course;
    }

    @Override
    public Courses getClubNameByCourse(int courseId) {
        if(courseId == 0) throw new IllegalArgumentException("Course Id cannot be 0");
        Courses course = new Courses();
        String sql = "SELECT club_name FROM golf_courses WHERE id = ?";

        try{
            String clubName = jdbcTemplate.queryForObject(sql, String.class, courseId);
            course.setClubName(clubName);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getClubNameByCourse");
        }
        return course;
    }

    @Override
    public Courses getCourseByAddress(String address) {
        if(address == null) throw new IllegalArgumentException("Address cannot be null.");
        Courses course = new Courses();
        String sql = "SELECT club_name, course_name, address, yardage, par, holes FROM golf_courses WHERE address ILIKE ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, address);
            while(results.next()){
                course = mapRowToCourse(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCourseByAddress");
        }
        return course;
    }


    private Courses mapRowToCourse(SqlRowSet rs) {
        Courses courses = new Courses();
        courses.setCourseId(rs.getInt("id"));
        courses.setClubName(rs.getString("club_name"));
        courses.setCourseName(rs.getString("course_name"));
        courses.setAddress(rs.getString("address"));
        courses.setCity(rs.getString("city"));
        courses.setState(rs.getString("state_ab"));
        courses.setYardage(rs.getInt("yardage"));
        courses.setCountry(rs.getString("country"));
        courses.setPar(rs.getInt("par"));
        courses.setHoles(rs.getInt("holes"));
        return courses;
    }
}
