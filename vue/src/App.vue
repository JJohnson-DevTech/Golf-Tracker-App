<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link id="logout-link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;&nbsp;
      <router-link id="home-link" v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''">Home</router-link>&nbsp;&nbsp;
    </div>
    <router-view />
  </div>
</template>

<style>
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
  color: #Ed0030;
}


</style>
