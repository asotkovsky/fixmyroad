<template>
  <div
    draggable="true"
    @dragend="beingDragged = false"
    v-on:dragstart="dragStart"
    :class="{
      'hidden':beingDragged,
      'pothole-card-hover': descriptionHover,
    }"
    class="pothole-card"
  >
    <img
      class="severity-icon-pothole-card"
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
      {{ getlastStatusName() }}
    </p>
    <img
      class="location-on-roadway-icon"
      v-show="!$store.getters.currentUserIsAdmin"
      v-bind:src="
        require('../assets/icon-' + pothole.locationOnRoadway + '.jpg')
      "
    />
    <img
      v-bind:src="
        assignedTo
          ? require('../assets/checked.png')
          : require('../assets/unchecked.png')
      "
      v-show="$store.getters.currentUserIsAdmin"
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
      beingDragged: false,
    };
  },
  computed: {
    assignedTo() {
      return this.pothole.statuses.find((status) => status.name == "Assigned");
    },
  },
  methods: {
    handleDelete() {
      PotholeService.deletePothole(this.pothole.id).then(() =>
        location.reload()
      );
    },
    getlastStatusName() {
      let filteredStatuses = [];
      this.pothole.statuses.forEach((status) => {
        if (status.public == true) {
          filteredStatuses.push(status);
        }
      });
      return filteredStatuses[filteredStatuses.length - 1].name;
    },
    dragStart(event) {
      event.dataTransfer.setData("text/plain", this.pothole.id);
      this.beingDragged = true;

    },
  },
};
</script>

<style>
p {
  margin: 0;
}

.show-modal-icon {
  justify-self: center;
}

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
  border-style: solid;
  border-width: 3px;
  border-color: #737373;
  background-color: white;
  color: black;
  border-radius: 5px;
  align-items: center;
  justify-content: left;
  min-height: 4em;
  padding-right: 0px;
}

/* div.pothole-card-no-map {
  grid-template-columns: 0.5fr 1fr 1fr 1.5fr 2fr 1fr 0.5fr 0.5fr;
div.pothole-card-no-map {
  grid-template-columns: 0.5fr 1fr 1fr 1fr 1fr 0.5fr 0.5fr 0.2fr;
}

div.pothole-card-show-map{
  grid-template-columns: 0.5fr 1fr 1fr 1fr 1fr 0.5fr 0.5fr;

} */

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

img.severity-icon-pothole-card {
  width: 60px;
  height: 60px;
  align-self: center;
}

.pothole-card.hidden{
  opacity:50%;
}

</style>


