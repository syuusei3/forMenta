<template>
  <form :class="classList" v-on:submit.prevent="addList">
    <input v-model="list_title"
           type="text"
           class="text-input"
           placeholder="+ Add new list"
           @focusin="startEditing"
           @focusout="finishEditing"
           @keypress.enter="addListTitle()"
    >
    <button type="submit"
            class="add-button"
            v-if="isEditing || titleExists">
      Add
    </button>
  </form>
</template>

<script>
import axios from 'axios'
export default {
  data: function() {
    return {
      list_id: '',
      list_title: '',//v-model
      isEditing: false,
    }
  },
  computed: {
    classList() {
      const classList = ['addlist']
      if (this.isEditing) {
        classList.push('active')
      }
      if (this.titleExists) {
        classList.push('addable')
      }
      return classList
    },
    titleExists() {
      return this.list_title.length > 0
    },
  },
  methods: {
    addList: function() {
      this.$store.dispatch('addlist', { list_id: new Date().getTime(), list_title: this.list_title })
      this.list_title = ''
    },
    addListTitle(){
      const addEndpoint = 'http://localhost:8000/addtodo';
      console.log("AddEndPoint:",addEndpoint)
    
      const tmplist = {
        id: new Date().getTime(),
        title: this.list_title,
        //content:this.content
      }
      axios.post(addEndpoint,tmplist)//post to DB
      //this.$emit('addList')///event for reload
    },
    startEditing: function() {
      this.isEditing = true
    },
    finishEditing: function() {
      this.isEditing = false
    },
  }
}
</script>
