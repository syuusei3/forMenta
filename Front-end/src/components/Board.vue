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
    }
  },
}
</script>