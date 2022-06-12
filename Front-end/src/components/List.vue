<template>
  <div class="list">
    <div class="listheader">
      <p class="list-title">{{ title }}</p>
      <!-- <p class="list-counter">total: {{ totalCardInList }}</p> -->
      <div class="deletelist" @click="deleteTodo({title})">×</div>
    </div>
    <draggable group="cards"
               :list="cards"
               @end="$emit('change')">
      <Card v-for="(item, index) in cards"
            :body="item.body"
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
  props: {
    title: {
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
    deleteTodo(list) {
            const deleteEndpoint = 'http://localhost:8000/deletetodo';
            console.log("DeleteEndPoint",deleteEndpoint)
            console.log("list",list)
            console.log("listIndex",this.listIndex)

            if(confirm('本当にこのリストを削除しますか？')){
              this.$store.dispatch('removelist', { listIndex: this.listIndex })
              axios.post(deleteEndpoint, list)
            // .then((res)=> {
            //     this.lists = res.data.list;
            // })
          }
        }
      }
}
</script>