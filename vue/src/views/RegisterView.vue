<template>
  <body class="registerContainer">
    <div class="background-image">
      <img src="@/assets/GolfHole7.png">
    </div>
    <div class="CaddyHack">
      <img src="@/assets/Logo2-No-Background.png" />
    </div>
    <div id="register" class="text-center">
      <form v-on:submit.prevent="register">
        <h1>Create Account</h1>
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <br>
        <br>
        <div class="form-input-group">
          <label for="firstName">First Name</label>
          <input type="text" id="firstName" v-model="user.firstName" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="lastName">Last Name</label>
          <input type="text" id="lastName" v-model="user.lastName" required />
        </div>
        <div class="form-input-group">
          <label for="email">Email</label>
          <input type="email" id="email" v-model="user.email" required />
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password</label>
          <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
        </div>
          <button type="submit">Create Account</button>
          <p><router-link id="router-login" v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
      </form>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        firstName: "",
        lastName: "",
        email: "",
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>

.background-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
}

.background-image img {
  width: 100%;
  height: 100%;
}

.form-input-group {
  margin-bottom: 0.5rem;
  font-family: "Sriracha", serif;
}


input[type=text], input[type=email], input[type=password] {
  width: 50%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  border: 2px solid #fcf400;
  color:#fcf400;
  font-size: 20px;
  border-radius: 10px;
  background-color: rgba(0, 94, 35, 0.5);
}

h1 {
  font-family: "Fugaz One", serif;
  text-align: center;
  width: 100%;
  color: #fcf400;
}

.text-center {
  text-align: center;
  margin-top: 10%;
}

#router-login {
  color: #fcf400;
}

#router-login:hover {
  color: #005e23;
}

.CaddyHack {
  position: absolute;
  height: 25%;
  width: 25%;
  margin-left: 26%;
  margin-top: -2.5%;
}

.CaddyHack img {
  width: 100%;
  height: 100%;
}

.text-center {
  text-align: center;
  margin-top: 25%;
  position: relative;
}

#register {
  position: absolute;
  right: 10%;
  font-size: 30px;
  transform: translate(-30%, -50%);
  width: 100%;
}

button {
  background-color: rgba(0, 94, 35, 0.5); /* Green background */
  color: #fcf400; /* Font color */
  border: 2px solid #fcf400; /* Solid green border */
  padding: 10px 20px;
  font-size: 22px;
  margin-top: 30px;
  font-family: "Fugaz One", serif;
  cursor: pointer;
  border-radius: 15px;
  transition: background-color 0.3s, color 0.3s;
}

button:hover {
  background-color: #fcf400; /* Yellow background */
  color: rgba(0, 94, 35, 1); /* Green text */
  border: 2px solid rgba(0, 94, 35, 1);
}

</style>