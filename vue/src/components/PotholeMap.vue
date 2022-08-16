<template>
  <div class="map">
    <Map v-bind:center="center" id="map" :zoom="12">
    
      <GMarker v-for="currentPothole in potholes"
      :key="currentPothole.id"
      :position="{lat: currentPothole.latitude, lng: currentPothole.longitude}"
      @click="setSelectedPothole(currentPothole)"
      />
    </Map>
  </div>
</template>

<script>

import { Map, Marker as GMarker } from "vue2-google-maps";

export default {
    name:"pothole-map",
    props: ["potholes"],
    components: { Map, GMarker },
    methods: {
        setSelectedPothole(pothole){
        this.$store.commit("SET_SELECTED_POTHOLE", pothole);  
        }
    },
    data() {
        return {

        center: { lat: 39.1, lng: 280 },

        }
    },
    mounted() {
    window.navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };
    });
  },
}
</script>

<style>
#map {
  position: relative;
  height: 400px;
  width: 800px;
  justify-content: center;
}
</style>