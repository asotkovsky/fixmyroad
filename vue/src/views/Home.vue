<template>
  <div class="home">
  <div>
  <img class='home-icon' @click="reDirect('/report')" v-bind:src="require('../assets/report-icon.png')">
  <a href="/report"> Report A New Pothole</a>
  </div>
  <div>
  <img class='home-icon' @click="reDirect('/potholes')" v-bind:src="require('../assets/pothole-list-icon.png')">
  <a href="/potholes"> View Reported Pothole</a>

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
  display: flex;
  justify-content: center;
}

.home div {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.home img {
  width: 30vw;
}
a {
  color:aliceblue;
  text-decoration: none;
  font-size: 40px;
  font-weight: bold;
  
  

}
</style>
