import axios from 'axios';

const golfCourseAPI = axios.create({
    baseURL: "https://golfcourseapi.com/v1/",
    headers: {
        Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOIiwiZXhwIjoxNzM2ODY5MTk0fQ.2hpwExc3efjJDvAF0wDeGk_uXtVSfRN9wUr5oh6RH6tiRlz5OogLzlpBGIUifjm1dF5WAx8BI5vK2DmkKOh6hA`
    }
});

export default {


}