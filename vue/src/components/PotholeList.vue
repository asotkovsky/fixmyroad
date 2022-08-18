<template>
  <div>
    <div
      :class="showMap ? 'potholes-list-map-view' : 'pothole-list-hide-map-view'"
    >
      <div class="buttons">
        <button
          id="toggle-user-filter"
          v-show="this.$store.state.user.username"
          class="button"
          v-on:click="flipShowMyPotholes()"
        >
          {{ toggleUserFilterText }}
        </button>
        <button id="show-filters" class="button" v-on:click="flipShowFilters()">
          {{ toggleFilterText }}
        </button>
        <button id="show-map" class="button" v-on:click="flipShowMap()">
          {{ toggleMapText }}
        </button>
        <button
          id="clear-filters"
          v-show="isFiltered"
          class="button"
          v-on:click="clearFilters()"
        >
          Clear Filters
        </button>
      </div>

      <div
        class="card-container list-headers"
        :class="{ 'card-container-show-map': showMap }"
      >
        <div
          :class="
            showMap
              ? 'pothole-card-alignment-show-map'
              : 'pothole-card-alignment-hide-map'
          "
        >
          <span id="severity-filter">
            Severity
            <select
              v-on:change="
                (e) => {
                  $store.commit('SET_FILTER_FIELD', {
                    fieldName: 'severity',
                    value: e.target.value,
                  });
                }
              "
              v-show="showFilters"
              name="filterSeverity"
              :value="$store.state.filter.severity"
            >
              <option value=""></option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
          </span>
          <span id="date-filter">
            Reported
            <select
              v-on:change="
                (e) => {
                  $store.commit('SET_FILTER_FIELD', {
                    fieldName: 'numberOfDays',
                    value: e.target.value,
                  });
                }
              "
              v-show="showFilters"
              name="filterDate"
              :value="$store.state.filter.numberOfDays"
            >
              <option value=""></option>
              <option value="7">Last 7 Days</option>
              <option value="30">Last 30 Days</option>
              <option value="365">This Year</option>
            </select>
          </span>
          <span id="road-filter">
            Road
            <input
              v-on:change="
                (e) => {
                  $store.commit('SET_FILTER_FIELD', {
                    fieldName: 'roadName',
                    value: e.target.value,
                  });
                }
              "
              v-show="showFilters"
              list="filteredRoadName"
              type="text"
              :value="$store.state.filter.roadName"
            />
            <datalist id="filteredRoadName">
              <option v-for="roadName in roadNames" v-bind:key="roadName">
                {{ roadName }}
              </option>
            </datalist>
          </span>
          <span id="neighborhood-filter">
            Neighborhood
            <input
              v-on:change="
                (e) => {
                  $store.commit('SET_FILTER_FIELD', {
                    fieldName: 'neighborhood',
                    value: e.target.value,
                  });
                }
              "
              v-show="showFilters"
              list="filterNeighborhood"
              type="text"
              :value="$store.state.filter.neighborhood"
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
          <span v-show="!showMap" id="description-filter">
            Description
            <input
              v-on:input="
                (e) => {
                  $store.commit('SET_FILTER_FIELD', {
                    fieldName: 'description',
                    value: filter.description,
                  });
                }
              "
              v-show="showFilters"
              type="text"
              :value="$store.state.filter.description"
            />
          </span>
          <span id="status-filter">
            Status
            <select
              v-on:change="
                (e) => {
                  $store.commit('SET_FILTER_FIELD', {
                    fieldName: 'status',
                    value: e.target.value,
                  });
                }
              "
              v-show="showFilters"
              name="filterStatus"
              :value="$store.state.filter.status"
            >
              <option value=""></option>
              <option value="Reported">Reported</option>
              <option value="Assigned">Assigned</option>
              <option value="Scheduled For Inspection">
                Scheduled For Inspection
              </option>
              <option value="Inspected">Inspected</option>
              <option value="Scheduled For Repair">Scheduled For Repair</option>
              <option value="Repaired">Repaired</option>
            </select>
          </span>
          <span
            id="location-filter"
            v-show="!$store.getters.currentUserIsAdmin"
          >
            Location
            <select
              v-on:change="
                (e) => {
                  $store.commit('SET_FILTER_FIELD', {
                    fieldName: 'locationOnRoadway',
                    value: e.target.value,
                  });
                }
              "
              v-show="showFilters"
              name="filterLocationOnRoadway"
              :value="$store.state.filter.locationOnRoadway"
            >
              <option value=""></option>
              <option value="Road">Road</option>
              <option value="Shoulder">Shoulder</option>
            </select>
          </span>
          <span v-show="$store.getters.currentUserIsAdmin">
            Assigned
            <select
              v-on:change="
                (e) => {
                  $store.commit('SET_FILTER_FIELD', {
                    fieldName: 'assigned',
                    value: e.target.value,
                  });
                }
              "
              v-show="showFilters"
              :value="$store.state.filter.assigned"
            >
              <option value="0"></option>
              <option value="1">Assigned</option>
              <option value="2">Unassigned</option>
            </select>
          </span>
        </div>
      </div>

      <pothole-map class="map" :potholes="filteredPotholes" v-show="showMap" />
      <div
        class="card-container list-body"
        :class="{ 'card-container-show-map': showMap }"
      >
        <pothole-card
          :showMap="showMap"
          :class="
            showMap
              ? 'pothole-card-alignment-show-map'
              : 'pothole-card-alignment-hide-map'
          "
          v-for="currentPothole in filteredPotholes"
          :key="currentPothole.id"
          :pothole="currentPothole"
        />
      </div>
      <employeeList
        id="employee-list"
        v-if="$store.getters.currentUserIsAdmin"
      />
    </div>
  </div>
