<template>
    <div class="course-list-container">
        <div class="course-list-image"></div>

        <div>
            <div class="course-list-header">
                <h1>Course List</h1>

                <router-link to="/courses/add" class="add-course-button">Add New Course</router-link>
            </div>
            <CourseList :filters="filters" @update-filters="updateFilters" />

            <ul class="course-list">
                <li v-for="course in filteredCourses" :key="course.id">
                    <div class="course-box club-name">{{ toTitleCase(course.club_name) }}</div>
                    <div class="course-box course_name">{{ course.course_name }}</div>
                    <div class="course-box city">{{ course['location.city'] || 'N/A' }}</div>
                    <div class="course-box state">{{ course['location.state'] || 'N/A' }}</div>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
import APIService from '@/services/APIService.js';
import CourseList from '@/components/CourseList.vue';

export default {
    components: {
        CourseList
    },
    data() {
        return {
            filters: {
                club_name: '',
                course_name: '',
                city: '',
                state: '',
            },
            courses: [] // Initialize as an empty array
        };
    },
    computed: {
        filteredCourses() {
            console.log(this.courses);
            return this.courses.filter(course => {
                return Object.keys(this.filters).every(key => {
                    if (!this.filters[key]) {
                        return true;
                    }
                    const value = this.filters[key].toLowerCase();
                    return (course[key] && course[key].toLowerCase().includes(value)) ||
                        (course[`location.${key}`] && course[`location.${key}`].toLowerCase().includes(value));
                });
            });
        }
    },
    methods: {
        async fetchCourses() {
            try {
                const response = await APIService.allCourses();
                console.log('Fetched courses', response.data);
                if (Array.isArray(response.data)) {
                    this.courses = response.data;
                    this.logCourses(); // Log courses for debugging
                } else {
                    console.error('Unexpected response structure:', response.data);
                }
            } catch (error) {
                console.error('Error fetching courses:', error);
            }
        },
        updateFilters(updatedFilters) {
            this.filters = updatedFilters;
        },
        toTitleCase(text) {
            if (!text) return '';
            return text.replace(/\w\S*/g, (txt) => {
                return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
            });
        },
        logCourses() {
            console.log('Courses:', this.courses);
        }
    },
    mounted() {
        // Fetch the courses from your API and assign them to the 'courses' data property
        this.fetchCourses();
    }
};
</script>

<style scoped>
.course-list-container {
    position: relative;
    width: 100%;
    height: 100%;
}

.course-list-image {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
    background-image: url('@/assets/GolfHole1.png');
    background-size: cover;
    /* Ensures the image covers the container */
    background-position: center;
    /* Centers the image */
    background-repeat: no-repeat;
    /* Prevents the image from repeating */
}

h1 {
    font-family: 'Fugaz One', serif;
    font-weight: 400;
    font-style: normal;
    color: #fcf400;
    margin-bottom: 4px;
    border: none;
}
.course-list-header {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
}

.add-course-button {
    font-family: 'Fugaz One', serif;
    font-weight: 400;
    font-style: normal;
  background-color: #fcf400;
  color: #005e23;
  padding: 5px 10px;
  text-decoration: none;
  border-radius: 10px;
}

.add-course-button:hover {
  background-color: #005e23;
  color: #fcf400;
}

.course-list {
    list-style-type: none;
    padding: 0;
    margin: auto;
    max-width: 100%;
    font-family: 'Sriracha', serif;
    font-size: 1.1rem;
    font-weight: 400;
    font-style: normal;
}

.course-list li {
    display: grid;
    grid-template-columns: 2fr 2fr 2fr 1fr;
    margin-bottom: 5px;
    transition: background-color 0.3s;
    border-radius: 10px;
}

.course-list li:hover {
    background-color: #005E23CF;
    cursor: pointer;
}

.course-box {
    display: inline-block;
    flex: 1;
    text-align: left;
    box-sizing: border-box;
    padding: 0 5px;
}

.city {
    text-align: left;
    margin-left: .5em;
}

.state {
    margin: 0 auto;
    padding-right: 50%;
}

@media (max-width: 1200px) {
    .course-list {
        font-size: 1rem;
    }
}

@media (max-width: 992px) {
    .course-list {
        font-size: 0.9rem;
    }
}

@media (max-width: 768px) {
    .course-list {
        font-size: 0.8rem;
    }
}

@media (max-width: 576px) {
    .course-list {
        font-size: 0.7rem;
    }
}
</style>