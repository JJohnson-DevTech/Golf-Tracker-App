import axios from 'axios';

const golfCourseAPI = axios
// .create({
//   baseURL: "http://localhost:9000/api/courses",
//   headers: {
//     Authorization: `Key TYIZ7KUIADD4EOMVIOUGTKEGXQ`
//   }
// });

export default {

  allCourses() {
    return golfCourseAPI.get('/api/courses')
  },

  courseById(id) {
    return golfCourseAPI.get(`/api/courses/${id}`)
  },

  courseByName(name) {
    return golfCourseAPI.get(`/api/courses?search_query=${name}`)
  },

  allTeeTimes() {
    return golfCourseAPI.get('/api/teetimes')
  },

  getTeeTimeByUserId(id) {
    return golfCourseAPI.get(`/api/teetimes/${id}`)
  },

  addTeeTime(teeTime) {
    return golfCourseAPI
    .post('/api/teetimes', teeTime)
    .then((response) => {
      console.log('Tee time added:', response.data); // Log response from back end
      return response.data;
    })
    .catch((error) => {
      console.error('There was an error adding the Tee time!', error);
      throw error; // Rethrow the error or handle as needed
    });

  },

  addNewCourse(course) {
    return golfCourseAPI
      .post('/api/courses', course)
      .then((response) => {
        console.log('Course added:', response.data); // Log response from back end
        return response.data;
      })
      .catch((error) => {
        console.error('There was an error adding the course!', error);
        throw error; // Rethrow the error or handle as needed
      });
  },

}