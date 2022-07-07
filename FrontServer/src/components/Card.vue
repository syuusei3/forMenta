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

    <tr>
            <p>コンテンツ</p>
            
            <input v-model="content"
                   type="text"
                  @keypress.enter="addContent()"
                  @kyepress.enter="addContetToCard()"/>
            <button v-on:click="addContent()">追加</button>
            
     </tr>
    <tbody>
            <tr v-for="tmp in contents" v-bind:key="tmp.content_id">
                <td> {{tmp.content}}</td>
                <td>
                <button v-on:click="deleteContent(tmp)">削除</button> 
                </td>
            </tr>
    </tbody>
    
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
      //content_title,
      modal:false,
      content:'',
      content_id:''
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
    contents: {
      type: Array,
      required: true
    },
    // content_title: {
    //   type: String,
    //   required: true
    // },
  },
  methods: {
    openModal(){
      console.log("modal open" , this.contents)
      this.modal = true
    },
    closeModal(){
      console.log("modal close")
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
            //console.log("contents on card.vue:",this.contents)
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
        },



    addContentToCard(){
      console.log("content title:", this.content)
      console.log("Card Index:", this.cardIndex)
      console.log("Card ID:", this.cardID)
      this.$store.dispatch('addContentToCard', {content_id: new Date().getTime(), content: this.content, card_id: this.cardID, listIndex: this.listIndex, cardIndex: this.cardIndex })
      this.card_title = ''
      //console.log('methods addCardToList ListIndex:',this.lists[JSON.stringify(this.listIndex)])
    },
    addContent(){
      const addContentEndpoint = 'http://localhost:8000/addcontents';
      console.log("AddContentdEndPoint:",addContentEndpoint)
      console.log("this.content_title:",this.content_title)
      //from List.vue

      const tmp = {
        content_id : new Date().getTime(),//card_id
        content : this.content,
        card_id : this.card_id,
        // card_title : this.title,
        // content:this.content
      }
      console.log("send list:",tmp)

      axios.post(addContentEndpoint,tmp)//post to DB
      //this.$emit('addList')///event for reload
    },
    deleteContent(tmp) {
            const deleteContentEndpoint = 'http://localhost:8000/deletecontents';
            console.log("DeleteEndPoint",deleteContentEndpoint)
            //console.log("list",list)
            //console.log("key",this.key)
            if(confirm('本当にこのリストを削除しますか？')){
              //this.$store.dispatch('removelist', { listIndex: this.listIndex })//keyは定義しなくても良い？
              axios.post(deleteContentEndpoint, tmp)
            // .then((res)=> {
            //     this.lists = res.data.list;
            // })
          }
        }
  },
}
</script>