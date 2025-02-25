<template>
    <div class="add-course-container">
        <div class="add-course-image"></div>
        <h1>Add Course</h1>
        <form @submit.prevent="addCourse" class="add-course-form">
            <label for="club_name">Club Name:</label>
            <input type="text" id="club_name" v-model="clubName" placeholder="Enter Club Name" required>

            <label for="course_name">Course Name:</label>
            <input type="text" id="course_name" v-model="courseName" placeholder="Enter Course Name" required>

            <label for="address">Address:</label>
            <input type="text" id="address" v-model="location.address" placeholder="Enter Address" required>

            <label for="city">City:</label>
            <input type="text" id="city" v-model="location.city" placeholder="Enter City" required>

            <label for="state">State:</label>
            <input type="text" id="state" v-model="location.state" placeholder="Enter State" required>

            <label for="country">Country:</label>
            <input type="text" id="country" v-model="location.country" placeholder="Enter Country" required>

            <button type="submit">Add Course</button>
        </form>
    </div>
</template>

<script>
import APIService from '@/services/APIService.js';

export default {
    data() {
        return {
            clubName: '',
            courseName: '',
            location: {
                address: '',
                city: '',
                state: '',
                country: ''
            }
        };
    },
    methods: {
        addCourse() {
            // Call the API service to add the course
            APIService.addNewCourse({
                club_name: this.clubName,
                course_name: this.courseName,
                'location.address': this.location.address,
                'location.city': this.location.city,
                'location.state': this.location.state,
                'location.country': this.location.country
            })
                .then(() => {
                    // Course added successfully, navigate to the course list page
                    this.$router.push('/courses');
                })
                .catch(error => {
                    // Handle the error
                    console.error("There was an error adding the course!", error);
                });
        }
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

.add-course-image {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
    background-image: url('@/assets/GolfHole1.png');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}
</style>