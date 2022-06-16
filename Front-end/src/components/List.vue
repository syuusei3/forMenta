<template>
  <div class="list">
    <div class="listheader">
      <p class="list-title">{{ title }}</p>
      <!-- <p class="list-counter">total: {{ totalCardInList }}</p> -->
      <div class="deletelist" @click="deleteTodo()">×</div>
    </div>
    <draggable group="cards"
               :list="cards"
               @end="$emit('change')">
      <Card v-for="(item, index) in cards"
            :card_title="item.card_title"
            :key="item.id"
            :cardIndex="index"
            :listIndex="listIndex"
      />
      <CardAdd :listIndex="listIndex" />
    </draggable>
  </div>
</template>
<script>
import draggable from 'vuedraggable'
import CardAdd from './CardAdd'
import Card from './Card'
import axios from 'axios'
export default {
  components: {
    CardAdd,
    Card,
    draggable
  },
  //props from board
  props: {
    title: {//this title was defined in Board
      type: String,
      required: true
    },
    listIndex: {
      type: Number,
      required: true
    },
    cards: {
      type: Array,
      required: true
    },
    listID: {
      type: Number,
      required: true
    },
    // list_title: {
    //   type: Number,
    //   required: true
    // }
  },
  computed: {
    totalCardInList() {
      return this.cards.length
    }
  },
  methods: {
    // removeList: function() {
    //   if(confirm('本当にこのリストを削除しますか？')){
    //     this.$store.dispatch('removelist', { listIndex: this.listIndex })
    //   }
    // },
    deleteTodo() {
            const deleteEndpoint = 'http://localhost:8000/deletetodo';
            console.log("DeleteEndPoint",deleteEndpoint)
            //console.log("list",list)
            console.log("listIndex",this.listIndex)
            console.log("listIndex",this.title)
            //console.log("listtitle",this.list_title)
            console.log("delete key",this.key)
            //console.log("key",this.key)
            if(confirm('本当にこのリストを削除しますか？')){
              const tmplist = {
                  id: this.listID,
                  title:this.title,
                  //content:this.content
                  }
              this.$store.dispatch('removelist', { listIndex: this.listIndex })//keyは定義しなくても良い？
              axios.post(deleteEndpoint, tmplist)
            // .then((res)=> {
            //     this.lists = res.data.list;
            // })
          }
        }
      }
}
</script>