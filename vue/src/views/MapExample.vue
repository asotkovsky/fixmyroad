<template>
  <Map v-bind:center="center" id="map" :zoom="12" v-on:click="handleClick">
    <GMarker :position="currentMarker"/>
  </Map>
</template>

<script>
import { Map, Marker as GMarker } from "vue2-google-maps";
export default {
  components: { Map, GMarker },

  data() {
    return { map: null, currentMarker: null, center: { lat: 39.1, lng: 280 }};
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
      this.currentMarker = { lat: event.latLng.lat(), lng: event.latLng.lng() };
      console.log(this.currentMarker)
    },
  },
};
</script>

<style>
#map {
  height: 500px;
}
</style>