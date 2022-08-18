<template>
  <div
    class="employee_card"
    v-on:dragover.prevent
    v-on:drop.prevent="handleDrop"
    v-on:click="handleClick"
  >
  <span>
    {{ employee.fullName }}
  </span>
  <span>
    Assigned Potholes: {{ assignedPotholes.length }}
  </span>
  </div>
</template>

<script>
import PotholeService from "@/services/PotholeService.js";
export default {
  data() {
    return {
      employeesList: [],
    };
  },
  props: {
    employee: Object,
  },

  methods: {
    handleDrop(event) {
      console.log(event.dataTransfer.getData("text/plain"));
      let potholeId = event.dataTransfer.getData("text/plain");
      let status = {
        id: 2,
        date: new Date().toISOString().slice(0, 10),
        email: this.employee.name,
      };
      PotholeService.createStatus(potholeId, status).then(() => {
        this.$store.dispatch("reloadPotholes");
      });
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
    handleClick() {
      console.log("handleClickCalled");
      this.$store.commit("SET_FILTER_FIELD", {
        fieldName: "employeeName",
        value: this.employee.name,
      });
    },
  },
  computed: {
    assignedPotholes() {
      return this.$store.state.potholes.filter((pothole) => {
        return this.isEmployeePothole(this.employee.name, pothole);
      });
    },
  },
};
</script>

<style>
.employee_card {
  background-color: #737373;
  padding: 12px;
  border-radius: 5px;
  display:flex;
  flex-direction: column;
  align-items: flex-start;
}
.employee_card:hover {
  filter: brightness(65%);
}
</style>