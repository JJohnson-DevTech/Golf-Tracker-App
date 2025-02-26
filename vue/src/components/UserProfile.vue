<template>
  <div class="user-profile">
  </div>
  <div v-if="user">
    <h1>Welcome, {{ user.username }}</h1>

    <div class="info-container">
      <div class="info-box">
        <section>
          <h2>Handicap</h2>
        </section>
      </div>
      <div class="info-box">
        <ul>
          <li></li>
          <li></li>
          <li></li>
        </ul>
      </div>
      <div class="info-box">
        <h2>Favorite Courses</h2>
        <ul>
          <li>{{ user.favoriteCourse }}</li>
          <li></li>
          <li></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
//import { ref, onMounted } from "vue";
import { ref, onMounted } from "vue"
import authService from "../services/AuthService";

export default {
  name: "UserProfile",
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
};
</script>

