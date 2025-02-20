<template>
  <div class="home">
    <h1>Home</h1>
    <router-link to="/courses">Course List</router-link>
    <p>You must be authenticated to see this</p>
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
h1 {
  font-family: 'Fugaz One', serif;
  font-weight: 400;
  font-style: normal;
  color: #fcf400;
  margin-bottom: 0;
}

p {
  font-family: 'Sriracha', serif;
  font-weight: 400;
  font-style: normal;
}
</style>