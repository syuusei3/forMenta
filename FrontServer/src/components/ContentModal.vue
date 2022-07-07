<template>
  <transition name="modal" appear>
    <div class="modal-overlay" @click.self="$emit('close')">
      <div class="modal-window">
        <div class="modal-content">
          <slot/>
          <!-- <h3>{{contents}}</h3> -->
         
        </div>
        <button @click="$emit('close')">close</button>
      </div>
    </div>
  </transition>
</template>


<script>
// import axios from 'axios'

export default {
  props: {
    // content: {
    //   type: String,
    //   required: true
    // },
    // cardIndex: {
    //   type: Number,
    //   required: true
    // },
    // listIndex: {
    //   type: Number,
    //   required: true
    // },
    // contentIndex: {
    //   type: Number,
    //   required: true
    // },
    // content_id: {
    //   type: Number,
    //   required: true
    // },
    // contents: {
    //   type: Array,
    //   required: true
    // },
  },
  data: function() {
    return {
      //content_id:'',
      //content: '',
      //card_id:'',
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
    // addCardToList: function() {
    //   console.log("content:", this.content)
    //   console.log("card Index:", this.cardIndex)
    //   console.log("card ID:", this.cardID)
    //   this.$store.dispatch('addCardToList', {card_id: new Date().getTime(), content: this.content, card_id: this.cardID, cardIndex: this.cardIndex, listIndex: this.listIndex })
    //   this.card_title = ''
    //   //console.log('methods addCardToList ListIndex:',this.lists[JSON.stringify(this.listIndex)])
    // },
    // addCotent(){
    //   const addContentEndpoint = 'http://localhost:8000/addcontents';
    //   console.log("AddCardEndPoint:",addContentEndpoint)
    //   //from List.vue

    //   const tmplist = {
    //     content_id : new Date().getTime(),//card_id
    //     content : this.content,
    //     card_id : this.cardID,
    //     // card_title : this.title,
    //     // content:this.content
    //   }
    //   axios.post(addContentEndpoint,tmplist)//post to DB
    //   //this.$emit('addList')///event for reload
    // },
  }
}
</script>
