<template>
  <div class="list">
    <div class="listheader">
      <p class="list-title">{{ title }}</p>
      <!-- <p class="list-counter">total: {{ totalCardInList }}</p> -->
      <div class="deletelist" @click="deleteListTitle()">×</div>
    </div>
    <draggable group="cards"
               :list="cards"
               @end="$emit('change')">
      <Card v-for="(item, index) in cards"
            :card_title="item.card_title"
            :card_id="item.card_id"
            :key="item.id"
            :cardIndex="index"
            :listID="item.list_id"
            :listIndex="listIndex"
      />
      <CardAdd :listIndex="listIndex"
               :listID="this.listID" />
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
    // card_title: {
    //   type: String,
    //   required: true
    // },
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
    deleteListTitle() {
            const deleteEndpoint = 'http://localhost:8000/deletetodo';
            console.log("DeleteEndPoint",deleteEndpoint)
            //console.log("list",list)
            console.log("listIndex:",this.listIndex)
            console.log("list title:",this.title)
            console.log("listt ID:",this.listID)
            console.log("delete key:",this.key)
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