<template>
<div>
  <div class="pothole-card">
    <img class='severity-icon' v-bind:src="require('../assets/severity-icon-' + pothole.severity + '.png')">
    <p>Route</p>
    <p>Neighborhood</p>
    <p>{{ pothole.description }}</p>
    <img class='location-on-roadway-icon' v-bind:src="require('../assets/icon-' + pothole.locationOnRoadway + '.jpg')">
  </div>
</div>

</template>

<script>

export default {
  props: {
    pothole: Object,
  },

  data() {
    return {
      center: { lat: this.pothole.latitude, lng: this.pothole.longitude },
    };
  },
};
</script>

<style>

div.pothole-card {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  border-style: solid;
  border-width: 3px;
  border-color:black;
  border-radius:5px;
  align-items: center;
}

.card-map{
  height:15vh;
  width:15vw;
}

img.location-on-roadway-icon {
  width: 60px;
  height: 60px
}

img.severity-icon {
  width: 60px;
  height: 60px
}
</style>


