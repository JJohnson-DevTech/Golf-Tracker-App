package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Courses;
import com.techelevator.services.CourseService;
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
    public Courses getCourseById(int courseId) {
        if(courseId == 0) throw new IllegalArgumentException("Course Id cannot be 0");
        Courses course = new Courses();
        String sql = "SELECT club_name, course_name, address, yardage, par, holes FROM golf_courses WHERE course_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseId);
            if(results.next()){
                course = mapRowToCourse(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCourseById", e);
        }
        return course;
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
            throw new DaoException("Issue with getCourses", e);
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
            throw new DaoException("Issue with getCoursesByState", e);
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
            throw new DaoException("Issue with getCoursesByCity", e);
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
            throw new DaoException("Issue with getCoursesByName", e);
        }
        return courses;
    }

    @Override
    public Courses getCourseYardage(int courseId) {
        if(courseId == 0) throw new IllegalArgumentException("Course id Cannot be 0");
        Courses course = new Courses();
        String sql = "SELECT yardage FROM golf_courses WHERE course_id = ?";

        try{
            int yardage = jdbcTemplate.queryForObject(sql, int.class, courseId);
            course.setYardage(yardage);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCourseYardage", e);
        }
        return course;
    }

    @Override
    public Courses getCoursePar(int courseId) {
        if(courseId == 0) throw new IllegalArgumentException("Course Id cannot be 0.");

        Courses course = new Courses();
        String sql = "SELECT par FROM golf_courses WHERE course_id = ?";

        try{
            int par = jdbcTemplate.queryForObject(sql, int.class, courseId);
            course.setPar(par);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCoursePar", e);
        }
        //Returning course here will return the object as a whole if we want just the yardage/par then we should
        // just return the int value of par or yardage
        return course;
    }

    @Override
    public Courses getCourseHoles(int courseId) {
        if(courseId == 0) throw new IllegalArgumentException("Course Id cannot be 0.");

        Courses course = new Courses();
        String sql = "SELECT holes FROM golf_courses WHERE course_id = ?";

        try{
            int holes = jdbcTemplate.queryForObject(sql, int.class, courseId);
            course.setHoles(holes);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getCourseHoles", e);
        }
        return course;
    }

    @Override
    public Courses getClubNameByCourse(int courseId) {
        if(courseId == 0) throw new IllegalArgumentException("Course Id cannot be 0");
        Courses course = new Courses();
        String sql = "SELECT club_name FROM golf_courses WHERE course_id = ?";

        try{
            String clubName = jdbcTemplate.queryForObject(sql, String.class, courseId);
            course.setClubName(clubName);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getClubNameByCourse", e);
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
            throw new DaoException("Issue with getCourseByAddress", e);
        }
        return course;
    }

    @Override
    public Courses createCourse(Courses course) {
        String sql = "INSERT INTO golf_courses (club_name, course_name, address, city, state_ab, yardage, par, holes, country) " +
                "VALUES (LOWER(TRIM(?)), ?, ?, ?, ?, ?, ?, ?, ?) RETURNING course_id;";
        try{
            int newCourseId = jdbcTemplate.queryForObject(sql, int.class, course.getClubName(), course.getCourseName(), course.getAddress(), course.getCity(), course.getState(), course.getYardage(), course.getPar(), course.getHoles(), course.getCountry());
            course = getCourseById(newCourseId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with createCourse");
        }
        return course;
    }

    //TODO MAYBE COME BACK LATER
//    public Courses insertApiDataToDb(){
//        List<Integer> newIds = new ArrayList<>();
//
//        String sql = "INSERT INTO golf_courses (club_name, course_name, address, city, state_ab, yardage, par, holes, country)" +
//                " VALUES (?, ?, ?, ?, ?, ?, ?,?, ?) RETURNING course_id;";
//        Courses[] course = service.listCourses();
//        SqlRowSet newCourseIds = jdbcTemplate.queryForRowSet(sql, course.getClubName(), course.getCourseName(), course.getAddress(), course.getCity(), course.getState(), course.getYardage(), course.getPar(), course.getHoles(), course.getCountry());
//
//        for(Courses c : courses) {
//            SqlRowSet newCourseIds = jdbcTemplate.queryForRowSet(sql, course.getClubName(), course.getCourseName(), course.getAddress(), course.getCity(), course.getState(), course.getYardage(), course.getPar(), course.getHoles(), course.getCountry());)
//        }
//
//    }


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
