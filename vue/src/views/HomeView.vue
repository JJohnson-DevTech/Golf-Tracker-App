<template>
  <div class="home-container">
    <div class="home-image"></div>
    <div class="home">
      <h1>Home</h1>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
  data() {
    return {

    };
  },
};
</script>

<style scoped>
.home-container {
  position: relative;
  width: 100%;
  height: 100%;
  margin: auto;
}

.home-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background-image: url('@/assets/GolfHole4.png');
  background-size: cover;
  /* Ensures the image covers the container */
  background-position: center;
  /* Centers the image */
  background-repeat: no-repeat;
  /* Prevents the image from repeating */
}

h1 {
  font-family: 'Fugaz One', serif;
  font-weight: 400;
  font-style: normal;
  color: #fcf400;
  margin-bottom: 0;
  border: none;
}

p {
  font-family: 'Sriracha', serif;
  font-weight: 400;
  font-style: normal;
}

</style>