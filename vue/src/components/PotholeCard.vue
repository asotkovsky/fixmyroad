<template>
  <div class="pothole-card">
      <p>{{pothole.id}}</p>
      <p>{{pothole.longtitude}}</p>
      <p>{{pothole.latitude}}</p>
      <p>{{pothole.description}}</p>
      <p>{{pothole.severity}}</p>
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