<template>

  <div id="app">
    <span id="nav">
      <img
        class="horizontal-logo"
        @click="reDirect('/')"
        v-show="$route.name !== 'login' && $route.name !== 'register'"
        v-bind:src="require('@/assets/fixmyroad-horizontal-logo.png')"
        
      />
      <div class="links">
        <div id="logged-in-user-display" v-show="(JSON.stringify(this.$store.state.user) != '{}') && $route.name != 'home'">
        <img id="person-icon" v-bind:src="require('@/assets/person-icon.png')"/>
        <p >{{this.$store.state.user.firstName}}</p>
        </div>
        <router-link
          class="button"
          v-show="$route.name !== 'home' && $route.name !== 'login'"
          v-bind:to="{ name: 'home' }"
          tag="button"
          >Home</router-link
        >

        <router-link
          class="button"
          v-show="$route.name !== 'home' && $route.name !== 'potholes-list'"
          v-bind:to="{ name: 'potholes-list' }"
          tag="button"
          >Reported Potholes</router-link
        >

        <router-link
          class="button"
          v-show="$route.name !== 'home' && $route.name !== 'report-pothole'"
          v-bind:to="{ name: 'report-pothole' }"
          v-if="$store.state.token != ''"
          tag="button"
          >Report A Pothole</router-link
        >

        <router-link
          class="button"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          tag="button"
          >Logout</router-link
        >
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


<style>

body {
  background-color: #0079bf;
  color: white;
}

#app {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

#nav {

  display: grid;
  grid-template-areas: "logo links";
  width: 100%;
  color: white;
  
}

#person-icon{
  height: 27px;
  width: 27px;
  padding-right: 5px;
}

.links {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: flex-start;
  gap: 10px;
  grid-area: links;
}

#logged-in-user-display{
  display: flex;
justify-content: center;
font-weight: 600;
padding-top: 7px;
margin-right:5px;
}

.button {
  border-style: solid;
  border-color: #737373;
  border-radius: 5px;
  background-color: #737373;
  color: white;
  padding: 10px;
  font-weight: 750;
}

.links router-link:hover {
  background-color: #fad52f;
  color: #737373;
  border-color: #fad52f;
}

.horizontal-logo {
  cursor: pointer;
  grid-area: logo;
  height: 100px;
}

::-webkit-scrollbar {
    width: 14px;
}

::-webkit-scrollbar-thumb {
  background:#737373; 
  border-radius: 9px;
}
::-webkit-scrollbar-thumb:hover {
  background: #fad52f; 
}


</style>
