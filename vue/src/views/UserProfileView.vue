<template>
    <header>
      <h1>Welcome, User Profile</h1>
    </header>
    <body class="userContainer">
      <div class="background-image">
        <img src="@/assets/GolfHole4.png" />
      </div>
      <div class="logo">
        <img src="@/assets/Logo2-No-Background.png" />
      </div>
      <div class="user-profile">
        <div v-if="user">
          <h1>Welcome, {{ user.username }}</h1>
            <div class="user-info">
              <h2>My Stats</h2>
          
                <h2>My Leagues</h2>
                <h2>My Favorite Courses</h2>
            </div>
        </div>
      </div>
    </body>
  </template>
  
  <script>
  
  
//   export default {
//     components: {
//       UserProfile,
//     },
//     name: "UserProfileView",
//     setup() {
//       const user = ref(null);
  
//       onMounted(async () => {
//         try {
//           user.value = await authService.getUser();
//         } catch (error) {
//           console.error("Error fetching user data:", error);
//         }
//       });
  
//       return { user };
//     },
//   };
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
  .logo {
    position: absolute;
    height: 25%;
    width: 25%;
    margin-left: 26%;
    margin-top: -10%;;
  }

  .logo img {
    width: 100%;
    height: 100%;
  }

  header h1{
    margin-right: 20%
  }
  </style>