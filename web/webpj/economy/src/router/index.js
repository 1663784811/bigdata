import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import Spider from '@/views/Spider.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Spider
  },
  {
    path: '/spider',
    name: 'spider',
    component: Spider
  }
]

const router = createRouter({
  routes,
  history: createWebHistory(),
  scrollBehavior() {
    return { top: 0 }
  }
})

export default router
