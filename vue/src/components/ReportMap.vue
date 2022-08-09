<template>



<div class="map">
  <Map v-bind:center="center" id="map" :zoom="12" v-on:click="handleClick">
    <GMarker v-if="$store.state.currentPin" :position="$store.state.currentPin" />
  </Map>
  
   </div>
</template>

<script>
import { Map, Marker as GMarker } from "vue2-google-maps";
export default {
  components: { Map, GMarker },

  data() {
    return {
      roadName: "",
      neighborhood: "",
      city: "",
      state: "",
      
      map: null,
      center: { lat: 39.1, lng: 280 },
    };
  },

  mounted() {
    window.navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };
    });
  },

  methods: {
    handleClick(event) {
        let latitude  = event.latLng.lat()
        let longitude = event.latLng.lng()
      let currentMarker = { lat:latitude, lng: longitude};
      this.$store.commit("SET_CURRENT_PIN", currentMarker)
      
    },
  },
};
</script>

<style>
#map {
  position: relative;
  height: 500px;
  width: 500px;
    
}
</style>