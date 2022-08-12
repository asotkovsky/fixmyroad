<template>
<div>
  <div :class="{'pothole-card-hover' : descriptionHover}" class="pothole-card">
    <img class='severity-icon' v-if="pothole.severity != 0" v-bind:src="require('../assets/severity-icon-' + pothole.severity + '.png')">
    <p>{{pothole.statuses[0].date}}</p>
    <p>{{pothole.roadName}}</p>
    <p>{{pothole.neighborhood}}</p>
    <p id="description1" @mouseover.stop="descriptionHover = true" @mouseleave.stop="descriptionHover = false">{{pothole.description }}</p>
    <p id="status" v-if="pothole.statuses">{{pothole.statuses[(pothole.statuses.length - 1)].name}}</p>
    <img class='location-on-roadway-icon' v-bind:src="require('../assets/icon-' + pothole.locationOnRoadway + '.jpg')">
    <img class='show-modal-icon' @click="showAdminModal = true" v-bind:src="require('../assets/plus-icon.png')">
    <admin-modal :pothole="pothole" v-if="showAdminModal"/>
  </div>

</div>

</template>

<script>

import PotholeService from "@/services/PotholeService.js";
import AdminModal from '../views/AdminModal.vue';
export default {
  props: {
    pothole: Object,
  },
  components: {
    AdminModal
  },

  data() {
    return {
      center: { lat: this.pothole.latitude, lng: this.pothole.longitude },
      descriptionHover: false,
      showAdminModal: false,
    };
  },
  methods:{
    handleDelete(){
      PotholeService.deletePothole(this.pothole.id)
      .then(()=>location.reload())

    }
  }
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
  grid-template-columns: 1fr 1fr 1.5fr 2fr 1fr 1fr 1fr .1fr;

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
div.delete-button{
  font-family: sans-serif;
  font-size: 1.5em;
  background-color: red;
  text-decoration: none;
  border-radius: 5px;
  display:flex; 
  padding: .2em;
  justify-content: center;
  align-items: center;

}
</style>


