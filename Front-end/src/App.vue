<template>
  <div id="app">
    <Board />
  </div>
</template>

<script>
import Board from './components/Board.vue'
import axios from 'axios'

export default {
  name: 'App',
  components: {
    Board
  },
  methods:{
    getTodo(){
      const getEndpoint = 'http://localhost:8000/gettodo';
      console.log(getEndpoint)
      axios.get(getEndpoint)
      .then((res)=> {
        //this.lists = res.data.lists;
        console.log("first-res",res);
        //これ重要
        localStorage.setItem('trello-lists',JSON.stringify(res.data.todos))
        this.$store.commit('getLists', res.data.todos)//good
        //localStorage.setItem('trello-lists',JSON.stringify(res.data.todos))
        //this.list = localStorage('trello-lists')
        //this.$store.commit('updateLists',res.data.todos)
        //this.$store.commit('addLists',res.data.todos) 
      })
    },
  },
  created: function() {
        this.getTodo();
        //setInterval(this.$store.commit('getLists', this.lists), 1000)
    }, 
}

</script>
