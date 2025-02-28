<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link id="logout-link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;&nbsp;
      <router-link id="user-link" v-bind:to="{ name: 'user' }" v-if="$store.state.token != ''">My Profile</router-link>&nbsp;&nbsp;
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
}

#user-link, #logout-link {
  font-family: 'Fugaz One', serif;
  font-size: 40px;
  font-style: normal;
  color: #fcf400;
  background-color: #005e23;
  padding: 10px;
  border-radius: 25px;
  opacity: 0.8;
}

#user-link:hover, #logout-link:hover {
  color: #Ed0030;
}

</style>