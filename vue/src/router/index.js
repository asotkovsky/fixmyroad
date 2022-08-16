import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import PotholeList from '../views/Potholes.vue'
import ReportPotholes from '../views/ReportPotholes.vue'
import PageNotFound from '../components/404.vue'
import AdminModal from '../views/AdminModal.vue'
import Timeline from "@/components/Timeline.vue";
import AlternatePotholeList from "@/components/AlternatePotholeList.vue"


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
  
    {
      path: "/potholes",
      name: "potholes-list",
      component: PotholeList,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/report",
      name: "report-pothole",
      component: ReportPotholes,
      meta: {
        requiresAuth: true
      }

    },
    {
      path: "/404",
      name: "404",
      component: PageNotFound,
      meta: {
        requiresAuth: false
      }
    },
    { 
      path: "*", 
      redirect: "/404" 
    },
   {
    path: "/modal",
    name: "modal",
    component: AdminModal,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/timeline",
    name: "timeline",
    component: Timeline,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/potholelist2",
    name: "alternate-pothole-list",
    component: AlternatePotholeList,
    meta: {
      requiresAuth: false
  }
  }

  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
