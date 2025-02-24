<template>
  <div class="login-container">
    <div class="login-image"></div>
    <div id="login">
      <div class="login_form-card">
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
        <div class="form-input-group-username">
          <label for="username"></label>
          <input type="text" id="username" placeholder="Username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group-password">
          <label for="password"></label>
          <input type="password" id="password" placeholder="Password" v-model="user.password" required />
        </div>
        <button type="submit">Sign in</button>
        <p>
          <router-link class="router-link" v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
        </p>
      </form>
    </div>
  </div>
</template>
<script>
import authService from "../services/AuthService";
export default {
  data() {
    return {
      username: '',
      password: ''
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
  font-weight: 400;
  font-style: normal;
  color: #FCF400;
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
  /* Ensures the image covers the container */
  background-position: center;
  /* Centers the image */
  background-repeat: no-repeat;
  /* Prevents the image from repeating */
}
.login_form-card {
  position: fixed;
  top: 60vh;
  left: 29vw;
  padding: -10px;
  margin: 0;
}
.login_form-card img {
  width: 34vw;
  height: 50vh;
}
#login {
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  border-radius: 10px;
  width: 300px;
}
form {
  position: absolute;
  top: 71vh;
  left: 35vw;
}
h1 {
  font-family: "Fugaz One", serif;
  font-weight: 400;
  font-style: normal;
  color: #FCF400;
  margin-bottom: 0;
}
.form-input-group-username {
  margin-bottom: 15px;
  padding-top: 7px;
  opacity: 0.5;
  display: flex;
  flex-grow: ;
}
.form-input-group-password {
  margin-bottom: 15px;
  padding-top: 10px;
}
label {
  font-family: "Fugaz One", serif;
  font-weight: 400;
  font-style: normal;
  color: #FCF400;
}
input {
  width: 100%;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #FCF400;
}
button {
  width: 100%;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #FCF400;
  background-color: #FCF400;
  color: #005E23;
  font-family: "Fugaz One", serif;
  font-weight: 400;
  font-style: normal;
}
button:hover {
  background-color: #005E23;
  color: #FCF400;
}
p {
  font-family: "Fugaz One", serif;
  font-weight: 400;
  font-style: normal;
  color: #FCF400;
}
a {
  font-family: "Fugaz One", serif;
  font-weight: 400;
}
.router-link {
  display: inline;
  white-space: nowrap;
}
</style>