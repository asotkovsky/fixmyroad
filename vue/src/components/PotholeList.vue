<template>
<div>
    <h1>Reported Potholes: {{potholes.length}}</h1>
    <div class='list-headers'>
        <p>Severity</p>
        <p>Route</p>
        <p>Neighborhood</p>
        <p>Description</p>
        <p>Location On Roadway</p>
    </div>
  <div class="potholes-list">
      <pothole-card class="pothole.card" v-for="currentPothole in potholes" :key="currentPothole.id" :pothole="currentPothole" />
      </div>
    </div>
</template>

<script>

import PotholeService from '@/services/PotholeService.js'
import PotholeCard from '@/components/PotholeCard.vue'

export default {
name: 'pothole-list',
components: {
    PotholeCard
},
data() {
    return {
        potholes: []
    }
},
created() {
PotholeService.getPotholes().then((response) => {
    this.potholes = response.data;
})
}
}
</script>

<style>

body {
    background-color: #0079BF;
    color: white;
}
div.potholes-list {
    display:flex;
    flex-direction: column;
    gap: 20px;
}

.pothole-card {
    background-color: white;
    color:black;
}

div.list-headers {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
}

p {
    font-family: sans-serif;
}

h1 {
    font-family: sans-serif;
}

</style>