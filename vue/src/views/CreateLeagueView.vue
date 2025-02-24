

<template>
  <div class="create-league-container">
    <div>
      <div class="heading">
        <h1>Create League</h1>
      </div>
      <!--<CreateLeague />-->
      <form id="createLeague">
        <div class="form-input-group">
          <div class="admin">
            <label for="host">League Host</label>
            <input
              type="text"
              id="host"
              v-model="league.host"
              required
              autofocus
            />
          </div>
          <div class="league">
            <label for="leagueName">League Name</label>
            <input
              type="text"
              id="leagueName"
              v-model="league.leagueName"
              required
              autofocus
            />
          </div>
          <div class="course">
            <label for="course">Course</label>
            <select
              type="select"
              id="course"
              v-model="league.course"
              required
              autofocus
            >
                <option v-for="course in courses" :key="course.id" :value="course.id">
                    {{ course.course_name }}
                </option>
            </select>
          </div>
          <div class="players">
            <label for players>Number of Players</label>
            <input
              type="number"
              id="players"
              v-model="league.players"
              min="4"
              required
              autofocus
            />
          </div>
        </div>
        <div class="submit">
          <button type="submit" v-bind:to="{ name: 'League' }">
            Create League
          </button>
        </div>
        <div class="link">
          <label for="generated-link">League Link</label>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";
import CreateLeagueView from "../components/CreateLeague.vue";
import axios from "axios";

export default {
  components: {
    CreateLeagueView,
  },
  data() {
    return {
      league: {
        host: "",
        leagueName: "",
        course: "",
        link: "",
      },
    };
  },
  methods: {
    createLeague() {
      // Logic to create a league
      console.log("League created:", this.league);
    },
    
    
    getLink() {
      axios
        .get("http://localhost:9000/api/leagues")
        .then((response) => {
          this.link = response.data;
        })
        .catch((error) => {
          console.error("There was an error fetching the link!", error);
        });
    },
  },
};
</script>

<style>
form {
  margin: 0 auto;
  text-align: center;
  width: 10vw;
  max-width: 500px;
  min-width: 300px;
  margin-right: 60%;
  margin-top: -3%;
}

.form-input-group {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space;
  margin-top: 40%;
  height: auto;
  width: auto;
  
}

#host {
  margin-left: 11%;
  color:#005e23
}

#leagueName {
  margin-left: 11%;
  color: #005e23;
}

#course {
  margin-left: %;
  width: 100%;
}



#players {
  margin-left: 10%;
  padding: 
}

.heading {
  margin-top: 10%;
  text-align: center;
  margin-left: 11%
}

h1 {
  font-family: "fugaz one", serif;
  font-weight: 400;
  font-style: normal;
  color: #fcf400;
  margin-bottom: -20px;
  border: 5px solid#005e23;
  margin-right: 60%;
}
.create-league-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background-image: url("@/assets/GolfHole2.png");
  background-size: cover;
  /* Ensures the image covers the container */
  background-position: center;
  /* Centers the image */
  background-repeat: no-repeat;
  /* Prevents the image from repeating */
}

.submit {
  margin-top: 10%;
  margin-bottom: 10%;
}
.submit button {
  background-color: #005e23;
  color: #fcf400;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>