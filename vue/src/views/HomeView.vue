<template>
  <div class="home-container">
    <div class="home-image"></div>
    <div class="home">
      <h1>Home</h1>
      <div class="page-links-container">
        <router-link class="page-link" to="/courses">View Courses</router-link>
        <router-link class="page-link" to="">View Tee Times</router-link>
        <router-link class="page-link" to="/leagues">View Leagues</router-link>
        <router-link class="page-link" to="/user">MyProfile</router-link>
      </div>
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

.page-links-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.page-link {
  font-family: 'Sriracha', serif;
  font-size: xx-large;
  font-weight: 400;
  font-style: normal;
  color: #fcf400;
  text-decoration: none;
  transition: background-color 0.3s;
  border-radius: 10px;
}

.page-link:hover {
  background-color: #005e23;
  color: #Ed0030;
}

/* Media query to adjust font size on smaller screens */
@media (max-width: 1200px) {
  .page-link {
    font-size: x-large;
  }
}

@media (max-width: 992px) {
  .page-link {
    font-size: large;
  }
}

@media (max-width: 768px) {
  .page-link {
    font-size: medium;
  }
}

@media (max-width: 576px) {
  .page-link {
    font-size: small;
  }
}</style>