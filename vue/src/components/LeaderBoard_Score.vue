<template>
  <div>
    <div class="leaderboard">
      <h1>Leaderboard</h1>
      <div class="login-container">
        <div class="logo-container"></div>
        <div class="background-image"></div>
        <div v-if="isHost">
          <p><strong>You are the creator of the league, you can update scores.</strong></p>
        </div>
        <div class="addPlayer" v-if="isHost">
          <input type="text" v-model="newPlayerName" placeholder="Add Player Name" />
          <button @click="addPlayer">Add Player</button>
        </div>
        <table class="league-table">
          <thead>
              <tr>
                  <th>Player</th>
                  <th>Score</th>
              </tr>
          </thead>
          <tbody>
              <tr v-for="score in scores" :key="score.scoreId">
                  <td>{{ score.playerId }}</td>
                  <td v-if="isHost">
                    <input v-model="score.newScore" type="number" />
                    <button @click="updateScore(score.playerId, score.newScore)">Update Score</button>
                  </td>
                  <td v-else>
                    {{ score.score }}
                  </td>
              </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="nav-back">
      <router-link id="back" to="/leagues">Back</router-link>
    </div>
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
      newPlayerName: '',
    };
  },
  methods: {
    async fetchLeaderboard() {
      try {
        const leagueId = this.$route.params.leagueId;
        const response = await axios.get(`http://localhost:9000/api/leagues/${leagueId}`);
        this.league = response.data;
        const userResponse = await axios.get("http://localhost:9000/api/user");
        this.isHost = userResponse.data.username === this.league.host;
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
        await axios.post("http://localhost:9000/api/scores/", {
          userId,
          score: newScore,
        });
        const player = this.leaderboard.find(p => p.id === userId);
        if (player) {
          player.score = newScore;
        }
      } catch (error) {
        console.error("Error updating score:", error);
      }
    },
    async addPlayer() {
      try {
        const response = await axios.post("http://localhost:9000/api/players", {
          leagueId: this.league.id,
          playerName: this.newPlayerName,
        });
        this.leaderboard.push(response.data);
        this.newPlayerName = '';
      } catch (error) {
        console.error("Error adding player:", error);
      }
    },
  },
  mounted() {
    this.fetchLeaderboard();
  },
};

</script>

<style scoped>

.logo-container {
  position: absolute;
  top: 7%;
  left: 42%;
  width: 20%;
  height: 45%;
  margin: -100px auto;
  background: url("@/assets/Logo2-No-Background.png") no-repeat center center;
  background-size: contain;
  z-index: 1;
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

.leaderboard {
  padding: 10px;
}

.player {
  margin: 10px 0;
}

h1 {
  text-align: center;
  width: 30%;
  font-size: 40px;
}

.nav-back {
  margin-top: 15%;
  
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
  color: #FCF400;
  text-decoration: none;
}

#back:hover {
  color: #ED0030;
}

.league-table {
  width: 80%;
  max-width: 1000px;
  margin: 20% auto 10% auto;
  border-collapse: collapse;
  background-color: rgba(255, 255, 255, 0.8);
}

.league-table th,
.league-table td {
  border: 1px solid #005E23;
  padding: 10px;
  text-align: center;
  font-family: 'Sriracha', serif;
}

.league-table th {
  background-color: #FCF400;
  color: #005E23;
}

.league-table td {
  background-color: #FFFFFF;
  color: #005E23;
}

.league-table tr:nth-child(even) td {
  background-color: #F2F2F2;
}

table {
  height: auto
}

</style>