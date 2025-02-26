<template>
    <div class="add-tee-time-container">
        <div class="add-tee-time-image"></div>
        <h1>Create Tee Time</h1>
        <form @submit.prevent="addTeeTime" class="add-tee-time-form">
            <label for="club_name">Club Name:</label>
            <input 
                type="text" 
                id="club_name" 
                v-model="clubName" 
                @focus="showClubNameList = true"
                @blur="handleClubNameBlur" 
                placeholder="Enter Club Name" 
                required 
            />

            <div v-if="showClubNameList && filteredClubNames.length" class="filtered-list">
                <ul>
                    <li 
                        v-for="club in filteredClubNames" 
                        :key="club.id" 
                        @click="selectClub(club)"
                    >
                        {{ club.club_name }}
                    </li>
                </ul>
            </div>

            <label for="course_name">Course Name:</label>
            <input 
                type="text" 
                id="course_name" 
                v-model="courseName" 
                @focus="showCourseNameList = true"
                @blur="handleCourseNameBlur" 
                placeholder="Enter Course Name" 
                required 
            />
            
            <div v-if="showCourseNameList && filteredCourseNames.length" class="filtered-list">
                <ul>
                    <li 
                        v-for="course in filteredCourseNames" 
                        :key="course.id" 
                        @click="selectCourse(course)"
                    >
                        {{ course.course_name }}
                    </li>
                </ul>
            </div>
            
            <label for="date">Date</label>
            <input type="date" id="date" v-model="tee_time.date" required />
            
            <label for="time">Time</label>
            <input type="time" id="time" v-model="tee_time.time" required />
            
            <label for="players">Number of Players:</label>
            <input type="number" id="players" v-model="teeTimeDto.numPlayers" min="1" required />
            
            <label for="league">League (Optional)</label>
            <input type="text" id="league" v-model="teeTimeDto.leagueId" placeholder="Enter League Name" />
            
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
                club_name: '',
                course_name: '',
                tee_time: '',
                num_players: 1,
                league_name: ''
            },
            teeTimeDto: {
                courseId: 0,
                userId: 0,
                leagueId: null,
                teeTime: '',
                numPlayers: 1
            },
            clubName: '',  // Bind this to the input for filtering club name
            courseName: '', // Bind this to the input for filtering course name
            courses: [],  // Store the list of courses fetched from the API
            showClubNameList: false,  // Boolean to show filtered club names list
            showCourseNameList: false,  // Boolean to show filtered course names list
        };
    },
    computed: {
        fullTeeTime() {
            const date = new Date(this.tee_time.date + 'T' + this.tee_time.time);
            return date.getTime();
        },
        // Filter courses based on clubName when the club input is focused
        filteredClubNames() {
            return this.courses.filter(course =>
                course.club_name.toLowerCase().includes(this.clubName.toLowerCase())
            );
        },
        // Filter courses based on courseName when the course input is focused
        filteredCourseNames() {
            return this.courses.filter(course =>
                course.course_name.toLowerCase().includes(this.courseName.toLowerCase())
            );
        }
    },
    methods: {
        addTeeTime() {
            this.teeTimeDto.userId = this.$store.state.user.id;
            this.teeTimeDto.teeTime = this.fullTeeTime;
            // Call the API service to add the tee time
            APIService.addTeeTime(
                this.teeTimeDto
            )
                .then(() => {
                    // Tee time added successfully, navigate to the tee time list page
                    this.$router.push('/tee-times');
                })
                .catch(error => {
                    // Handle the error
                    console.error("There was an error adding the tee time!", error);
                });
        },
        async fetchCourses() {
            try {
                const response = await APIService.allCourses();
                this.courses = response.data;
            } catch (error) {
                console.error(error);
            }
        },
        // Method to select a club name from the list and populate the input
        selectClub(club) {
            this.clubName = club.club_name;
            this.showClubNameList = false;  // Hide the filtered list
        },
        // Method to select a course name from the list and populate the input
        selectCourse(course) {
            this.courseName = course.course_name;
            this.teeTimeDto.courseId = course.id;
            this.showCourseNameList = false;  // Hide the filtered list
        },
        // Method to handle blur for club name input to hide the list
        handleClubNameBlur() {
            setTimeout(() => { 
                // Ensure list is not hidden immediately after click
                this.showClubNameList = false;
            }, 100);
        },
        // Method to handle blur for course name input to hide the list
        handleCourseNameBlur() {
            setTimeout(() => { 
                // Ensure list is not hidden immediately after click
                this.showCourseNameList = false;
            }, 100);
        }
    },
    mounted() {
        this.fetchCourses();  // Fetch courses when the component is mounted
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

.filtered-list ul {
    padding: 0;
    margin: 0;
    list-style-type: none;
}

.filtered-list li {
    cursor: pointer;
    border-radius: 10px;
    transition: 0.3s;
    font-family: 'Sriracha', serif;
}

.filtered-list li:hover {
    background-color: #005e23;
}

#players {
    width: 8%;
    max-width: 300px; 
    min-height: 2em;
    border-radius: 20px; 
    outline: none; 
    text-align: center; 
    margin: 5px 0; 
}
</style>