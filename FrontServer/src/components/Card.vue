<template>
  <div class="card">
    <!-- <button class="close-button" @click="removeCardFromList"> -->
    <button class="close-button" @click="deleteCardTitle">
      ×
    </button>
    <!-- <button class="modal-button" @click="openModal">
      write
    </button> -->
    <div class="body" @click="openModal">
      {{ card_title }}
    </div>
  

  <ContentModal @close="closeModal" v-if="modal">
    <h2>◆{{ card_title }}◆</h2>
    <p>コンテンツ</p>
    <div><input v-model="message"></div>
  </ContentModal>
</div>
</template>

<script>
import ContentModal from './ContentModal'
import axios from 'axios'

export default {
  components: {
    ContentModal
  },
  data: function() {
    return {
      modal:false,
      message:''
    }
  },
  props: {
    card_title: {
      type: String,
      required: true
    },
    listIndex: {
      type: Number,
      required: true
    },
    cardIndex: {
      type: Number,
      required: true
    },
    card_id: {
      type: Number,
      required: true
    },
  },
  methods: {
    openModal(){
      this.modal = true
    },
    closeModal(){
      this.modal = false
    },
    // removeCardFromList: function() {
    //   if(confirm('本当にこのカードを削除しますか？')) {
    //     this.$store.dispatch('removeCardFromList', { cardIndex: this.cardIndex, listIndex: this.listIndex })
    //   }
    // },
    deleteCardTitle() {
            const deleteCardEndpoint = 'http://localhost:8000/deletecards';
            console.log("DeleteCardEndPoint",deleteCardEndpoint)
            //console.log("list",list)
            console.log("CardIndex:",this.cardIndex)
            console.log("card title:",this.card_title)
            console.log("list ID:",this.listIndex)
            console.log("card ID:",this.card_id)
            //console.log("key",this.key)
            if(confirm('本当にこのリストを削除しますか？')){
              const tmplist = {
                  card_id: this.card_id,
                  card_title:this.card_title,
                  //content:this.content
                  }
              this.$store.dispatch('removeCardFromList', { cardIndex: this.cardIndex, listIndex: this.listIndex })//keyは定義しなくても良い？
              axios.post(deleteCardEndpoint, tmplist)
            // .then((res)=> {
            //     this.lists = res.data.list;
            // })
          }
        }
  },
}
</script>