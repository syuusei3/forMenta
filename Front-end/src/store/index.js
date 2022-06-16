import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


const savedLists = localStorage.getItem('trello-lists')//import list from localStorage
//const savedCards = localStorage.getItem('trello-cards')

const store = new Vuex.Store({
  /*state: {
    lists: savedLists ? JSON.parse(savedLists): [
      {
        title: 'Backlog',
        cards: [
          { body: 'English' },
          { body: 'Mathematics' },
        ]
      },
      {
        title: 'Todo',
        cards: [
          { body: 'Science' }
        ]
      },
      {
        title: 'Doing',
        cards: []
      }
    ],
  },*/
  state:{
    lists: []
  },
  actions: {
    addlist(context, payload) {
      console.log('action:addlist executed.')
      context.commit('addlist', payload)
    },
    removelist(context, payload) {
      console.log('action:removelist executed.')
      context.commit('removelist', payload)
    },
    addCardToList(context, payload) {
      console.log('action:addCardToList executed.')
      context.commit('addCardToList', payload)
    },
    removeCardFromList(context, payload) {
      console.log('action:removeCardFromList executed.')
      context.commit('removeCardFromList', payload)
    },
    updateList(context, payload) {
      console.log('action:updateList executed.')
      context.commit('updateList', payload)
    },
    
  },
  mutations: {
    addlist(state, payload) {
      state.lists.push({ list_id: payload.list_id, list_title: payload.list_title, cards:[] })
      console.log('mutation:addlist',state.lists)
    },
    removelist(state, payload) {
      state.lists.splice(payload.listIndex, 1)
      console.log('mutation:removelist',state.lists)
    },
    addCardToList(state, payload) {
      //state.lists[payload.listIndex].cards.push({ body: payload.body })
      state.lists[payload.listIndex].cards.push({ cards_title: payload.body })
      //localStorage.setItem('trello-lists', JSON.stringify(state.lists))
      console.log('mutation:addCardToList',state.lists,':',savedLists)
    },
    removeCardFromList(state, payload) {
      state.lists[payload.listIndex].cards.splice(payload.cardIndex, 1)
      console.log('mutation:removeCardFromList',state.lists)
    },
    updateList(state, payload) {
      state.lists = payload.lists
      console.log('mutation:updateList',state.lists)
    },
    
    getLists(state, lists) {
      state.lists = lists
      // state.lists = JSON.parse(lists)//into state lists
      console.log('mutation:lists',lists)
      //console.log('lists',JSON.stringify(this.$store.state.lists))
      // state.lists.forEach(list => {
      //   list.cards=[]
      // })
      //localStorage.setItem('trello-lists', JSON.stringify(state.lists))
      console.log("mutation:getLists", lists)
    },
    /*
    getCards(state,cards)
    */
  },
  getters: {
    totalCardCount(state) {
      let count = 0
      state.lists.map(content => count += content.cards.length)
      return count
    }
  }
})


store.subscribe((mutation, state) => {
  localStorage.setItem('trello-lists', JSON.stringify(state.lists))

  console.log('subscribe:savedLists',savedLists)
})

export default store//export to main.js
