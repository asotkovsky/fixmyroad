<template>
  <div id="map"></div>
</template>

<script>
import { Loader } from "@googlemaps/js-api-loader";

export default {
  data() {
    return { map: null, currentMarker: null };
  },

  methods:{
    doSomething(event){
      console.log(event)
    }
  },

  mounted() {
    const loader = new Loader({
      apiKey: process.env.VUE_APP_GOOGLE_API_KEY,
    });

    let center = { lat: 39.2, lng: 280 };
    window.navigator.geolocation.getCurrentPosition((position) => {
      center.lat = position.coords.latitude;
      center.lng = position.coords.longitude;
    });

    loader.load().then((google) => {
      this.map = new google.maps.Map(document.getElementById("map"), {
        center,
        zoom: 8,
      });

      this.currentMarker = new google.maps.Marker({ map: this.map });

      this.map.addListener("click", (e) => {
        const position = { lat: e.latLng.lat(), lng: e.latLng.lng() };
        this.currentMarker.setPosition(position);
        this.doSomething(e);
      });
    });
  },
};
</script>

<style>
#map {
  height: 500px;
}
</style>