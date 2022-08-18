<template>
  <div class="home">
    <h1 id="welcome">Welcome {{this.$store.state.user.firstName}}!</h1>
  <div id="report-icon">  
  <img  class='home-icon' @click="reDirect('/report')" v-bind:src="require('../assets/report-icon.png')">
  <a href="/report"> Report A New Pothole</a>
  </div>
  <div id="list-icon">
  <img class='home-icon' @click="reDirect('/potholes')" v-bind:src="require('../assets/pothole-list-icon.png')">
  <a href="/potholes"> View Reported Potholes</a>
  </div>
  </div>
</template>

<script>
export default {
  methods: {
    reDirect(path){
      this.$router.push(path)
      console.log("this click")
    }

  },
  name: "home"
};
</script>

<style scoped>

.home-icon{
  cursor: pointer;
}

.home {
  display: grid;
  grid-template-areas: "welcome welcome"
                      "report-icon list-icon";
  justify-items: center;
}

.home div {
  display: flex;
  flex-direction: column;
}

#welcome{
  grid-area: welcome;
  margin: 0px;
}

#report-icon{
  grid-area: report-icon;
  align-items: center;
  padding-top: 10vh;

}

#list-icon{
  grid-area: list-icon;
  align-items: center;
  padding-top: 10vh;
}

.home img {
  height: 45vh;
  width: 45vh;
}

.home img:hover {
height: 50vh;
width: 50vh;
}

a {
  color:aliceblue;
  text-decoration: none;
  font-size: 30px;
  font-weight: bold;
  font-family: sans-serif;
  width: 300px;
  text-align: center;
  margin-top: 15px;

}
.home-icon{
  cursor: pointer;
}
</style>
