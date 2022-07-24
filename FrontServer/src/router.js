import Vue from 'vue';
import Router from 'vue-router'
import HomeView from './views/HomeView.vue'
import LoginView from './views/LoginView.vue'
import MakeAccount from './views/MakeAccount.vue'
import Board from './components/Board.vue'

Vue.use(Router)

export default new Router({
  mode:'history',
  routes:[
    {
      path: '/',
      component: HomeView
    },
    {
      path: '/board',
      component: Board
    },
    {
      path: '/make',
      component: MakeAccount
    },
    {
      path: '/login',
      component: LoginView
    },
  ]
})

