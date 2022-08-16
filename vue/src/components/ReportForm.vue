<template>
  <div class="report-attributes">
    <div class="report">
      <img
          class="map-pin-icon"
          v-bind:src="require('../assets/blue-map-pin-icon.png')"
        />
      <div id="selected-location">Your Selected Location</div>
      <div id="road-name" >Road Name: {{ $store.state.currentPin.roadName }}</div>
      <div id="neighborhood" >Neighborhood: {{ $store.state.currentPin.neighborhood }}</div>
      <div id="city">City Name: {{ $store.state.currentPin.city }}</div>
      <div id="state">State: {{ $store.state.currentPin.state }}</div>
    </div>
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
        <option value="Road">Road</option>
        <option value="Shoulder">Shoulder</option>
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

      <label for="imageUpload">Select an Image:</label>
      <input @change="previewImage($event)" type="file" id="imageUpload" accept="image/*">
        <div v-if="previewUrl">
            <img class="post_image" :src="previewUrl" alt="">
        </div>


      <input id="submit"
        type="submit"
        value="Submit"
        :disabled="!submitEnabled"
      />
    </form>

  </div>
</template>
 
 <script>
import PotholeService from "@/services/PotholeService.js";
import { uploadBytes } from "firebase/storage";
import { initializeApp } from "firebase/app";
import { getStorage, ref, getDownloadURL} from "firebase/storage";
import {v4 as uuidv4 } from "uuid";

const firebaseConfig = {
  apiKey: process.env.VUE_APP_GOOGLE_API_KEY,
  authDomain: "potholetracker-358713.firebaseapp.com",
  projectId: "potholetracker-358713",
  storageBucket: "potholetracker-358713.appspot.com",
  messagingSenderId: "207952003661",
  appId: "1:207952003661:web:ad363995e7633473011204",
  measurementId: "G-K7Q2166FNK",
};
  const app = initializeApp(firebaseConfig);
  const storage = getStorage(app);
  

export default {
  data() {
    return {
      previewUrl: '',
      imageData: null,
      newPothole: {
      latitude: null,
        longitude: null,
        severity: "",
        description: "",
        locationOnRoadway: "",
        roadName : "", 
        neighborhood : "", 
        city: "", 
        state : "",
        imageUrl: []
      },
    };

  },
  computed: {
    submitEnabled() {
      return (('lat' in this.$store.state.currentPin) && ('lng' in this.$store.state.currentPin) && (this.newPothole.severity > 0))
    }
  },
  methods: {
    setSeverity(severitySelection) {
      this.newPothole.severity = severitySelection;
    },
    handleSave(){
      if(this.imageData){
        this.uploadImage().then((URL) =>{
          this.newPothole.imageUrl.push(URL)
          this.uploadPothole()
        })
      }else{
        this.uploadPothole()
      }

    },
    uploadPothole() {
      if (this.newPothole.description == "") {
        this.newPothole.description = "None provided.";
      }
      this.newPothole.latitude = this.$store.state.currentPin.lat;
      this.newPothole.longitude = this.$store.state.currentPin.lng;
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
        imageUrl: [],
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

     previewImage(event) {
            this.imageData = event.target.files[0];
            this.previewUrl = URL.createObjectURL(this.imageData)
        },

        uploadImage() {
            const storageRef = ref(storage, 'images/' + uuidv4());
            return uploadBytes(storageRef, this.imageData).then((snapshot) => {
            return getDownloadURL(snapshot.ref)
               

            });
        }
  },
};
</script>
 
 <style>
 .post_image{
height: 15vh;
 }

.report-attributes {
  display: grid;
  grid-template-columns: 1.7fr 2fr;
  padding-top: 10px;
}

img.selected {
  border-style: solid;
  border-color: #737373;
  border-width: 5px;
}

#severity-selection {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.report-form {
  display: flex;
  flex-direction: column;
  color: black;
}

.report {
  display: grid;
  gap: 5px;
  align-content: center;
  grid-template-areas: "map-pin-icon selected-location "
                       ". road-name"
                       ". neighborhood"
                       ". city"
                       ". state";
  grid-template-columns: .1fr .9fr;                    
  line-height: 26pt;
  justify-content: left;
  color: black;

}

textarea {
  margin-bottom: 10px;
  margin-top: 10px;
}

select{
  margin-bottom: 10px;
  margin-top: 10px;
}

#severity{
margin-top: 10px;
}

#road-name {
  grid-area: road-name;
}

#neighborhood {
  grid-area: neighborhood;
}

#city {
  grid-area: city;
}

#state {
  grid-area: state;
}

.map-pin-icon{
  grid-area: map-pin-icon;
  height: 35px;
  width: 35px;
}

#selected-location {
  grid-area: selected-location;
}

#submit {
  margin-bottom: 10px;
}
::-webkit-scrollbar {
    width: 14px;
}

::-webkit-scrollbar-thumb {
  background:#737373; 
  border-radius: 9px;
}
::-webkit-scrollbar-thumb:hover {
  background: #fad52f; 
}

</style>
