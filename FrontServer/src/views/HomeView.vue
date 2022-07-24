<template>
  <div>
    <div class="central-home">
    <h2>Imitate Trello</h2>
    <img class="home" alt="home" src="../assets/MultipleChecklist_R_blue.png">
    <button type="button" class="btn btn-primary" @click="openLogin">Login</button>
    <button type="button" class="btn btn-primary" @click="makeAccount">NewAccount</button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'HomeView',
  components: {
    //HelloWorld
  },
  data: function() {
    return {
    }
  },
  methods: {
    openLogin(){
            this.$router.push('/login')
        },
    makeAccount(){
            this.$router.push('/make')
        }
    // openLogin(){
    //         this.$router.push('/login')
    //     }
  }
}
</script>