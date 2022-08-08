<template>
  <div class="pothole-card">
      <p>{{pothole.id}}</p>
      <p>{{pothole.longitude}}</p>
      <p>{{pothole.latitude}}</p>
      <p>{{pothole.description}}</p>
      <img v-bind:src="require('../assets/severity-icon-' + pothole.severity + '.png')">
      <p>{{pothole.locationOnRoadway}}</p>
      </div>
</template>

<script>
export default {
props: {
  pothole: Object }
}

</script>

<style>
div.pothole-card {
display: flex;
justify-content: space-between;
}
</style>