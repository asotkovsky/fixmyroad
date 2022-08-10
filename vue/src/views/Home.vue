<template>
  <div class="home">
  <div>
  <img class='home-icon' @click="reDirect('/report')" v-bind:src="require('../assets/report-icon.png')">
  <a href="/report"> Report A New Pothole</a>
  </div>
  <div>
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

.home {
  display: grid;
  grid-template-columns: 500px 500px;
  justify-items: center;
  justify-content:center;
}

.home div {
  display: flex;
  margin: 100px;
  height: 400px;
  width: 500px;
  flex-direction: column;
  align-items: center;
  justify-content: end;

}

.home img {
  height: 300px;
  width: 300px;
}

.home img:hover {
height: 375px;
width: 375px;
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
</style>
