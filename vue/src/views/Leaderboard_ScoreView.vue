<template>
  <div class="leaderboard-container">
    <div class="leaderboard-img"></div>
    <div class="leaderboard-content">
      <LeaderBoard_Score />
    </div>
  </div>
</template>

<script>
import LeaderBoard_Score from "../components/LeaderBoard_Score.vue";

export default {
  components: {
    LeaderBoard_Score,
  },
};
</script>

<style scoped>
.leaderboard-container {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

.leaderboard-img {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("@/assets/GolgHole1.png");
  background-size: cover;
  background-position: center;
  z-index: -1; /* Ensure the image is behind other content */
}

.leaderboard-content {
  position: relative;
  z-index: 1; /* Ensure the content is above the background image */
  padding: 20px;
}
</style>