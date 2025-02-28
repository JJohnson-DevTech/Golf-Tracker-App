<template>
  <div class="user-profile-image"></div>
  <div class="headline">
    <div class="greeting">
      <h1>Welcome, {{ $store.state.user.firstName }}</h1>
    </div>
    <div class="logo">
      <img src="@/assets/Logo2-No-Background.png" alt="Logo" />
    </div>
  </div>
  <div class="userContainer">
    <h2>My Handicap: </h2>
    <div class="user-nav-routes">
      <router-link class="page-link" to="/courses">View Courses</router-link>
      <router-link class="page-link" to="/tee-times">My Tee Times</router-link>
      <router-link class="page-link" to="/leagues">My Leagues</router-link>
    </div>
  </div>
</template>
<script>
import { ref, onMounted } from "vue";
import authService from "../services/AuthService";
export default {
  name: "UserProfileView",
  setup() {
    const user = ref(null);
    onMounted(async () => {
      try {
        user.value = await authService.getUser();
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    });
    return { user };
  },
  methods: {
    getScore() {
      axios.get("http://localhost:9000/api/scores/course")
        .then((response) => {
          this.scores = response.data;
        })
        .catch((error) => {
          console.error("There was an error fetching the scores!", error);
        });
    }
  },
  getCourse() {
    axios
      .get("http://localhost:9000/api/courses/{courseId}")
      .then((response) => {
        this.courses = response.data;
        this.filteredCourses = this.courses;
      })
      .catch((error) => {
        console.error("There was an error fetching the courses!", error);
      });
  },
};
</script>
<style scoped>
.user-profile-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background: url('@/assets/GolfHole4.png') no-repeat center 40%;
  background-size: cover;
}
.greeting {
  font-family: 'fugaz one', serif;
}
.logo img {
  position: fixed;
  width: 25%;
  height: 25%;
  left: 45%;
  top: -1%
}
h1 {
  font-family: 'Fugaz One', serif;
  font-size: 70px;
  margin-right: 20%;
  margin-top: 15%;
  border: none;
  background: none;
}
.user-nav-routes {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}
.page-link {
  font-family: 'Sriracha', serif;
  font-size: 40px;
  font-style: normal;
  color: #FCF400;
  text-decoration: none;
  transition: background-color 0.3s;
  border-radius: 10px;
}
.page-link:hover {
  background-color: #005E23;
  color: #ED0030;
}
h2 {
  font-family: 'Fugaz One', serif;
  font-size: 60px;
  font-style: normal;
  color: #FCF400;
  border: none;
  margin-top: 10%;
  margin-left: 15%;
  margin-bottom: 10%;
}
/* Media query to adjust font size on smaller screens */
@media (max-width: 1200px) {
  .page-link {
    font-size: x-large;
  }
}
@media (max-width: 992px) {
  .page-link {
    font-size: large;
  }
}
@media (max-width: 768px) {
  .page-link {
    font-size: medium;
  }
}
@media (max-width: 576px) {
  .page-link {
    font-size: small;
  }
}
</style>