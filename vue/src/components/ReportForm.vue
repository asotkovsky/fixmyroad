 <template>
  <div class='report-attributes'>
      <div>Road name: {{ roadName }}</div>
      <div> Neighborhood: {{ neighborhood }}</div>
      <div>City name:{{ city }}</div>
      <div>State: {{ state }}</div>

  

    <form class="report-form" @submit.prevent="handleSave">
     <label id="severity" for="severity">Severity </label>
    <div id="severity-selection">
      <img class='severity-icon' @click="setSeverity(1)" v-bind:src="require('../assets/severity-icon-1.png')">
      <img class='severity-icon' @click="setSeverity(2)" v-bind:src="require('../assets/severity-icon-2.png')">
      <img class='severity-icon' @click="setSeverity(3)" v-bind:src="require('../assets/severity-icon-3.png')">
      <img class='severity-icon' @click="setSeverity(4)" v-bind:src="require('../assets/severity-icon-4.png')">
      <img class='severity-icon' @click="setSeverity(5)" v-bind:src="require('../assets/severity-icon-5.png')">
    </div>
  <label id="location_on_roadway" for="location_on_roadway">Location On Roadway</label>
  <select id="location_on_roadway" v-model="newPothole.locationOnRoadway">

    <option value="road">Road</option>
    <option value="shoulder">Shoulder</option>

    </select>

    <label for="description">Description </label>
  <textarea id="description" v-model="newPothole.description" rows="4" cols="50">Please describe the pothole</textarea>

    <input type="submit" value="Submit">
   </form>
  </div>
</template>
 
 <script>
import PotholeService from "@/services/PotholeService.js";
export default {
  data() {
    return { 
      roadName : "", 
      neighborhood : "", 
      city: "", 
      state : "",
      newPothole: {
        latitude: "",
        longitude: "",
        severity: "",
        description: "",
        locationOnRoadway: "",
        roadName : "", 
        neighborhood : "", 
        city: "", 
        state : ""
        // Need to add timestamp?
      },
    };
  },
  methods: {
    setSeverity(severitySelection){
      console.log("This button was clicked");
      this.newPothole.severity = severitySelection;
    },
    handleSave(event) {
      console.log('Save was clicked!', event);

      // Clear the form for the next addition (and prevents odd bugs in adding data multiple times)
      this.newPothole = {
        newPothole: {
        latitude: "",
        longitude: "",
        severity: "",
        description: "",
        locationOnRoadway: "",
        roadName : "", 
        neighborhood : "", 
        city: "", 
        state : ""
      },
      }
  }
  },
  watch: {
    "$store.state.currentPin": function () {
      PotholeService.getLocation(
        this.$store.state.currentPin.lat,
        this.$store.state.currentPin.lng
      ).then((response) => {
        let addressParts = response.data.results[0].address_components;
        console.log(addressParts);
        this.city = addressParts.find((part) =>
          part.types.includes("locality")
        ).short_name;
        this.roadName = addressParts.find((part) =>
          part.types.includes("route")
        ).short_name;
        let neighborhoodPart = addressParts.find((part) =>
          part.types.includes("neighborhood")
        );
        if (neighborhoodPart) {
          this.neighborhood = neighborhoodPart.short_name;
        } else this.neighborhood = "";

        this.state = addressParts.find((part) =>
          part.types.includes("administrative_area_level_1")
        ).short_name;
      });
    },
  },
}
</script>
 
 <style>

 

 

  #severity-selection {
  display: inline-block;
 
  
  }


 .report-form {
   display: flex;
   flex-direction: column; }

  
  
</style>