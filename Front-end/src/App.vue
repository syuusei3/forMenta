<template>
  <div id="app">
    <Board/>
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
    /*
    getCard(){
      const getCardEndpoint = 'http://localhost:8000/getcard';
      console.log("getCardEndpoint",getCardEndpoint)
      axios.get(getCardEndpoint)

      .then((res)=>{
        console.log("getCard-res",res);
        localStorage.setItem('trello-lists',JSON.stringify(res.data.todos))
        this.$store.commit('getCards', res.data.todos)
      })
    } 
    */
  },
  created: function() {
        this.getTodo();
        //setInterval(this.$store.commit('getLists', this.lists), 1000)
    }, 
}

</script>
