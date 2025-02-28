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
    <div v-if="link">
    <p>Invite Link: <input type="text" v-model="link" readonly></p>
    <button @click="copyLink">Copy Link</button>
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
      link: "",
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
        
        this.league.leagueId = response.data.leagueId;
        this.link = response.data.inviteLink;

        console.log("League created successfully:", response.data);
      } catch(error) {
        this.error = error.response ? error.response.data.message : 'Something wrong with createLeague()';
      };
      // Logic to create a league
      console.log("League created:", this.league);
    },

    copyLink() {
      navigator.clipboard.writeText(this.link).then(() => {
        alert("Invite link copied to clipboard!");
      }).catch(err => {
        console.error("Failed to copy link:", err);
      });
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
  font-size: 1.5rem;
  color: #fcf400;  
}

button {
  font-family: 'Sriracha', serif;
  margin-top: -10px;
}

.link {
  font-family: 'Sriracha', serif;
  font-size: 1.5rem;
  font-style: normal;
  margin-top: -5%;
}

.course-list {
  list-style-type: none;
  padding: 0;
  margin: 0;
  max-height: 130px;
  overflow-y: auto;
  border: 2px solid #fcf400;
  border-radius: 10px;
  background-color: rgba(0, 94, 35, 0.3);
  width: 100%;
  margin-left: 11%;
  margin-top: 4px;
  z-index: 1;
}
.course-list li {
  padding: 5px;
  cursor: pointer;
  text-align: center;
  border-radius: 10px;
  padding: 5px 15px;
  margin: 8px 15px;
}
.course-list li:hover {
  background-color: #005E23CF;
}

</style>
