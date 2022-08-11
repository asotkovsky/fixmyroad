<template>
  <div class="report-attributes">
    <div>Road name: {{ $store.state.currentPin.roadName }}</div>
    <div>Neighborhood: {{ $store.state.currentPin.neighborhood }}</div>
    <div>City name:{{ $store.state.currentPin.city }}</div>
    <div>State: {{ $store.state.currentPin.state }}</div>

    <form class="report-form" @submit.prevent="handleSave">
      <label id="severity" for="severity">Severity </label>
      <div id="severity-selection">
        <img
          class="severity-icon"
          @click="setSeverity(1)"
          :class="{ selected: newPothole.severity == 1 }"
          v-bind:src="require('../assets/severity-icon-1.png')"
        />
        <img
          class="severity-icon"
          @click="setSeverity(2)"
          :class="{ selected: newPothole.severity == 2 }"
          v-bind:src="require('../assets/severity-icon-2.png')"
        />
        <img
          class="severity-icon"
          @click="setSeverity(3)"
          :class="{ selected: newPothole.severity == 3 }"
          v-bind:src="require('../assets/severity-icon-3.png')"
        />
        <img
          class="severity-icon"
          @click="setSeverity(4)"
          :class="{ selected: newPothole.severity == 4 }"
          v-bind:src="require('../assets/severity-icon-4.png')"
        />
        <img
          class="severity-icon"
          @click="setSeverity(5)"
          :class="{ selected: newPothole.severity == 5 }"
          v-bind:src="require('../assets/severity-icon-5.png')"
        />
      </div>
      <label id="location_on_roadway" for="location_on_roadway"
        >Location On Roadway</label
      >
      <select id="location_on_roadway" v-model="newPothole.locationOnRoadway">
        <option value="road">Road</option>
        <option value="shoulder">Shoulder</option>
      </select>

      <label for="description">Description </label>
      <textarea
        id="description"
        maxlength="200"
        v-model="newPothole.description"
        rows="4"
        cols="50"
      >
Please describe the pothole</textarea
      >

      <input
        type="submit"
        value="Submit"
        :disabled="$store.state.currentPin == null || newPothole.severity == 0"
      />
    </form>
  </div>
</template>
 
 <script>
import PotholeService from "@/services/PotholeService.js";

export default {
  data() {
    return {
      newPothole: {
        latitude: null,
        longitude: null,
        severity: "",
        description: "",
        locationOnRoadway: "",
        roadName : "", 
        neighborhood : "", 
        city: "", 
        state : ""
      },
    };
  },
  methods: {
    setSeverity(severitySelection) {
      this.newPothole.severity = severitySelection;
    },
    handleSave() {
      if (this.newPothole.description == "") {
        this.newPothole.description = "None provided.";
      }
      this.newPothole.city = this.$store.state.currentPin.city;
      this.newPothole.state = this.$store.state.currentPin.state;
      this.newPothole.neighborhood = this.$store.state.currentPin.neighborhood;
      this.newPothole.roadName = this.$store.state.currentPin.roadName;

      PotholeService.createPothole(this.newPothole)
        .then(() => this.$router.push({ name: "potholes-list" }))
        .catch((error) => {
          alert(error.message);
        });

      // Clear the form for the next addition (and prevents odd bugs in adding data multiple times)
      const newPothole = {
        latitude: "",
        longitude: "",
        severity: "",
        description: "",
        locationOnRoadway: "",
        roadName: this.$store.state.currentPin.roadName,
        neighborhood: this.$store.state.currentPin.neighborhood,
        city: this.$store.state.currentPin.city,
        state: this.$store.state.currentPin.roadName,
      };
      this.newPothole = newPothole;

      this.$store.commit("SET_CURRENT_PIN", {});
    },
  },
};
</script>
 
 <style>
img.selected {
  border-style: solid;
  border-color: #737373;
  border-width: 5px;
}

#severity-selection {
  display: inline-block;
}

.report-form {
  display: flex;
  flex-direction: column;
}
</style>
