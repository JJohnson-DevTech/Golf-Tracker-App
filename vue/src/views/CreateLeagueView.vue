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
form {
  margin: 0 auto;
  text-align: center;
  width: 10vw;
  max-width: 500px;
  min-width: 300px;
  margin-right: 60%;
  margin-top: -3%;
}

.form-input-group {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space;
  margin-top: 25%;
  height: auto;
  width: auto;
}

#host {
  margin-left: 11%;
  color: #005e23;
}

#leagueName {
  margin-left: 11%;
  color: #005e23;
}

#course {
  margin-left: 5%;
  width: 100%;
}

#players {
  margin-left: 10%;
}

.heading {
  margin-top: 6%;
  text-align: center;
  margin-left: 11%;
}

h1 {
  font-family: "fugaz one", serif;
  font-weight: 400;
  font-style: normal;
  color: #fcf400;
  margin-bottom: -20px;
  border: 5px solid #005e23;
  margin-right: 60%;
}

.create-league-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background-image: url("@/assets/GolfHole2.png");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.submit {
  margin-top: 10%;
  margin-bottom: 10%;
}

.submit button {
  background-color: #005e23;
  color: #fcf400;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

#back {
    margin-top: 20px;
    font-family: 'Fugaz One', serif;
    font-weight: 400;
    font-style: normal;
    color: #fcf400;
    text-decoration: none; /* Ensure no underline */
}

#back:hover {
    color: #Ed0030; /* Maintain yellow color on hover */
}
</style>