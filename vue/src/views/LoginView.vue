<template>
  <div class="background-container">
    <div class="login-card">
      <input type="text" placeholder="Username" v-model="username" class="input-field" />
      <input type="password" placeholder="Password" v-model="password" class="input-field" />
      <button class="login-button">Login</button>
      <p class="signup-link" id="signup">Need an account? <a href="/signup">Sign up</a></p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: ''
    };
  }
};
</script>

<style scoped>
body, html {
  margin: 0;
  padding: 0;
  overflow: hidden;
  width: 100%;
  height: 100%;
}

/* Background Styling */
.background-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: url("@/assets/GolfHole3.jpg") no-repeat center center;
  background-size: cover;
}

/* Login Card Styling */
.login-card {
  position: absolute;
  bottom: 10px;
  right: -50px;
  width: 300px;
  padding: 175px;
  background: url("@/assets/Scorecard.png") no-repeat center center;
  background-size: contain;
  display: flex;
  flex-direction: column;
}

input[type="text"] {
  font-size: 14px;
  width: 60%;
  border: 1px solid #ccc;
  opacity: 0.4;
  border-radius: 5px;
}

input[type="password"] {
  font-size: 14px;
  width: 55%;
  border: 1px solid #ccc;
  opacity: 0.4;
  border-radius: 5px;
}

input[type="text"], input[type="password"]{
  padding: 2px;
  margin-bottom: 10px;
}

/* Login Button */
.login-button {
  width: 35%;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  margin-top: 10px;
  margin-bottom: 12px;
}

.login-button, input[type="text"], input[type="password"] {
  margin-left: -48px;
}

.login-button:hover {
  background-color: #218838;
}

/* Signup Link */
.signup-link {
  font-size: 22px;
  margin-left: -48px;
  margin-top: 50px;
}

.signup-link a {
  color: #007bff;
  text-decoration: none;
  align-self: flex-start;
}

.signup-link a:hover {
  text-decoration: underline;
}
</style>