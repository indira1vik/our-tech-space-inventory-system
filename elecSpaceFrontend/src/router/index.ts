import { createRouter, createWebHistory } from 'vue-router';
import Login from '../pages/Login.vue';
import Home from '../pages/Home.vue';
import Welcome from '../pages/Welcome.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Welcome
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/home',
      component: Home
    }
  ]
});

const publicPages = ["/", "/login"];

router.beforeEach(async (to) => {
  if (publicPages.includes(to.path)) {
    // Don't redirect logged-in users automatically here, just allow public page access
    return; // 
  } else {
    // Protected pages: redirect if not logged in
    try {
      const res = await fetch("http://localhost:8080/api/auth/me", { credentials: "include" });
      if (!res.ok) return { path: "/login", replace: true };
    } catch {
      return { path: "/login", replace: true };
    }
  }
});


export default router;