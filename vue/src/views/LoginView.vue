<template>
  <head>
    <title>CaddyHack-Login</title>
  </head>
  <body class="login-container">
    <div class="CaddyHack">
      <img src="@/assets/Logo2-No-Background.png" />
    </div>
   <div class="background-image">
      <img src="@/assets/GolfHole3.png" />
    </div>

    <div id="login">
      <div class="login_form-card">
        <img src="@/assets/Scorecard.png" />
      </div>
      <form v-on:submit.prevent="login">
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group-username">
          <label for="username"></label>
          <input
            type="text"
            id="username" placeholder="Username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div class="form-input-group-password">
          <label for="password"></label>
          <input
            type="password"
            id="password" placeholder="Password"
            v-model="user.password"
            required
          />
        </div>
        <button type="submit" v-bind:to="{name: 'UserProfile'}">Sign in</button>
        <span>
          <router-link class="registration" v-bind:to="{ name: 'register' }"
            >Need an account? Sign up.</router-link
          >
        </span>
      </form>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
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
  body {
    font-family: "Sriracha", serif;
    font-weight: 400;
    font-style: normal;
    color: #fcf400;
    margin: 0;
    padding: 0;
  }

  .background-image {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
    background-image: url("@/assets/GolfHole3.png");
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
  }

  .background-image img {
    width: 100%;
    height: 100%;
    
 }
  
.login_form-card{
    position: fixed;
    top: 60vh;
    left: 29vw;
    padding: -10px;
    margin: 0;
  } 

  .login_form-card img{
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
    color: #fcf400;
    margin-bottom: 0;
  }

  .form-input-group-username {
    margin-bottom: 15px;
    margin-right: 20%;
    padding-top: 7px;
    opacity: 0.5;
    display: flex;
    
  }

  .form-input-group-password {
    margin-top: -9%;
    margin-right: 25%;
    padding-top: 5px;
    opacity: 0.5;
  }

  label {
    font-family: "Fugaz One", serif;
    font-weight: 400;
    font-style: normal;
    color: #fcf400;
  }

  

  input {
    width: 100%;
    padding: 5px;
    border-radius: 5px;
    border: 1px solid #fcf400;
  }

  button {
    width: 80%;
    padding: 5px;
    border-radius: 5px;
    border: 1px solid #fcf400;
    background-color: #fcf400;
    color: #005e23;
    font-family: "Fugaz One", serif;
    font-weight: 400;
    font-style: normal;
    margin-top: 10%;
    margin-right: 15%;
  }

  button:hover {
    background-color: #005e23;
    color: #fcf400;
  }

  form > span {
    font-family: "Fugaz One", serif;
    font-weight: 400;
    font-style: normal;
    font-size: large;
    color: #fcf400;
    display: inline-block;
    margin-top: 10px;
    
  }

  a {
    font-family: "Fugaz One", serif;
    font-weight: 400;
  }

  .registration {
    display: inline;
    white-space: nowrap;
  }

  .CaddyHack {
    position: absolute;
    height: 25%;
    width: 25%;
    margin-left: 26%;
    margin-top: -10%;;
  }

  .CaddyHack img {
    width: 100%;
    height: 100%;
  }
</style>
