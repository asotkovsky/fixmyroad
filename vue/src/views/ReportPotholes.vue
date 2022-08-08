<template>
<div>
  <Map v-bind:center="center" id="map" :zoom="12" v-on:click="handleClick">
    <GMarker :position="currentMarker" />
  </Map>
   Road name: {{roadName}}
   Neighborhood: {{neighborhood}}
   City name: {{city}}
   State: {{state}}

   </div>
</template>

<script>
import { Map, Marker as GMarker } from "vue2-google-maps";
import PotholeService from "@/services/PotholeService.js"
export default {
  components: { Map, GMarker },

  data() {
    return {
      roadName: "",
      neighborhood: "",
      city: "",
      state: "",
      
      map: null,
      currentMarker: null,
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
      this.currentMarker = { lat:latitude, lng: longitude};
      PotholeService.getLocation (latitude, longitude)
      .then (response =>{
          let addressParts = response.data.results[0].address_components;
          console.log(addressParts);
          this.city = addressParts.find(part => part.types.includes("locality")).short_name
          this.roadName = addressParts.find(part => part.types.includes("route")).short_name
            this.neighborhood = addressParts.find(part => part.types.includes("neighborhood")).short_name
            this.state = addressParts.find(part => part.types.includes("administrative_area_level_1")).short_name
      })

    },
  },
};
</script>

<style>
#map {
  height: 500px;
}
</style>