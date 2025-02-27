<template>
  <div class="login-container">
    <div class="logo-container"></div>
    <div class="background-image"></div>
    <div class="login-form-container">
      <div class="login_form-card">
        <div class="form-content">
          <form v-on:submit.prevent="login">
            <div role="alert" v-if="invalidCredentials">
              Invalid username and password!
            </div>
            <div role="alert" v-if="this.$route.query.registration">
              Thank you for registering, please sign in.
            </div>
            <div class="input-group username-group">
              <input type="text" id="username" placeholder="Username" v-model="user.username" required autofocus />
            </div>
            <div class="input-group password-group">
              <input type="password" id="password" placeholder="Password" v-model="user.password" required />
            </div>
            <button type="submit" class="login-button">Sign in</button>
            <span class="registration-link">
              <router-link class="registration" v-bind:to="{ name: 'register' }"> Need an account? Sign up.</router-link>
            </span>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status === 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/user");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (response && response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>


.logo-container {
  position: absolute;
  top: 10px; 
  left: 10px;
  width: 30%;
  height: 30%;
  background: url("@/assets/Logo2-No-Background.png") no-repeat center center;
  background-size: contain;
  z-index: 10;
}

.background-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background: url("@/assets/GolfHole3.png") no-repeat center 90%;
  background-size: cover;
}

/* Grouping the form and scorecard image */
.login-form-container {
  position: absolute;
  bottom: 5vh;
  right: 1vw;
  z-index: 1;
}

.login_form-card {
  position: relative;
  bottom: 10px;
  right: -50px;
  width: 300px;
  padding: 175px;
  background: url("@/assets/Scorecard.png") no-repeat center center;
  background-size: contain;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.input-group {
  position: absolute;
  width: 100%;
}

.username-group {
  top: 23.3%;
  margin-left: -33.5%;
}

.password-group {
  top: 30.5%; 
  margin-left: -33.5%;
}

input[type="text"],
input[type="password"] {
  font-size: 14px;
  border: 2px solid #FCF400;
  opacity: 0.6;
  font-weight: bold;
  border-radius: 5px;
  text-align: center;
  color: #005e23;
  background-color: transparent;
}

.login-button {
  position: relative;
  margin-top: 35px;
  margin-right: 20px;
  left: 1px;
  border: 1px solid #FCF400;
  border-radius: 5px;
  background-color: #005e23;
  color: #FCF400;
}

.login-button:hover {
  background-color: #FCF400;
  color: #005E23;
}

.registration-link a {
  color: #007bff;
  text-decoration: none;
}

</style>
