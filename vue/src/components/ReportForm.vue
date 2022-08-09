 <template>
  <div>
      <div>Road name: {{ roadName }}</div>
      <div> Neighborhood: {{ neighborhood }}</div>
      <div>City name:{{ city }}</div>
      <div>State: {{ state }}</div>
    <form>
     <label id="severity" for="severity">Severity: </label>
     <select id="severity">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
     </select>
     
  <label id="location_on_roadway" for="location_on_roadway">Location On Roadway:</label>
  <select id="location_on_roadway">
    <option value="road">Road</option>
    <option value="shoulder">Shoulder</option>

    </select>
    <label for="description">Description: </label>
  <textarea id="description"  rows="4" cols="50">Please describe the pothole</textarea>
    <input type="submit" value="Submit">
   </form>
  </div>
</template>
 
 <script>
import PotholeService from "@/services/PotholeService.js";
export default {
  data() {
    return { roadName : "", neighborhood : "", city: "", state : "" };
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
};
</script>
 
 <style>

 

 


</style>