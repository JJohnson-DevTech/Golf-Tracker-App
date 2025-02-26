import axios from 'axios';

const golfCourseAPI = axios
.create({
  baseURL: "http://localhost:9000/api/courses",
  headers: {
    Authorization: `Key TYIZ7KUIADD4EOMVIOUGTKEGXQ`
  }
});

export default {

  allCourses() {
    return golfCourseAPI.get('')
  },

  courseById(id) {
    return golfCourseAPI.get(`/courses/${id}`)
  },

  courseByName(name) {
    return golfCourseAPI.get(`/courses?search_query=${name}`)
  },

  addNewCourse(course) {
    return golfCourseAPI
      .post('', course)
      .then((response) => {
        console.log('Course added:', response.data); // Log response from back end
        return response.data;
      })
      .catch((error) => {
        console.error('There was an error adding the course!', error);
        throw error; // Rethrow the error or handle as needed
      });
  },

  getLink() {
    golfCourseAPI
      .get("/leagues")
      .then((response) => {
        this.link = response.data;
      })
      .catch((error) => {
        console.error("There was an error fetching the link!", error);
      });
  },
}