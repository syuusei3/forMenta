<template>
  <form :class="classList" @submit.prevent="addCardToList">
    <input v-model="card_title"
           type="text"
           class="text-input"
           placeholder="Add new card"
           @focusin="startEditing"
           @focusout="finishEditing"
           @keypress.enter="addCardTitle()"
    />
    <button type="submit"
            class="add-button"
            v-if="isEditing || bodyExists">
      Add
    </button>
  </form>
</template>


<script>
import axios from 'axios'
export default {
  props: {
    listIndex: {
      type: Number,
      required: true,
    },
    listID: {
      type: Number,
      required: true,
    },
    // card_title: {
    //   type: String,
    //   required: true,
    // },
  },
  data: function() {
    return {
      card_title: '',
      list_id:'',
      isEditing: false,
    }
  },
  computed: {
    classList() {
      const classList = ['addcard']
      if (this.isEditing) {
        classList.push('active')
      }
      if (this.bodyExists) {
        classList.push('addable')
      }
      return classList
    },
    bodyExists() {
      return this.card_title.length > 0
    }
  },
  methods: {
    startEditing: function() {
      this.isEditing = true
    },
    finishEditing: function() {
      this.isEditing = false
    },
    addCardToList: function() {
      console.log("card title:", this.card_title)
      console.log("List Index:", this.listIndex)
      console.log("List ID:", this.listID)
      this.$store.dispatch('addCardToList', { card_title: this.card_title, listIndex: this.listIndex, list_id: this.listID })
      this.card_title = ''
      //console.log('methods addCardToList ListIndex:',this.lists[JSON.stringify(this.listIndex)])
    },
    addCardTitle(){
      const addCardEndpoint = 'http://localhost:8000/addcards';
      console.log("AddCardEndPoint:",addCardEndpoint)
      //from List.vue
      const tmplist = {
        //task_id : this.listIndex,
        card_title : this.card_title,
        list_id : this.listID,
        // card_title : this.title,
        //content:this.content
      }
      axios.post(addCardEndpoint,tmplist)//post to DB
      //this.$emit('addList')///event for reload
    },
  }
}
</script>