</template>

<script>
import PotholeCard from "@/components/PotholeCard.vue";
import PotholeMap from "@/components/PotholeMap.vue";
import employeeList from "@/components/EmployeeList.vue";

export default {
  name: "pothole-list",
  components: {
    PotholeCard,
    PotholeMap,
    employeeList,
  },
  data() {
    return {
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
    };
  },
  methods: {
    clearFilters() {
      this.$store.commit("SET_SELECTED_POTHOLE", {});
      this.$store.commit("CLEAR_FILTERS");
      this.filterUserFavorite = false;
      this.toggleUserFilterText = "Show My Potholes";
    },
    flipShowFilters() {
      this.showFilters = !this.showFilters;
      if (this.showFilters) {
        this.toggleFilterText = "Hide Filters";
      } else {
        this.toggleFilterText = "Show Filters";
      }
    },
    flipShowMyPotholes() {
      this.filterUserFavorite = !this.filterUserFavorite;
      if (this.filterUserFavorite) {
        this.toggleUserFilterText = "Show All Potholes";
      } else {
        this.toggleUserFilterText = "Show My Potholes";
      }
    },
    flipShowMap() {
      this.showMap = !this.showMap;
      if (this.showMap) {
        this.toggleMapText = "Hide Map";
      } else {
        this.toggleMapText = "Show Map";
      }
    },
    isEmployeePothole(employeeEmail, pothole) {
      let repairedStatus = pothole.statuses.find(
        (status) => status.name == "Repaired"
      );
      if (repairedStatus) {
        return false;
      }
      return pothole.statuses.find((status) => {
        return status.name == "Assigned" && status.email == employeeEmail;
      });
    },
  },

  mounted() {
    this.$store.dispatch("reloadPotholes");
    let roadNames = this.$store.state.potholes.map(
      (pothole) => pothole.roadName
    );
    this.roadNames = [...new Set(roadNames)];
    let neighborhoods = this.$store.state.potholes.map(
      (pothole) => pothole.neighborhood
    );
    this.neighborhoods = [...new Set(neighborhoods)];
  },
  unmounted() {
    console.log("pothole list unmounted");
  },
  computed: {
    isFiltered() {
      if (this.$store.state.filter.severity) {
        return true;
      }
      if (this.$store.state.filter.roadName) {
        return true;
      }
      if (this.$store.state.filter.neighborhood) {
        return true;
      }
      if (this.$store.state.filter.description) {
        return true;
      }
      if (this.$store.state.filter.status) {
        return true;
      }
      if (this.$store.state.filter.locationOnRoadway) {
        return true;
      }
      if (this.$store.state.filter.city) {
        return true;
      }
      if (this.$store.state.filter.state) {
        return true;
      }
      if (this.$store.state.filter.numberOfDays) {
        return true;
      }
      if (this.$store.state.selectedPothole.id) {
        return true;
      }
      if (this.$store.state.filter.employeeName) {
        return true;
      }
      if (this.$store.state.filter.assigned > 0) {
        return true;
      }
      if (this.filterUserFavorite) {
        return true;
      } else {
        return false;
      }
    },
    startDate() {
      const startDate = new Date();
      startDate.setDate(
        startDate.getDate() - this.$store.state.filter.numberOfDays
      );
      return startDate;
    },
    filteredPotholes() {
      return this.$store.state.potholes
        .filter((pothole) => {
          if (this.$store.state.filter.severity == "") {
            return true;
          } else {
            return pothole.severity == this.$store.state.filter.severity;
          }
        })
        .filter((pothole) => {
          if (this.$store.state.filter.roadName == "") {
            return true;
          } else {
            return pothole.roadName
              .toLowerCase()
              .includes(this.$store.state.filter.roadName.toLowerCase());
          }
        })
        .filter((pothole) => {
          if (this.$store.state.filter.neighborhood == "") {
            return true;
          } else {
            return pothole.neighborhood
              .toLowerCase()
              .includes(this.$store.state.filter.neighborhood.toLowerCase());
          }
        })
        .filter((pothole) => {
          if (this.$store.state.filter.locationOnRoadway == "") {
            return true;
          } else {
            return (
              pothole.locationOnRoadway ==
              this.$store.state.filter.locationOnRoadway
            );
          }
        })
        .filter((pothole) => {
          if (this.$store.state.filter.description == "") {
            return true;
          } else {
            return pothole.description
              .toLowerCase()
              .includes(this.$store.state.filter.description.toLowerCase());
          }
        })
        .filter((pothole) => {
          if (this.$store.state.filter.status == "") {
            return true;
          } else {
            return (
              pothole.currentStatus.name == this.$store.state.filter.status
            );
          }
        })
        .filter((pothole) => {
          if (this.$store.state.filter.numberOfDays == "") {
            return true;
          } else {
            return new Date(pothole.statuses[0].date) >= this.startDate;
          }
        })
        .filter((pothole) => {
          if (this.filterUserFavorite && this.$store.state.user.username) {
            let statusFound = pothole.statuses.find(
              (status) => status.email == this.$store.state.user.username
            );
            return statusFound;
          } else {
            return true;
          }
        })
        .filter((pothole) => {
          if (this.$store.state.selectedPothole.id) {
            return pothole.id == this.$store.state.selectedPothole.id;
          } else {
            return true;
          }
        })
        .filter((pothole) => {
          if (this.$store.state.filter.employeeName == "") {
            return true;
          } else {
            return this.isEmployeePothole(
              this.$store.state.filter.employeeName,
              pothole
            );
          }
        })
        .filter((pothole) => {
          if (this.$store.state.filter.assigned == 0) {
            return true;
          } else if (this.$store.state.filter.assigned == 1) {
            return pothole.statuses.find((status) => status.name == "Assigned");
          } else {
            let assignedStatuses = pothole.statuses.filter(
              (status) => status.name == "Assigned"
            );
            return assignedStatuses.length == 0;
          }
        });
    },
  },
};
</script>

