<template>
  <div class="home">
  <div>
  <img class='home-icon' v-bind:src="require('../assets/report-icon.png')">
  <h1>Report A New Pothole</h1>
  </div>
  <div>
  <img class='home-icon' v-bind:src="require('../assets/pothole-list-icon.png')">
  <h1>View Reported Potholes</h1>
  </div>
  </div>
</template>

<script>
export default {
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
</style>
