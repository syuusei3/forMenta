<template>
  <div>
    <header>
      SAP To-do
    </header>
    <main>
      <!-- <p class="info-line">All: {{ totalCardCount }} tasks</p> -->
      <div class="list-index">
      <draggable :list="lists"
                  @end="movingList"
                  class="list-index">
        <List v-for="(item, index) in lists"
              :key="item.list_id"
              :title="item.list_title" 
              :cards="item.cards"
              :listID="item.list_id"
              :listIndex="index"
              @change="movingCard"
        />
        <ListAdd />
      </draggable>
      </div>
    </main>
  </div>
</template>
<script>
import draggable from 'vuedraggable'
import ListAdd from './ListAdd.vue'
import List from './List'
import axios from 'axios'
import { mapState } from 'vuex'  ///mapstate
export default {
  components: {
    ListAdd,
    List,
    draggable
  },
  computed: {
    ...mapState([
      'lists'
    ]),
    // totalCardCount() {
    //   return this.$store.getters.totalCardCount
    // }
  },
  methods: {
    movingCard: function() {
      this.$store.dispatch('updateList', { lists: this.lists })
    },
    movingList: function() {
      this.$store.dispatch('updateList', { lists: this.lists })
    },
    addList(){
      console.log('Boardvue Addlist Event')
      this.$emit('addList')///event for reload
    },
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
        //this.getTodo();
        //setInterval(this.$store.commit('getLists', this.lists), 1000)
    }, 
}
</script>