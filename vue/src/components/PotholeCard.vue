<template>
<div>
  <div :class="{'pothole-card-hover' : descriptionHover}" class="pothole-card">
    <img class='severity-icon' v-if="pothole.severity != 0" v-bind:src="require('../assets/severity-icon-' + pothole.severity + '.png')">
    <p>{{pothole.roadName}}</p>
    <p>{{pothole.neighborhood}}</p>
    <p id="description1" @mouseover.stop="descriptionHover = true" @mouseleave.stop="descriptionHover = false">{{pothole.description }}</p>
    
    <p id="status" v-if="pothole.statuses">{{pothole.statuses[(pothole.statuses.length - 1)].name}}</p>
    <img class='location-on-roadway-icon' v-bind:src="require('../assets/icon-' + pothole.locationOnRoadway + '.jpg')">
    <status-form :pothole="pothole"/>
  </div>
 
</div>

</template>

<script>
import StatusForm from './StatusForm.vue';

export default {
  props: {
    pothole: Object,
  },
  components: {
    StatusForm
  },
  data() {
    return {
      center: { lat: this.pothole.latitude, lng: this.pothole.longitude },
      descriptionHover: false,
    };
  },
};
</script>

<style>

#description1 {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  max-height: 2.2em;
  margin-top: 1em;
  margin-bottom: 1em;
}

#description1:hover {
  max-height: 100%;
  overflow: visible;
  white-space: normal;
  
  


  
}


div.pothole-card {
  display: grid;
  gap: 15px;
  grid-template-columns: .7fr 1.5fr 1.5fr 2fr 1fr .7fr 1fr;
  border-style: solid;
  border-width: 3px;
  border-color: #737373;
  border-radius:5px;
  align-items: center;
  min-height:4em;
}

div.pothole-card-hover {
  height: auto
}

.card-map{
  height:15vh;
  width:15vw;
}

img.location-on-roadway-icon {
  width: 60px;
  height: 60px
}

img.severity-icon {
  width: 60px;
  height: 60px
}




</style>


