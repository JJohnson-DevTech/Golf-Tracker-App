<template>
    <div class="league-container">
      <div class="league-header">
        <div class="league-label">League Name</div>
        <div class="league-label">Course</div>
        <div class="league-label">Members</div>
      </div>
      <div v-if="leagues.length > 0" class="league-list">
        <div v-for="league in leagues" :key="league.id" class="league-row">
          <RouterLink class="league-box" :to="{ name: 'LeaderBoard' }" v-on:click="goToLeaderBoardPage">
            {{ league.leagueName }}
          </RouterLink>
          <div class="course-box">{{ league.courseName }}</div>
          <div class="members-box">{{ league.minPlayers }}</div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      leagues: {
        type: Array,
        required: true,
      },
    },
    methods: {
      goToLeaderBoardPage() {
        this.$router.push({ name: 'LeaderBoard' });
      },
    },
    mounted() {
      console.log(this.leagues);
    },
  };
  </script>
  
  <style scoped>
  .league-container {
    display: flex;
    flex-direction: column;
    align-items: start;
    width: 100%;
  }
  
  .league-header {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    width: 80%;
    margin-top: 10%;
    font-weight: bold;
    text-align: center;
    padding: 10px 0;
    border-bottom: 2px solid #000;
    background-color: rgba(0, 94, 35, 0.4);
    border-radius: 15px;
    margin-left: -18%;
  }
  
  .league-label {
    font-size: 35px;
  }
  
  .league-list {
    width: 80%;
    margin-left: -18%;
    font-size: 22px;
  }
  
  .league-row {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    text-align: center;
    padding: 10px 0;
    background-color: rgba(0, 94, 35, 0.4);
    border-radius: 15px;
    border-bottom: 2px solid #ccc;
  }
  
  .league-box {
    color: #fcf400;
    text-decoration: none;
    font-weight: bold;
  }

  .league-box:hover {
    background-color: #FCF400;
    color: #005E23;
    margin: 0 auto;
    width: 50%;
    border-radius: 15px;
    text-align: center;
  }

  
  
  .course-box, .members-box {
    font-size: 22px;
  }
  </style>