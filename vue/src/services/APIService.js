import axios from 'axios';

const golfCourseAPI = axios.create({
    baseURL: "http://localhost:9000/api/courses",
    headers: {
        Authorization: `Key TYIZ7KUIADD4EOMVIOUGTKEGXQ`
    }
});

export default {

        allCourses(){
            return golfCourseAPI.get('')
        },

       courseById(id){
            return golfCourseAPI.get(`/courses/${id}`)
       },
       
       courseByName(name){
            return golfCourseAPI.get(`/courses?search_query=${name}`)
       }
}