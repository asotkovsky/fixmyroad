<template>
  <div class="potholes-list">
    <h1>Reported Potholes: {{ potholes.length }}</h1>
    <div class="buttons">
    <button id="toggle-user-filter" v-show="this.$store.state.user.username" class="button" v-on:click ="flipShowMyPotholes()">{{toggleUserFilterText}}</button>
    <button id="show-filters" class="button" v-on:click="flipShowFilters()">{{toggleFilterText}}</button>
    <button id="show-map" class="button" v-on:click="flipShowMap()">{{toggleMapText}}</button>
    <button id="clear-filters" v-show="isFiltered" class="button" v-on:click="clearFilters()">Clear Filters</button>
    </div>
    <pothole-map :potholes="filteredPotholes" v-show="showMap"/>
    <div class="list-headers" >

      <span>
        <p>Severity</p>
        <select v-show="showFilters" name="filterSeverity" v-model.number="filter.severity">
          <option value=""></option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
      </span>
      <span>
        <p>Date Reported:</p>
        <select v-show="showFilters" name="filterDate" v-model.number="filter.numberOfDays">
          <option value=""></option>
          <option value="7">Last 7 Days</option>
          <option value="30">Last 30 Days</option>
          <option value="365">This Year</option>
        </select>
      </span>
      <span>
        
        <p>Road</p>
        <input v-show="showFilters" list="filteredRoadName" type="text" v-model="filter.roadName" />
        <datalist id="filteredRoadName">
          <option v-for="roadName in roadNames" v-bind:key="roadName">
            {{ roadName }}
          </option>
        </datalist>
      </span>
      <span>
        <p>Neighborhood</p>
        <input v-show="showFilters"
          list="filterNeighborhood"
          type="text"
          v-model="filter.neighborhood"
        />
        <datalist id="filterNeighborhood">
          <option
            v-for="neighborhood in neighborhoods"
            v-bind:key="neighborhood"
          >
            {{ neighborhood }}
          </option>
        </datalist>
      </span>
      <span>
        <p>Description</p>
        <input v-show="showFilters" type="text" v-model="filter.description" />
      </span>
      <span>
        <p>Status</p>
        <select v-show="showFilters" name="filterStatus" v-model="filter.status">
          <option value=""></option>
          <option value="Reported">reported</option>
          <option value="Scheduled For Inspection">
            scheduled for inspection
          </option>
          <option value="Inspected">inspected</option>
          <option value="Scheduled For Repair">scheduled for repair</option>
          <option value="Repaired">repaired</option>
        </select>
      </span>
      <span>
        <p>Road/Shoulder</p>
        <select v-show="showFilters"
          name="filterLocationOnRoadway"
          v-model="filter.locationOnRoadway"
        >
          <option value=""></option>
          <option value="Road">Road</option>
          <option value="Shoulder">Shoulder</option>
        </select>

      </span>

    </div>
    <div class="potholes-list">
      <pothole-card
        class="pothole.card"
        v-for="currentPothole in filteredPotholes"
        :key="currentPothole.id"
        :pothole="currentPothole"
      />
    </div>
   
  </div>
</template>

<script>
import PotholeService from "@/services/PotholeService.js";
import PotholeCard from "@/components/PotholeCard.vue";
import PotholeMap from '@/components/PotholeMap.vue';

