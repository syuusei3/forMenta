<template>
  <form :class="classList" v-on:submit.prevent="addList">
    <input v-model="title"
           type="text"
           class="text-input"
           placeholder="+ Add new list"
           @focusin="startEditing"
           @focusout="finishEditing"
           @keypress.enter="addTodo()"
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
      id: '',
      title: '',
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
      return this.title.length > 0
    },
  },
  methods: {
    addList: function() {
      
      this.$store.dispatch('addlist', { id: new Date().getTime(), title: this.title })
      this.title = ''
    },
    addTodo(){
      
      const addEndpoint = 'http://localhost:8000/addtodo';
      console.log("AddEndPoint:",addEndpoint)
    
      const todo = {
        id: new Date().getTime(),
        title:this.title,
        //content:this.content
      }
      axios.post(addEndpoint,todo)//post to DB
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