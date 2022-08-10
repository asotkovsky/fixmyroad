<template>
  <div>
    <h1>Reported Potholes: {{ potholes.length }}</h1>
    <div class="list-headers">
      <p>Severity</p>
      <p>Road</p>
      <p>Neighborhood</p>
      <p>Description</p>
      <p>Location On Roadway</p>
    </div>
    <div class="potholes-list">
      <pothole-card
        class="pothole.card"
        v-for="currentPothole in potholes"
        :key="currentPothole.id"
        :pothole="currentPothole"
      />
    </div>
  </div>
</template>

<script>
import PotholeService from "@/services/PotholeService.js";
import PotholeCard from "@/components/PotholeCard.vue";

export default {
  name: "pothole-list",
  components: {
    PotholeCard,
  },
  data() {
    return {
      potholes: [],
    };
  },
  mounted() {
    PotholeService.getPotholes().then((response) => {
      this.potholes = response.data;
      this.potholes.sort((a, b) => {
        return new Date(b.datetimeReported) - new Date(a.datetimeReported);
      });
    });
  },
  unmounted(){
      console.log("pothole list unmounted")
  }
};
</script>

<style>
body {
  background-color: #0079bf;
  color: white;
}
div.potholes-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.pothole-card {
  background-color: white;
  color: black;
}

div.list-headers {
  display: grid;
  gap: 15px;
  grid-template-columns: 0.7fr 1.5fr 1.5fr 2fr 0.7fr;
}

p {
  font-family: sans-serif;
}

h1 {
  font-family: sans-serif;
}
</style>