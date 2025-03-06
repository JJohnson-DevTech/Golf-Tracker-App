import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'
// Import components
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import CourseListView from '../views/CourseListView.vue';
import UserProfileView from '../views/UserProfileView.vue';
import LeagueView from '../views/LeagueView.vue';
import CreateLeagueView from '../views/CreateLeagueView.vue';
import AddCourseView from '../views/AddCourseView.vue';
import LeaderBoard_ScoreView from '../views/Leaderboard_ScoreView.vue';
import TeeTimeView from '../views/TeeTimeView.vue';
import AddTeeTimeView from '../views/AddTeeTimeView.vue';
import InviteHandler from '../components/InviteHandler.vue';
/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    redirect: () => '/login'
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/courses",
    name: "CourseList",
    component: CourseListView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/courses/add",
    name: "AddCourse",
    component: AddCourseView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/user",
    name: "user",
    component: UserProfileView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/invite/:inviteCode',
    name: 'Invite',
    component: InviteHandler,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/leagues",
    name: "Leagues",
    component: LeagueView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/create-league",
    name: "CreateLeague",
    component: CreateLeagueView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/teetimes",
    name: "TeeTimes",
    component: TeeTimeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/teetimes/add",
    name: "AddTeeTime",
    component: AddTeeTimeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/leaderboard",
    name: "LeaderBoard",
    component: LeaderBoard_ScoreView,
    meta: {
      requiresAuth: true
    }
  }
];
// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});
router.beforeEach((to) => {
  // Get the Vuex store
  const store = useStore();
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});
export default router;






