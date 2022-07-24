<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
//import Board from './components/Board.vue'
import axios from 'axios'

export default {
  name: 'App',
  components: {
  },
  methods:{
    getTodo(){
      const getEndpoint = 'http://localhost:8000/gettodo';
      console.log("getEndPoint",getEndpoint)
      axios.get(getEndpoint)
      .then((res)=> {
        //this.lists = res.data.lists;
        console.log("first-res",res);
        localStorage.removeItem('trello-lists')
        localStorage.setItem('trello-lists',JSON.stringify(res.data))//into localstorage
        this.$store.commit('getLists', res.data)//good
        //this.list = localStorage('trello-lists')
      })
    },
  },
  created: function() {
        this.getTodo();
        //setInterval(this.$store.commit('getLists', this.lists), 1000)
    }, 
}

</script>
