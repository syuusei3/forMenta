import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const savedLists = localStorage.getItem('trello-lists')

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
    // lists: savedLists ? JSON.parse(savedLists):[
    //   /*{
    //     id:'',
    //     title:'',
    //     cards:[
    //       {
    //         id:'',
    //         body:'',
    //       },
    //     ]
    //   }*/
    // ]
  },
  actions: {
    addlist(context, payload) {
      context.commit('addlist', payload)
    },
    removelist(context, payload) {
      context.commit('removelist', payload)
    },
    addCardToList(context, payload) {
      context.commit('addCardToList', payload)
    },
    removeCardFromList(context, payload) {
      context.commit('removeCardFromList', payload)
    },
    updateList(context, payload) {
      context.commit('updateList', payload)
    },
    
  },
  mutations: {
    addlist(state, payload) {
      state.lists.push({ title: payload.title, cards:[] })
      console.log('addlist',state.lists)
    },
    removelist(state, payload) {
      state.lists.splice(payload.listIndex, 1)
      console.log('removelist',state.lists)
    },
    addCardToList(state, payload) {
      state.lists[payload.listIndex].cards.push({ body: payload.body })
      //localStorage.setItem('trello-lists', JSON.stringify(state.lists))
      console.log('addCardToList',state.lists,':',savedLists)
    },
    removeCardFromList(state, payload) {
      state.lists[payload.listIndex].cards.splice(payload.cardIndex, 1)
      console.log('removeCardFromList',state.lists)
    },
    updateList(state, payload) {
      state.lists = payload.lists
      console.log('updateList',state.lists)
    },
    getLists(state, lists) {
      //state.lists = lists
      state.lists = JSON.parse(savedLists)
      console.log('lists',lists)
      state.lists.forEach(list => {
        list.cards=[]
      })
      //localStorage.setItem('trello-lists', JSON.stringify(state.lists))
      console.log("getLists", lists)
      //state.lists.push({title: payload.title, cards:[] })
      // state.lists.forEach(list => {
      //   list.cards = []
      // })
      // console.log(state.lists)
    },
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

  console.log('savedLists',savedLists)
})

export default store
