<template>
    <body class="course-list-container">
        <div class="course-list-image">
            <img src="@/assets/GolfHole1.png" alt="Golf Hole" />
        </div>

        <div>
            <h1>Course List</h1>

            <div class="filters">
                <div class="filter-box">
                    <label for="clubName">Club Name:</label>
                    <input type="text" id="clubName" v-model="filters.clubName" @input="filterCourses" class="input-box" />
                </div>
                <div class="filter-box">
                    <label for="courseName">Course Name:</label>
                    <input type="text" id="courseName" v-model="filters.courseName" @input="filterCourses"
                        class="input-box" />
                </div>
                <div class="par-filter">
                    <label for="par">Par:</label>
                    <select id="par" v-model="filters.par" @change="filterCourses">
                        <option value="72">72</option>
                        <option value="68">68</option>
                        <option value="70">70</option>
                        <option value="71">71</option>
                    </select>
                </div>
                <div class="filter-box">
                    <label for="address">Address:</label>
                    <input type="text" id="address" v-model="filters.address" @input="filterCourses" class="input-box" />
                </div>
                <div class="filter-box">
                    <label for="city">City:</label>
                    <input type="text" id="city" v-model="filters.city" @input="filterCourses" class="input-box" />
                </div>
                <div class="filter-box">
                    <label for="state">State:</label>
                    <input type="text" id="state" v-model="filters.state" @input="filterCourses" />
                </div>
                <div class="filter-box">
                    <label for="country">Country:</label>
                    <input type="text" id="country" v-model="filters.country" @input="filterCourses" />
                </div>
            </div>
            <ul>
                <li v-for="course in filteredCourses" :key="course.id">
                    {{ course.clubName }} - {{ course.courseName }}
                </li>
            </ul>
        </div>
    </body>
</template>

<script>
export default {
    data() {
        return {
            filters: {
                clubName: '',
                courseName: '',
                par: '',
                address: '',
                city: '',
                state: '',
                country: ''
            },
            courses: [] // Replace with your API call to fetch the courses
        };
    },
    computed: {
        filteredCourses() {
            return this.courses.filter(course => {
                return Object.keys(this.filters).every(key => {
                    return course[key].toLowerCase().includes(this.filters[key].toLowerCase());
                });
            });
        }
    },
    methods: {
        filterCourses() {
            // Trigger re-computation of filteredCourses
        }
    },
    mounted() {
        // Fetch the courses from your API and assign them to the 'courses' data property
    }
};
</script>

<style scoped>
.course-list-container {
    position: relative;
    width: 100%;
    height: 100%;
    margin-left: -100px;
}

.course-list-image {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
}

.course-list-image img {
    width: 100%;
    height: 100%;
}

h1 {
    font-family: 'Fugaz One', serif;
    font-weight: 400;
    font-style: normal;
    color: #fcf400;
    margin-bottom: 0;
}

label {
    font-family: 'Sriracha', serif;
    font-weight: 400;
    font-style: normal;
}

.filters {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    flex-wrap: wrap;
    gap: 10px;
}

.filters>div {
    flex: 1;
    min-width: 120px;
}

.filters .par-filter {
    flex: 1.5;
    margin-right: -140px;
}

.filters label {
    display: block;
    margin-bottom: 5px;
    white-space: wrap;
}

.filters input {
    width: 130px;
    box-sizing: border-box;
}

.filter-box {
    display: flex;
    flex-direction: column;
}
</style>