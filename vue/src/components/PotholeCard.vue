<template>
  <div class="pothole-card">
    <Map v-bind:center="center"  :zoom="16" class="card-map">
      <GMarker :position="center" />
    </Map>
    <p>ID: {{ pothole.id }}</p>
    <p>Description: {{ pothole.description }}</p>
    <p>Severity: {{ pothole.severity }}</p>
    <p>Location On Roadway: {{ pothole.locationOnRoadway }}</p>
  </div>
</template>

<script>
import { Map, Marker as GMarker } from "vue2-google-maps";

export default {
  props: {
    pothole: Object,
  },
  components: { Map, GMarker },

  data() {
    return {
      center: { lat: this.pothole.latitude, lng: this.pothole.longitude },
    };
  },
};
</script>

<style>
div.pothole-card {
  display: flex;
  justify-content: space-between;
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
</style>