<style>
/*This is the styling/grid layout for when the map is showing*/
div.potholes-list-map-view {
  display: grid;
  grid-template-areas:
    "buttons headers"
    "map pothole-cards"
    "employeelist employeelist";
  height: 69vh;
  flex-direction: column;
  align-content: center;
  margin-top: 30px;
  padding-top: 25px;
}
#employee-list {
  grid-area: employeelist;
  display: flex;
  gap: 10px;
  padding-top: 10px;
}

div.map {
  grid-area: map;
  width: 47vw;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.card-container {
  margin-left: 5px;
  margin-right: 10px;
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.list-headers {
  grid-area: headers;
  justify-content: end;
}

.list-body {
  grid-area: pothole-cards;
  height: 69vh;
  overflow-y: auto;
}

div.pothole-card-alignment-show-map {
  display: grid;
  justify-content: left;
  gap: 15px;
  margin-right: 10px;
  grid-template-columns: 0.5fr 1fr 1fr 1fr 1fr 0.5fr 0.5fr;
  width: 47vw;
  grid-area: headers;
  align-items: center;
  margin-left: 10px;
}

/*This is the styling/grid layout for when the map is hidden*/
div.pothole-list-hide-map-view {
  display: flex;
  flex-direction: column;
  align-content: center;
  margin-bottom: 10px;
}

div.pothole-card-alignment-hide-map {
  display: grid;
  justify-content: left;
  gap: 15px;
  grid-template-columns: 0.5fr 1fr 1fr 1.5fr 2fr 1fr 0.5fr 0.5fr;
  align-items: center;
}

select {
  width: 100%;
  height: 25px;
}

input {
  width: 100%;
  height: 19px;
}

.buttons {
  display: flex;
  grid-area: buttons;
  max-width: 50vw;
  align-items: end;
  height: 70px;
  padding-bottom: 5px;
}

.button {
  border-style: solid;
  border-color: #737373;
  border-radius: 5px;
  background-color: #737373;
  color: white;
  font-family: sans-serif;
  font-weight: 750;
}

.button:hover {
  background-color: #fad52f;
  color: #737373;
  border-color: #fad52f;
}

#location-filter {
  margin-right: 10px;
}

#toggle-user-filter {
  margin-right: 10px;
}

#show-filters {
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
  background: #737373;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background: #fad52f;
}
</style>