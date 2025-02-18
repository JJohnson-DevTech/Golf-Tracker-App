<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link id="home-link" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link id="logout-link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>

html {
  background-color: #005e23;
  width: 100vh;
}

#capstone-app {
  color: #fcf400;
  margin: 0 auto;
  padding: 20px;
  width: 80%;
  font-size: larger;
}

#home-link, #logout-link {
  font-family: 'Fugaz One', serif;
  font-weight: 400;
  font-style: normal;
  color: #fcf400;
}

#home-link:hover, #logout-link:hover {
  color: white;
}

</style>
