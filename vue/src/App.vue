<template>
  <div id="app">
    <span id="nav">
      <img class='horizontal-logo' v-bind:src="require('@/assets/fixmyroad-horizontal-logo.png')">
      <div class='links'>
        <router-link class="button" v-show="$route.name !== 'home'" v-bind:to="{ name: 'home' }" tag="button">Home</router-link>
        <router-link class="button" v-show="$route.name !== 'home'" v-bind:to="{ name: 'potholes-list' }" tag="button">Reported Potholes</router-link>
        <router-link class="button" v-show="$route.name !== 'home'" v-bind:to="{ name: 'report-pothole' }" v-if="$store.state.token != ''" tag="button">Report A Pothole</router-link>
        <router-link class="button" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" tag="button">Logout</router-link>
        </div>
    </span>
    <router-view />
  </div>
</template>

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
  
}

.button {
  border-style: solid;
  border-color: #737373;
  border-radius: 5px;
  background-color: #737373;
  color: white;
  padding: 10px;
  font-family: sans-serif;
  font-weight:750
}

.links :hover{
  background-color:#FAD52F;
  color: #737373;
  border-color: #FAD52F;
  
  

}

#nav img {
  height: 100px
}

</style>
