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
  margin-left: -100px;
}

.league-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background-image: url('@/assets/GolfHole6.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

h1 {
  font-family: 'Fugaz One', serif;
  font-weight: 400;
  font-style: normal;
  color: #fcf400;
  margin-bottom: 0;
}

button {
background-color: #005e23;
  color: #fcf400;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>