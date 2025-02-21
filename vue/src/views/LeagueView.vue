<template>
     <div class="league-container">
        <div class="league-image"></div>

        <div>
            <h1>Leagues</h1>
            <League/>
        </div>
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
</style>