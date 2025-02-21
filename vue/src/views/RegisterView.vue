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
label {
  margin-right: 0.5rem;
  display: block;
}
input[type=text], input[type=email], input[type=password] {
  width: 50%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  opacity: 0.5;
}
form {
  margin: 0 auto;
  text-align: left;
  width: 10vw;
  max-width: 500px;
  min-width: 300px;
  margin-right: 60%;
  margin-top: -3%
}
h1 {
  font-family: "Fugaz One", serif;
  font-weight: 400;
  font-style: normal;
  color: #fcf400;
  margin-bottom: -20px;
  margin-right: 40%
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
    margin-top: -8%;
  }

  .CaddyHack img {
    width: 100%;
    height: 100%;
  }
</style>
