<template>
     <div class="league-container">
        <div class="league-image"></div>

        <div>
            <h1>Leagues</h1>
            <League/>
        </div>
        <button @click="$router.push('/create-league')">Create League</button>
    </div>
</template>

<script>
import League from '@/components/League.vue';

export default {
    components: {
        League
    },
  name: 'LeagueView',
  data() {
    return {};
  },
};
</script>

<style scoped>
.league-container {
  position: relative;
  width: 100%;
  height: 100%;
  margin-left: 20%;
}

.league-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background: url('@/assets/GolfHole6.png') no-repeat center center;
  background-size: cover;
  
}

h1 {
  font-family: 'Fugaz One', serif;
  font-size: 80px;
  color: #fcf400;
  margin-top: 10%;
  border: none;
  background: none;
  
}

button {
background-color: #005e23;
  color: #fcf400;
  padding: 15px 30px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10%;
}

</style>