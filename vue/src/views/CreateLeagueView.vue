<template>
  <div class="create-league-container">
    <div>
      <div class="heading">
        <h1>Create League</h1>
      </div>
      <CreateLeague />
    </div>
    <div class="nav-back">
        <router-link id="back" to="/leagues">Back</router-link>
    </div>
  </div>
</template>

<script>
import CreateLeague from "../components/CreateLeague.vue";
import League from "../components/League.vue";

export default {
  components: {
    CreateLeague,
    League,
  },

  
};
</script>

<style>

.create-league-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height:100%;
  z-index: -1;
  background: url("@/assets/GolfHole2.png") no-repeat center center; 
  background-size: cover;
}

form {
  text-align: center;
  width: 20vw;
  max-width: 500px;
  min-width: 300px;
  margin-left: 65%;
  margin-top: 10%;
}

.form-input-group {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#host, #leagueName, #course, #players {
  color: #fcf400;
  background-color: rgba(0, 94, 35, 0.5);
  border: 1px solid #fcf400;
  border-radius: 10px;
  font-size: 20px;
  margin-left: 1%;
}

#course {
  margin-left: 11%;
  width: 100%;
}

#players {
  margin-top: 20px;
  width: 15%;
}

.heading {
  margin-top: 10%;
  text-align: center;
  margin-left: 10%;
  width: 45%;
  font-size: 22px;
}

h1 {
  font-family: "fugaz one", serif;
  color: #fcf400;
  margin-bottom: -20px;
  border: 3px solid #005e23;
  margin-right: 60%;
  background-color: rgba(0, 94, 35, 0.5);
  border-radius: 20px;
}

.submit {
  margin-top: 10%;
  margin-bottom: 10%;
}

.submit button {
  font-size: 22px;
  background-color: #005e23;
  color: #fcf400;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

#back {
    font-family: 'Fugaz One', serif;
    font-size: 50px;
    color: #fcf400;
    text-decoration: none;
    margin-left: 25%;
}

#players::-webkit-inner-spin-button,
#players::-webkit-outer-spin-button {
    appearance: none; /* Remove default arrows */
    background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><polygon points="12,4 16,10 8,10" fill="%23fcf400"/><polygon points="12,20 8,14 16,14" fill="%23fcf400"/></svg>') no-repeat center;
    background-size: contain; /* Ensure it fits */
    width: 20px;
    height: 20px;
    cursor: pointer;
}

input:focus, select:focus {
    outline: 2px solid #fcf400; /* Yellow border on focus */
}

::-webkit-scrollbar {
    width: 10px; /* Width of the scrollbar */
}

::-webkit-scrollbar-thumb {
    background-color: #fcf400; /* Yellow scrollbar */
    border-radius: 10px; /* Rounded edges */
    border: 2px solid #005e23; /* Green outline */
}

::-webkit-scrollbar-track {
    background-color: rgba(0, 94, 35, 0.5); /* Green track */
    border-radius: 10px;
}

/* Firefox Scrollbar */
* {
    scrollbar-color: #fcf400 rgba(0, 94, 35, 0.5); /* Yellow thumb, green track */
}


#back:hover {
    color: #Ed0030;
}

</style>