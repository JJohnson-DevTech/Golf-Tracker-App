<template>
    <div>Processing your invite...</div>
  </template>
<script>

import axios from 'axios';
import { useRoute } from 'vue-router';

export default {
  setup() {
    const route = useRoute();
    const inviteCode = route.params.inviteCode;
    const userId = localStorage.getItem("userId"); // Or get from Vuex/Pinia if needed

    if (userId) {
      axios.get(`/api/invite/${inviteCode}?userId=${userId}`)
        .then(() => {
          console.log("Successfully joined the league.");
        })
        .catch(() => {
          console.error("Failed to join the league.");
        });
    } else {
      console.error("User is not logged in.");
    }
  }
};
</script>