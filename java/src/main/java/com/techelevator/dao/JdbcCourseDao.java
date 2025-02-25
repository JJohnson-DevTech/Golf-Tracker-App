package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Courses;
import com.techelevator.model.FavoriteCourse;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCourseDao implements CourseDao{

    private final JdbcTemplate jdbcTemplate;

    private FavoriteCourse favoriteCourse;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Courses getCourseById(int courseId) {
        if(courseId <= 0) throw new IllegalArgumentException("Course Id must be a positive integer.");
        Courses course = null;
        String sql = "SELECT course_id, club_name, course_name, address, city, state_ab, country, par FROM golf_courses WHERE course_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseId);
            if(results.next()){
                course = mapRowToCourse(results);
            } else {
                throw new DaoException("No course found with course ID: " + courseId);
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
        String sql = "SELECT course_id, club_name, course_name, address, city, state_ab, country, par FROM golf_courses;";
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
        String sql = "SELECT course_id, club_name, course_name, address, city, state_ab, country, par FROM golf_courses " +
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
        String sql = "SELECT course_id, club_name, course_name, address, city, state_ab, country, par FROM golf_courses " +
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
        String sql = "SELECT course_id, club_name, course_name, address, city, state_ab, country, par FROM golf_courses " +
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
        String sql = "SELECT course_id, club_name, course_name, address, city, state_ab, country, par FROM golf_courses WHERE address ILIKE ?";

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
    public boolean courseExists(int courseId){
        if(courseId <= 0) throw new IllegalArgumentException("Course Id needs to be greater than 0");
        String sql = "SELECT COUNT(*) FROM golf_courses WHERE course_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, courseId);
        return count != null && count > 0;
    }

    @Override
    public FavoriteCourse addNewFavoriteCourse(int userId, int courseId) {
        FavoriteCourse favoriteCourse = null;
        String sql = "INSERT INTO user_favorites (user_id, course_id) " +
                "VALUES (?, ?);";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, courseId);
            while(results.next()){
                favoriteCourse = mapRowToFavorite(results);
                if(favoriteCourse == null){
                    throw new DaoException("Favorite Course is null.");
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e) {
            throw new DaoException("Issue with createCourse", e);
        }
        return favoriteCourse;
    }


    @Override
    public Courses createCourse(Courses course) throws DaoException {
        if (course == null) {
            throw new IllegalArgumentException("Course object cannot be null");
        }

        String sql = "INSERT INTO golf_courses (club_name, course_name, address, city, state_ab, country ) " +
                "VALUES (LOWER(TRIM(?)), ?, ?, ?, ?, ?) RETURNING course_id;";  // Ensure no extra comma

        try {
            // Log the parameters being passed to the query
            System.out.println("Inserting course with values: ");
            System.out.println("Club Name: " + course.getClubName().trim());
            System.out.println("Course Name: " + course.getCourseName().trim());
            System.out.println("Address: " + course.getAddress());
            System.out.println("City: " + course.getCity().trim());
            System.out.println("State: " + course.getState().trim());
            System.out.println("Country: " + course.getCountry().trim());

            // Execute the query and get the new course ID
            int newCourseId = jdbcTemplate.queryForObject(sql, int.class,
                    course.getClubName().trim(),
                    course.getCourseName().trim(),
                    course.getAddress(),
                    course.getCity().trim(),
                    course.getState().trim(),
                    course.getCountry().trim());

            System.out.println("Generated course ID: " + newCourseId);

            course.setCourseId(newCourseId);  // Set the generated course_id
            return course;

        } catch (Exception e) {
            // Log the error message and stack trace for more details
            System.err.println("Error occurred while inserting course: " + e.getMessage());
            e.printStackTrace();  // This will print the stack trace to the console
            throw new DaoException("Issue with createCourse method: " + e.getMessage(), e);
        }
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
        courses.setCourseId(rs.getInt("course_id"));
        courses.setClubName(rs.getString("club_name"));
        courses.setCourseName(rs.getString("course_name"));
        courses.setAddress(rs.getString("address"));
        courses.setCity(rs.getString("city"));
        courses.setState(rs.getString("state_ab"));
        courses.setCountry(rs.getString("country"));
        courses.setPar(rs.getInt("par"));
        return courses;
    }

    private FavoriteCourse mapRowToFavorite(SqlRowSet rs){
        FavoriteCourse favoriteCourses = new FavoriteCourse();
        favoriteCourses.setUserId(rs.getInt("user_id"));
        favoriteCourses.setCourseId(rs.getInt("course_id"));
        return favoriteCourses;
    }
}
