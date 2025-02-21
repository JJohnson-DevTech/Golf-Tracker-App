<template>
    <div class="user-profile">
        <div v-if="user"></div>
            
        
            
    </div>
</template>

<script>
//import { ref, onMounted } from "vue";
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

