<template>
  <div id="app">
    <span id="nav">
    
      <img class='horizontal-logo'  @click="reDirect('/')" v-bind:src="require('@/assets/fixmyroad-horizontal-logo.png')"> 
      <div class='links'>
        <router-link v-bind:to="{ name: 'home' }" tag="button">Home</router-link>
        <router-link v-bind:to="{ name: 'potholes-list' }" tag="button">Reported Potholes</router-link>
        <router-link v-bind:to="{ name: 'report-pothole' }" v-if="$store.state.token != ''" tag="button">Report A Pothole</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" tag="button">Logout</router-link>
        </div>
    </span>
    <router-view />
  </div>
</template>


<script>
export default {
  methods: {
    reDirect(path){
      this.$router.push(path)
      console.log("this click")
    }

  }
}
</script>

<style scoped>

#nav {
  font-family: sans-serif;
  display: grid;
  grid-template-columns: 1fr 1fr;
  width: 100%;
  color: white;
  
}

.links {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  gap: 5px;
  border: #737373;
  
  
  
}
.links :hover{
  background-color: #a1a1a1;
  color: yellow;
  border: #737373;
  
  

}

#nav img {
  height: 100px
}
.horizontal-logo {
  cursor: pointer;


}

</style>
