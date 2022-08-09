 <template>
  <div class='report-attributes'>
      <div>Road name: {{ newPothole.roadName }}</div>
      <div> Neighborhood: {{ newPothole.neighborhood }}</div>
      <div>City name:{{ newPothole.city }}</div>
      <div>State: {{ newPothole.state }}</div>

  

    <form class="report-form" @submit.prevent="handleSave">
     <label id="severity" for="severity">Severity </label>
    <div id="severity-selection">
      <img class='severity-icon' @click="setSeverity(1)" :class="{selected: newPothole.severity == 1}" v-bind:src="require('../assets/severity-icon-1.png')">
      <img class='severity-icon' @click="setSeverity(2)" :class="{selected: newPothole.severity == 2}" v-bind:src="require('../assets/severity-icon-2.png')">
      <img class='severity-icon' @click="setSeverity(3)" :class="{selected: newPothole.severity == 3}" v-bind:src="require('../assets/severity-icon-3.png')">
      <img class='severity-icon' @click="setSeverity(4)" :class="{selected: newPothole.severity == 4}" v-bind:src="require('../assets/severity-icon-4.png')">
      <img class='severity-icon' @click="setSeverity(5)" :class="{selected: newPothole.severity == 5}" v-bind:src="require('../assets/severity-icon-5.png')">
    </div>
  <label id="location_on_roadway" for="location_on_roadway">Location On Roadway</label>
  <select id="location_on_roadway" v-model="newPothole.locationOnRoadway">

    <option value="road">Road</option>
    <option value="shoulder">Shoulder</option>

    </select>

    <label for="description">Description </label>
  <textarea id="description" maxlength = "200" v-model="newPothole.description" rows="4" cols="50">Please describe the pothole</textarea>

    <input type="submit" value="Submit" :disabled="$store.state.currentPin == null">
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
    setSeverity(severitySelection){
      this.newPothole.severity = severitySelection;
    },
    handleSave() {

      PotholeService.createPothole(this.newPothole).catch(error => {
        alert(error.message)
        })

      // Clear the form for the next addition (and prevents odd bugs in adding data multiple times)
      const newPothole = {
        latitude: "",
        longitude: "",
        severity: "",
        description: "",
        locationOnRoadway: "",
        roadName : this.newPothole.roadName, 
        neighborhood : this.newPothole.neighborhood, 
        city: this.newPothole.city, 
        state: this.newPothole.roadName,
      };
    this.newPothole = newPothole;
    this.$router.push("/potholes")
  }
  },
  watch: {
    "$store.state.currentPin": function () {
      PotholeService.getLocation(
        this.$store.state.currentPin.lat,
        this.$store.state.currentPin.lng
      ).then((response) => {
        this.newPothole.latitude = this.$store.state.currentPin.lat;
        this.newPothole.longitude = this.$store.state.currentPin.lng;

        let addressParts = response.data.results[0].address_components;
        console.log(addressParts);
        this.newPothole.city = addressParts.find((part) =>
          part.types.includes("locality")
        ).short_name;
        this.newPothole.roadName = addressParts.find((part) =>
          part.types.includes("route")
        ).short_name;
        let neighborhoodPart = addressParts.find((part) =>
          part.types.includes("neighborhood")
        );
        if (neighborhoodPart) {
          this.newPothole.neighborhood = neighborhoodPart.short_name;
        } else this.neighborhood = "";

        this.newPothole.state = addressParts.find((part) =>
          part.types.includes("administrative_area_level_1")
        ).short_name;
      });
    },
  },
}
</script>
 
 <style>

 

  img.selected {
    border-style: solid;
    border-color: #737373;
    border-width: 5px;
  }

  #severity-selection {
  display: inline-block
  }

 .report-form {
   display: flex;
   flex-direction: column; }

</style>