export default {
  name: "pothole-list",
  components: {
    PotholeCard,
    PotholeMap,
  },
  data() {
    return {
      potholes: [],
      roadNames: [],
      availableStatus: [],
      neighborhoods: [],
      description: [],
      showFilters: false,
      toggleFilterText: "Show Filters",
      filterUserFavorite: false,
      toggleUserFilterText: "Show My Potholes",
      showMap: true,
      toggleMapText: "Hide Map",
      filter: {
        severity: "",
        roadName: "",
        neighborhood: "",
        description: "",
        status: "",
        locationOnRoadway: "",
        city: null,
        state: null,
        numberOfDays: ""
      },
    };
  },
  methods:{
    clearFilters(){
      this.filter = {
        severity: "",
        roadName: "",
        neighborhood: "",
        description: "",
        status: "",
        locationOnRoadway: "",
        city: null,
        state: null,
        numberOfDays: ""
      }
      this.$store.commit("SET_SELECTED_POTHOLE", {})
      this.filterUserFavorite = false;
      this.toggleUserFilterText = "Show My Potholes";
      console.log(this.$store.state.selectedPothole.id)
    },
    flipShowFilters(){
     this.showFilters = !this.showFilters
      if(this.showFilters){
        this.toggleFilterText = "Hide Filters"
      }
      else{
        this.toggleFilterText = "Show Filters"
      }
    },
    flipShowMyPotholes(){

      this.filterUserFavorite =!this.filterUserFavorite
      if(this.filterUserFavorite){
        this.toggleUserFilterText = "Show All Potholes"
      }
      else{
        this.toggleUserFilterText = "Show My Potholes"
      }
    },
    flipShowMap(){
      this.showMap = !this.showMap
      if(this.showMap){
        this.toggleMapText = "Hide Map"
      }
      else{
        this.toggleMapText = "Show Map"
      }
    }
  },

  mounted() {
    PotholeService.getPotholes().then((response) => {
      this.potholes = response.data;
      this.potholes.sort((a, b) => {
        return new Date(b.datetimeReported) - new Date(a.datetimeReported);
      });
      let roadNames = this.potholes.map((pothole) => pothole.roadName);
      this.roadNames = [...new Set(roadNames)];
      let neighborhoods = this.potholes.map((pothole) => pothole.neighborhood);
      this.neighborhoods = [...new Set(neighborhoods)];

    
    });
  },
  unmounted() {
    console.log("pothole list unmounted");
  },
  computed: {
    isFiltered(){
      if (this.filter.severity){
        return true;
      }
      if (this.filter.roadName){
        return true;
      }
      if (this.filter.neighborhood){
        return true;
      }
      if (this.filter.description){
        return true;
      }
      if (this.filter.status){
        return true;
      }
      if (this.filter.locationOnRoadway){
        return true;
      }
      if (this.filter.city){
        return true;
      }
      if (this.filter.state){
        return true;
      }
      if (this.filter.numberOfDays){
        return true;
      }
      if (this.$store.state.selectedPothole.id){
        return true;
      }
      if (this.filterUserFavorite){
        return true;
      }
      else {
        return false;
      }
    },
    startDate(){
    const startDate = new Date();
    startDate.setDate(startDate.getDate() - this.filter.numberOfDays)
    return startDate;
    },
    filteredPotholes() {
      return this.potholes
        .filter((pothole) => {
          if (this.filter.severity == "") {
            return true;
          } else {
            return pothole.severity == this.filter.severity;
          }
        })
        .filter((pothole) => {
          if (this.filter.roadName == "") {
            return true;
          } else {
            return pothole.roadName
              .toLowerCase()
              .includes(this.filter.roadName.toLowerCase());
          }
        })
        .filter((pothole) => {
          if (this.filter.neighborhood == "") {
            return true;
          } else {
            return pothole.neighborhood
              .toLowerCase()
              .includes(this.filter.neighborhood.toLowerCase());
          }
        })
        .filter((pothole) => {
          if (this.filter.locationOnRoadway == "") {
            return true;
          } else {
            return pothole.locationOnRoadway == this.filter.locationOnRoadway;
          }
        })
        .filter((pothole) => {
          if (this.filter.description == "") {
            return true;
          } else {
            return pothole.description
              .toLowerCase()
              .includes(this.filter.description.toLowerCase());
          }
        })
        .filter((pothole) => {
          if (this.filter.status == "") {
            return true;
          } else {
            return pothole.currentStatus.name == this.filter.status;
          }
        })
        .filter((pothole) => {
          if (this.filter.numberOfDays == "") {
            return true;
          } else {
            return new Date(pothole.statuses[0].date) >= this.startDate;
          }
        })
      .filter((pothole)=> {
        if(this.filterUserFavorite && this.$store.state.user.username)
        {
          let statusFound = pothole.statuses.find (status => status.email == this.$store.state.user.username)
          return statusFound;
        }else{
          return true;
          
        }
      })
      .filter((pothole) => {
        if (this.$store.state.selectedPothole.id){
          return pothole.id == this.$store.state.selectedPothole.id
        }
        else {
          return true;
        }
      });

    },
  },
};
</script>

<style>
body {
  background-color: #0079bf;
  color: white;
}
div.potholes-list {
  display: flex;
  flex-direction: column;
  align-content: center;
  gap: 20px;
  margin-bottom: 10px;
}

div.map {
  display: flex;
  justify-content: center;
}

.pothole-card {
  background-color: white;
  color: black;
}

div.list-headers {
  display: grid;
  justify-content: left;
  gap: 15px;
  grid-template-columns: .7fr .7fr 1.5fr 1fr 2fr .7fr 0.2fr 0.2fr;
}

.button {
  border-style: solid;
  border-color: #737373;
  border-radius: 5px;
  background-color: #737373;
  color: white;
  padding: 10px;
  font-family: sans-serif;
  font-weight: 750;
  margin-bottom: 10px;
}

.button:hover {
  background-color: #fad52f;
  color: #737373;
  border-color: #fad52f;
}

#toggle-user-filter {
  margin-right: 10px;
}

#show-filters{
  margin-right: 10px;
}

#show-map {
  margin-right: 10px;
}


/* Track */
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px grey; 
  border-radius: 10px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
  background:#737373; 
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background: #fad52f; 
}

</style>