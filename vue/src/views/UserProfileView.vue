<template>
  
    
  
  <div class="user-profile-image"></div>
  <div class="headline"> 
    <div class="greeting">
      <h1>Welcome {{ $store.state.user.firstName }} {{ $store.state.user.lastName }},</h1>
    </div>
    
    <div class="logo">
      <img src="@/assets/Logo2-No-Background.png" alt="Logo" />
    </div>
      
  </div>
  <div class="userContainer">
    

        <div class="info-container">
          <div class="info-box-handicap">
            <section class="handicap">
              <h2>Handicap</h2>
              <p></p>
            </section>
          </div>
          <div class="info-box-leagues">
            <h2>My Leagues</h2>
            <ul>
              
            </ul>
          </div>
          <div class="info-box-scores">
            <h2>Scores</h2>
            <ul>
              
            </ul>
          </div>
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
    background-image: url('@/assets/GolfHole4.png');
    background-size: cover;
    /* Ensures the image covers the container */
    background-position: center;
    /* Centers the image */
    background-repeat: no-repeat;
    /* Prevents the image from repeating */
}
.greeting{
  font-family: 'fugaz one', serif;
}


.logo img {
  position: fixed;
  width:25%;
  height: 25%;
  left: 55%;
  top: 1%

}


h1 {
  margin-right: 20%;
  border: none;
}
.userContainer {
  height: 80vh;
  width: 100vhvh;
  
}

h2 {
  color: #fcf400;
}
.info-box-handicap {
  border: 5px double #005e23;
  margin-bottom: 30%
  
}

.info-box-leagues {
  border: 5px double #005e23;
  margin-bottom: 30%;
  
}
.info-box-scores {
  border: 5px double #005e23;
  width: 40%;
  text-align: center;
  
}

.info-container {
  display: flex;
  height: 50%;
  width: auto;
  margin-top: 35%;
  margin-left: 10%;
  justify-content: space-between;
}
</style>