<template>
  <form id="createLeague" @submit.prevent="createLeague">
    <div class="form-input-group">
      <div class="admin">
        <label for="host">League Administrator</label>
        <input type="text" id="host" placeholder="Your Username" v-model="league.host" required autofocus />
      </div>
      <div class="league">
        <label for="leagueName">League Name</label>
        <input type="text" id="leagueName" v-model="league.leagueName" required autofocus />
      </div>
      <div class="course">
        <label for="course">Course</label>
        <input type="text" id="course" v-model="courseSearch" @input="filterCourses" @focus="showCourseList = true"
          @blur="hideCourseList" required autofocus />
        <ul v-if="showCourseList && filteredCourses.length" class="course-list">
          <li v-for="course in filteredCourses" :key="course.id" @click="selectCourse(course)">
            {{ course.course_name }}
          </li>
        </ul>
      </div>
      <div class="players">
        <label for="players">Number of Players</label>
        <input type="number" id="players" v-model="league.players" min="4" required autofocus />
      </div>
    </div>
    <div class="submit">
      <button type="submit">Create League</button>
    </div>
    <div class="link">
      <label for="generated-link">League Link</label>
      <textarea v-model="link" id="generated-link"></textarea>
    </div>
  </form>
</template>

<script>

import axios from "axios";


export default {
  data() {
    return {
      league: {
        host: "",
        leagueName: "",
        course: 0,
        players: 4,
        link: "",
      },
      courses: [],
      courseSearch: "",
      filteredCourses: [],
      showCourseList: false,
      link:"",
    };
  },
  methods: {
    async createLeague() {

      try{
        const response = await axios.post('http://localhost:9000/api/leagues', {
          leagueName: this.league.leagueName,
          minPlayers: this.league.players,
          courseId: this.league.course,
          leagueHost: this.$store.state.user.id,
        });
        this.league = response.data;
        this.link = this.league.inviteLink;
        
      } catch(error) {
        this.error = error.response ? error.response.data.message : 'Something wrong with createLeague()';
      };
    
      // Logic to create a league
      console.log("League created:", this.league);
    },

    
    getCourses() {
      axios
        .get("http://localhost:9000/api/courses")
        .then((response) => {
          this.courses = response.data;
          this.filteredCourses = this.courses;
        })
        .catch((error) => {
          console.error("There was an error fetching the courses!", error);
        });
    },
    filterCourses() {
      this.filteredCourses = this.courses.filter(course =>
        course.course_name.toLowerCase().includes(this.courseSearch.toLowerCase())
      );
    },
    selectCourse(course) {
      console.log(course);
      this.league.course = course.id;
      this.courseSearch = course.course_name;
      this.showCourseList = false;
    },
    hideCourseList() {
      setTimeout(() => {
        this.showCourseList = false;
      }, 200); // Delay to allow click event to register
    }
  },
  
  
  
  mounted() {
    this.getCourses();
  },
};
</script>

<style scoped>
.form-input-group {
  font-family: 'Sriracha', serif;
    font-size: 1.1rem;
    font-weight: 400;
    font-style: normal;
}
button {
  font-family: 'Sriracha', serif;
  margin-top: -10px;
}
.link {
  font-family: 'Sriracha', serif;
  font-size: 1.1rem;
  font-weight: 400;
  font-style: normal;
  margin-top: -5%;
}
.course-list {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.course-list li {
  padding: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
  border-radius: 10px;
}

.course-list li:hover {
  background-color: #005E23CF;
}
</style>