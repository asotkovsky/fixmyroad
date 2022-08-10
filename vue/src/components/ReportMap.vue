<template>
  <div class="map">
    <Map v-bind:center="center" id="map" :zoom="12" v-on:click="handleClick">
      <GMarker
        v-if=" ('lat' in $store.state.currentPin) && ('lng' in $store.state.currentPin)"
        :position="$store.state.currentPin"
      />
    </Map>
  </div>
</template>

<script>
import { Map, Marker as GMarker } from "vue2-google-maps";
import potholeService from "@/services/PotholeService.js";
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
    getShortName(name, addressParts) {
      const partOfInterest = addressParts.find((part) =>
        part.types.includes(name)
      );
      if (partOfInterest && "short_name" in partOfInterest) {
        return partOfInterest.short_name;
      } else {
        return ""
      }
    },
    handleClick(event) {
      const lat = event.latLng.lat();
      const lng = event.latLng.lng();
      const currentPin = { lat, lng };

      potholeService.getLocation(lat, lng).then((response) => {
        const addressParts = response.data.results[0].address_components;

        currentPin.city = this.getShortName("locality", addressParts);
        currentPin.neighborhood = this.getShortName(
          "neighborhood",
          addressParts
        );
        currentPin.roadName = this.getShortName("route", addressParts);
        currentPin.state = this.getShortName(
          "administrative_area_level_1",
          addressParts
        );

        this.$store.commit("SET_CURRENT_PIN", currentPin);
      });
    },
  },
};
</script>

<style>
#map {


  height: 400px;
  width: 800px;
  position: relative;
  height: 500px;
  width: 500px;

}
</style>