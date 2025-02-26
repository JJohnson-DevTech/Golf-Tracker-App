<template>
  <div class="leaderboard">
    <h1> {{ leagues.leagueName }}</h1>
    <h1>Leaderboard</h1>

    <div v-if="isHost">
      <p><strong>You are the creator of the league, you can update scores.</strong></p>
    </div>

    <div v-for="player in leaderboard" :key="player.id" class="player">
      <div class="player-score">
        <div class="player-details">
          <span>{{ $state.store.user.username }}: </span>
          <span v-if="isCreator">
            <input v-model="player.newScore" type="number" />
            <button @click="updateScore(player.id, player.newScore)">Update Score</button>
          </span>
          <span v-else>
            {{ player.score }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import CreateLeague from "./CreateLeague.vue";

export default {
  name: "Leaderboard",
  data() {
    return {
      leaderboard: [], // Will hold the leaderboard data
      isHost: false, // Flag to check if the user is the league creator
    };
  },

  
    
  methods: {
    async fetchLeaderboard() {
      try {
        // Fetch leaderboard data from the backend
        const response = await axios.get("http://localhost:9000/leagues/{leagueId}");
        this.leaderboard = response.data.map(player => ({ 
          ...player, 
          newScore: player.score // Add 'newScore' to bind for editing
        }));

        
        const userResponse = await axios.get("http://localhost:9000/user");
        this.isCreator = userResponse.data.isCreator; 
      } catch (error) {
        console.error("Error fetching leaderboard:", error);
      }
    },

    async updateScore(userId, newScore) {
      try {
        // Send updated score to the backend
        await axios.post("http://localhost:9000/scores/", {
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
  computed: {
    isHost() {
      return this.$state.store.user.username === this.CreateLeague.league.host;
    },
  },
  
};
</script>

<style scoped>
/* Add basic styling */
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

input {
  width: 50px;
}
</style>