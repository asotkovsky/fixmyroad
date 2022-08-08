<template>
  <div id="app">
    <div id="nav">
      <h1>fixmyroad</h1>
      <div class='links'>
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'potholes-list' }">Reported Potholes</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        </div>
    </div>
    <router-view />
  </div>
</template>

<style scoped>

#nav {
  font-family: sans-serif;
  display: grid;
  grid-template-columns: 1fr 1fr;
  justify-content:;
}

</style>
