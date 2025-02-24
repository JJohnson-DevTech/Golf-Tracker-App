<template>
  <div class="login-container">
    <div class="logo">
      <img src="@/assets/Logo2-No-Background.png" />
    </div>
    <div class="background-image"></div>

    <div class="login">
      <div class="form-card">
        <img src="@/assets/Scorecard.png" />
        <div class="form-content">
          <form v-on:submit.prevent="login">
            <div role="alert" v-if="invalidCredentials">
              Invalid username and password!
            </div>
            <div role="alert" v-if="this.$route.query.registration">
              Thank you for registering, please sign in.
            </div>
            <div class="input-group username-group">
              <label for="username"></label>
              <input type="text" id="username" placeholder="Username" v-model="user.username" required autofocus />
            </div>
            <div class="input-group password-group">
              <label for="password"></label>
              <input type="password" id="password" placeholder="Password" v-model="user.password" required />
            </div>
            <button type="submit">Sign in</button>
            <span class="registration-link">
              <router-link class="registration" v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
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
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.login-container {
  font-family: "Sriracha", serif;
  color: #fcf400;
  position: relative;
  width: 100%;
  height: 100%;
  margin-left: -100px;
}

.logo {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-left: 10rem;
}

.logo img {
  margin-top: -40px;
  width: 20%; /* Adjust the width as needed */
}

.background-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background-image: url('@/assets/GolfHole3.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.form-card {
  position: fixed;
  bottom: 20px;
  right: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-card img {
  width: 95%; /* Adjust the width as needed */
}

.form-content {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  box-sizing: border-box;
}

form {
  margin-bottom: 3.5rem;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.input-group {
  margin-bottom: 30px;
  width: 100%;
}

.username-group {
  margin-bottom: 2rem; /* Adjust this value as needed */
}

.password-group {
  margin-top: -1rem; /* Adjust this value to move the password input up */
  margin-bottom: 7rem; /* Adjust this value as needed */
}

label {
  font-family: "Fugaz One", serif;
  color: #fcf400;
}

input {
  width: 50%;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #fcf400;
  margin-left: 3rem;
}

button {
  margin-top: -5rem; /* Adjust this value to move the button up */
  margin-bottom: 60px;
  width: 80%;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #fcf400;
  background-color: #fcf400;
  color: #005e23;
  font-family: "Fugaz One", serif;
}

button:hover {
  background-color: #005e23;
  color: #fcf400;
}

form > .registration-link {
  margin-top: -3rem; /* Adjust this value to move the registration link up */
}

form > span {
  font-family: "Fugaz One", serif;
  font-size: large;
  color: #fcf400;
  display: inline-block;
  margin-top: 10px;
}

.registration {
  padding-top: 1rem;
  display: inline;
  white-space: nowrap;
  color: #005e23;

}

.registration:hover {
  color: #ED0030;
}
</style>