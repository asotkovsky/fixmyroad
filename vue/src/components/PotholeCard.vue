<template>
  
    <div
      :class="{
        'pothole-card-hover': descriptionHover,
        'pothole-card-no-map': !showMap,
        'pothole-card-show-map': showMap,
      }"
      class="pothole-card"
    >
      <img
        class="severity-icon"
        v-if="pothole.severity != 0"
        v-bind:src="
          require('../assets/severity-icon-' + pothole.severity + '.png')
        "
      />
      <p v-if="pothole.statuses">{{ pothole.statuses[0].date }}</p>
      <p>{{ pothole.roadName }}</p>
      <p>{{ pothole.neighborhood }}</p>
      <p
        id="description1"
        v-show="!showMap"
        @mouseover.stop="descriptionHover = true"
        @mouseleave.stop="descriptionHover = false"
      >
        {{ pothole.description }}
      </p>
      <p id="status" v-if="pothole.statuses">
        {{ pothole.statuses[pothole.statuses.length - 1].name }}
      </p>
      <img
        class="location-on-roadway-icon"
        v-bind:src="
          require('../assets/icon-' + pothole.locationOnRoadway + '.jpg')
        "
      />
      <img
        class="show-modal-icon"
        @click="showAdminModal = true"
        v-bind:src="require('../assets/plus-icon.png')"
      />
      <admin-modal
        :pothole="pothole"
        @close="showAdminModal = false"
        v-if="showAdminModal"
      />
    </div>
  
</template>

<script>
import PotholeService from "@/services/PotholeService.js";
import AdminModal from "../views/AdminModal.vue";
export default {
  props: {
    pothole: Object,
    showMap: Boolean,
  },
  components: {
    AdminModal,
  },

  data() {
    return {
      center: { lat: this.pothole.latitude, lng: this.pothole.longitude },
      descriptionHover: false,
      showAdminModal: false,
    };
  },
  methods: {
    handleDelete() {
      PotholeService.deletePothole(this.pothole.id).then(() =>
        location.reload()
      );
    },
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
  border-style: solid;
  border-width: 3px;
  border-color: #737373;
  background-color: white;
  color: black;
  border-radius: 5px;
  align-items: center;
  justify-content: left;
  min-height: 4em;
}

div.pothole-card-no-map {
  grid-template-columns: 0.5fr 1fr 1fr 1fr 1fr 0.5fr 0.5fr 0.2fr;
}

div.pothole-card-show-map{
  grid-template-columns: 0.5fr 1fr 1fr 1fr 1fr 0.5fr 0.5fr;

}

div.pothole-card-hover {
  height: auto;
}

.card-map {
  height: 15vh;
  width: 15vw;
}

img.location-on-roadway-icon {
  width: 60px;
  height: 55px;
}

img.severity-icon {
  width: 60px;
  height: 60px;
}
</style>


