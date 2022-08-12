<template>
  <div>
    <form class="status-form" @submit.prevent="handleSave">
      <select name="status" id="status-select" v-model.number="potholeStatus">
        <option value="2">
          Scheduled For Inspection
        </option>
        <option value="3">Inspected</option>
        <option value="4">Scheduled For Repair</option>
        <option value="5">Repaired</option>
      </select>
      <input type="date" v-if="[2,4].includes(potholeStatus)" v-model="selectedDate"/>
      <input type="submit" value="Submit" :disabled="!submitEnabled" />
    </form>
  </div>
</template>

<script>
import PotholeService from "@/services/PotholeService.js";

export default {
  name: "status-form",
  data() {
    return {
      potholeStatus: "",
      selectedDate : null,
    };
  },
  props: ["pothole"],
  computed: {
    submitEnabled() {
      return this.potholeStatus != "";
    },
  },
  methods: {
    handleSave() {
      PotholeService.createStatus(this.pothole.id, this.potholeStatus).then(
        ()=>location.reload()
      );
      this.potholeStatus = "";
    },
  },
};
</script>

<style>
</style>