<template>
  <form id="createLeague">
    <div class="form-input-group">
      <div class="admin">
        <label for="host">League Administrator</label>
        <input type="text" id="host" v-model="league.host" required autofocus />
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
        <input
          type="select"
          id="course"
          v-model="league.course"
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
</template>

<script>
import AuthService from "../services/AuthService";

export default {
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
  },
};
</script>