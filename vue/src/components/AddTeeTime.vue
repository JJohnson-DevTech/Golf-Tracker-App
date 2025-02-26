<template>
    <div class="add-tee-time-container">
        <div class="add-tee-time-image"></div>
        <h1>Create Tee Time</h1>
        <form @submit.prevent="addTeeTime" class="add-tee-time-form">
            <label for="club_name">Club Name:</label>
            <input type="text" id="club_name" v-model="clubName" placeholder="Enter Club Name" required>

            <label for="course_name">Course Name:</label>
            <input type="text" id="course_name" v-model="courseName" placeholder="Enter Course Name" required>

            <button type="submit">Create</button>
        </form>
    </div>
</template>

<script>
import APIService from '@/services/APIService.js';

export default {
    data() {
        return {
            tee_time: {
                clubName: '',
                courseName: '',
                players: 1,
                league: ''
            },
            courses: [],
            courseSearch: "",
            filteredCourses: [],
            showCourseList: false,
        };
    },
    methods: {
        
    }
};
</script>

<style scoped>
.add-course-container {
    position: relative;
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
}

h1 {
    margin: 1px;
    border: none;
}

form {
    width: 60vw;
    max-width: 80%;
    min-width: 300px;
    padding-bottom: 2em;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 0 auto;
}

label {
    font-family: 'Sriracha', serif;
    font-size: 1.1rem;
    font-weight: 400;
    font-style: normal;
    width: 100%;
    max-width: 300px;
    text-align: center;
}

input {
    margin: 5px 0;
    width: 80%;
    max-width: 300px;
    min-height: 2em;
    border-radius: 20px;
    outline: none;
    text-align: center;
}

input:focus {
    outline: 2px solid #fcf400;
}

button {
    width: auto;
    align-self: center;
    background-color: #fcf400;
    color: #005e23;
    font-family: 'Fugaz One', serif;
}

button:hover {
    background-color: #005e23;
    color: #fcf400;
    cursor: pointer;
}

.add-tee-time-image {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
    background-image: url('@/assets/GolfHole3.png');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}
</style>