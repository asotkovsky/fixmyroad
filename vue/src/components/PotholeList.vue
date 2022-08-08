<template>
  <div class="potholes-list">
      <pothole-card v-for="currentPothole in potholes" :key="currentPothole.id" :pothole="currentPothole" />
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
.potholes-list {
display:flex;
flex-direction: column;
gap: 20px;
}
</style>