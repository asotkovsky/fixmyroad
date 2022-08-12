<template>
  <div>
    <form class="status-form" @submit.prevent="handleSave">
      <select name="status" id="status-select" v-model="potholeStatus">
        <option value="2">
          Scheduled For Inspection
        </option>
        <option value="3">Inspected</option>
        <option value="4">Scheduled For Repair</option>
        <option value="5">Repaired</option>
      </select>
      <br/>
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
      PotholeService.createStatus(this.pothole.id, this.potholeStatus)
      .then(()=>location.reload());
      this.potholeStatus = "";
    },
  },
};
</script>

<style>
</style>