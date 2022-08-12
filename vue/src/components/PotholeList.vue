<template>
  <div>
    <h1>Reported Potholes: {{ potholes.length }}</h1>
    <div class="list-headers">
      <span>
        <p>Severity</p>
        <select name="filterSeverity" v-model="filter.severity">
          <option value=""></option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
      </span>
      <span>
        <p>Date:</p>
        <select name="filterDate" v-model="filter.severity">
          <option value=""></option>
          <option :value="getDate()">Last 7 Days</option>
          <option value = "last-30-days">Last 30 Days</option>
          <option value = "last-year">This Year</option>
        </select>
      </span>
      <span>
        
        <p>Road</p>
        <input list="filteredRoadName" type="text" v-model="filter.roadName" />
        <datalist id="filteredRoadName">
          <option v-for="roadName in roadNames" v-bind:key="roadName">
            {{ roadName }}
          </option>
        </datalist>
      </span>
      <span>
        <p>Neighborhood</p>
        <input
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
        <input type="text" v-model="filter.description" />
      </span>
      <span>
        <p>Status</p>
        <select name="filterStatus" v-model="filter.date">
         <option value=""></option>
         <option value = "Reported">Reported</option>
         <option value = "Scheduled For Inspection">Scheduled For Inspection</option>
         <option value = "Inspected">Inspected</option>
         <option value = "Scheduled For Repair">Scheduled For Repair</option>
         <option value = "Repaired">Repaired</option>
        </select>
      </span>
      <span>
        <p>Road/Shoulder</p>
        <select
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

export default {
  name: "pothole-list",
  components: {
    PotholeCard,
  },
  data() {
    return {
      potholes: [],
      roadNames: [],
      availableStatus: [],
      neighborhoods: [],
      description: [],
      filter: {
        severity: "",
        roadName: "",
        neighborhood: "",
        description: "",
        status: "",
        locationOnRoadway: "",
        city: null,
        state: null,
        date: ""
      },
    };
  },
  methods: {
    getDate(dateRange) {
      var date = new Date();
      date.setDate(date.getDate() - dateRange);
      return date;
    },
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

      this.potholes.forEach((pothole) => {
        PotholeService.getPotholeStatuses(pothole.id).then((response) => {
          pothole.statuses = response.data;
          pothole.currentStatus =
            pothole.statuses[pothole.statuses.length - 1].name;
        });
      });
    });
  },
  unmounted() {
    console.log("pothole list unmounted");
  },
  computed: {
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
          if(this.filter.status == ""){
            return true;
          }
          else{
            return pothole.currentStatus == this.filter.status
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
  gap: 20px;
}

.pothole-card {
  background-color: white;
  color: black;
}

div.list-headers {
  display: grid;
  justify-content: left;
  gap: 15px;
  margin-left: 5px;
  grid-template-columns: 0.7fr .7fr 1.5fr 1.5fr 2fr 1fr 0.7fr 0.1fr;
}

p {
  font-family: sans-serif;
}

h1 {
  font-family: sans-serif;
}


</style>