import axios from 'axios';

const golfCourseAPI = axios.create({
    baseURL: "https://golfcourseapi.com/v1/",
    headers: {
        Authorization: `Key TYIZ7KUIADD4EOMVIOUGTKEGXQ`
    }
});

export default {

        allCourses(){
            return golfCourseAPI.get('/courses')
        },

       courseById(id){
            return golfCourseAPI.get(`/courses/${id}`)
       },
       
       courseByName(name){
            return golfCourseAPI.get(`/search_query=${name}`)
       }



}