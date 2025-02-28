<template>
  <div class="leaderboard">
    <h1>{{ league.leagueName }}</h1>
    <h1>Leaderboard</h1>
    <div class="login-container">
      <div class="logo-container"></div>
      <div class="background-image"></div>

      <div v-if="isHost">
        <p><strong>You are the creator of the league, you can update scores.</strong></p>
      </div>

      <table class="league-table">
        <thead>
            <tr>
                <th>Player</th>
                <th>Tee Time</th>
                <th>Score</th>
                
            </tr>
        </thead>
        <tbody>
            <tr v-for="score in scores" :key="score.scoreId">
                <td>{{ score.playerId }}</td>
                <td>{{ new Date(score.tee_time).toLocaleDateString() }} {{ new Date(scores.tee_time).toLocaleTimeString() }}</td>
                <td>{{ score.score }}</td>
            </tr>
        </tbody>
    </table>
<!-- 
      <div v-for="player in leaderboard" :key="player.id" class="player">
        <div class="player-score">
          <div class="player-details">
            <span>{{ player.username }}: </span>
            <span v-if="isHost">
              <input v-model="player.newScore" type="number" />
              <button @click="updateScore(player.id, player.newScore)">Update Score</button>
            </span>
            <span v-else>
              {{ player.score }}
            </span>
          </div>
          <div class="player-tee-time">
            <span>Most Recent Tee Time: {{ player.teeTime }}</span>
          </div>
        </div>
      </div> -->
    </div>
  </div>
  <div class="nav-back">
  <router-link id="back" to="/leagues">Back</router-link>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Leaderboard",
  data() {
    return {
      league: {},
      leaderboard: [],
      isHost: false,
    };
  },
  methods: {
    async fetchLeaderboard() {
      try {
        // Fetch league data from the backend
        const leagueId = this.$route.params.leagueId; // Assuming leagueId is passed as a route parameter
        const response = await axios.get(`http://localhost:9000/api/leagues/${leagueId}`);
        this.league = response.data;

        // Fetch user data to determine if the user is the creator
        const userResponse = await axios.get("http://localhost:9000/api/user");
        this.isHost = userResponse.data.username === this.league.host;

        // Initialize leaderboard with newScore and teeTime for editing
        this.leaderboard = await Promise.all(
          this.league.players.map(async (player) => {
            const teeTimeResponse = await axios.get(`http://localhost:9000/api/players/${player.id}/tee-time`);
            return {
              ...player,
              newScore: player.score,
              teeTime: teeTimeResponse.data.teeTime,
            };
          })
        );
      } catch (error) {
        console.error("Error fetching leaderboard:", error);
      }
    },
    async updateScore(userId, newScore) {
      try {
        // Send updated score to the backend
        await axios.post("http://localhost:9000/api/scores/", {
          userId,
          score: newScore,
        });

        // Update the leaderboard locally
        const player = this.leaderboard.find(p => p.id === userId);
        if (player) {
          player.score = newScore; // Update the displayed score
        }
      } catch (error) {
        console.error("Error updating score:", error);
      }
    },
  },
  mounted() {
    this.fetchLeaderboard();
  },
};
</script>

<style scoped>
/* Add basic styling */
.logo-container {
  position: absolute;
  top: 10px; /* Adjust the distance from the top */
  left: 10px; /* Adjust the distance from the left */
  width: 30%; /* Adjust the width of the logo */
  height: 30%; /* Adjust the height of the logo */
  background: url("@/assets/Logo2-No-Background.png") no-repeat center center;
  background-size: contain;
  z-index: 10; /* Ensure it stays on top of other elements */
}

.background-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background: url("@/assets/GolfHole8.jpeg") no-repeat center 90%;
  background-size: cover;
}

/* Grouping the form and scorecard image */
.leaderboard {
  padding: 20px;
}

.player {
  margin: 10px 0;
}

h1 {
  text-align: center;
}

strong {
  color: green;
}

.player-score {
  display: flex;
  align-items: center;
  margin: 10px 0;
}

.player-details {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.player-tee-time {
  margin-left: 10px;
}

input {
  width: 50px;
}
#back {
    margin-top: 20px;
    font-family: 'Fugaz One', serif;
    font-weight: 400;
    font-style: normal;
    color: #fcf400;
    text-decoration: none; /* Ensure no underline */
}

#back:hover {
    color: #Ed0030; /* Maintain yellow color on hover */
}

.league-table {
    width: 80%;
    max-width: 1000px;
    margin: 40px auto;
    border-collapse: collapse;
    background-color: rgba(255, 255, 255, 0.8);
}

.league-table th,
.league-table td {
    border: 1px solid #005e23;
    padding: 10px;
    text-align: center;
    font-family: 'Sriracha', serif;
}

.league-table th {
    background-color: #fcf400;
    color: #005e23;
}

.league-table td {
    background-color: #ffffff;
    color: #005e23;
}

.league-table tr:nth-child(even) td {
    background-color: #f2f2f2;
}
</style>