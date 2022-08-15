<template>
  <div>
    <h1>Reported Potholes: {{ potholes.length }}</h1>
    <div class="list-headers">
      <span>
        <p>Severity</p>
        <select name="filterSeverity" v-model.number="filter.severity">
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
        <select name="filterDate" v-model.number="filter.numberOfDays">
          <option value=""></option>
          <option value="7">Last 7 Days</option>
          <option value="30">Last 30 Days</option>
          <option value="365">This Year</option>
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
        <select name="filterStatus" v-model="filter.status">
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
        numberOfDays: ""
      },
    };
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
  grid-template-columns: .7fr .7fr 1.5fr 1fr 2fr .7fr 0.2fr 0.2fr;
}


<<<<<<< HEAD
h1 {
  font-family: sans-serif;
}
::-webkit-scrollbar {
  width: 20px;
}
=======
>>>>>>> 07d21c3428083cc00282161a26aa5618ffa51d83

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