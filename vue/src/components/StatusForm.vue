<template>
  <div>
<timeline :statuses="pothole.statuses"/>
    <form class="status-form" @submit.prevent="handleSave">
      <select name="status" id="status-select" v-model.number="status.id">
        <option value="2">Scheduled For Inspection</option>
        <option value="3">Inspected</option>
        <option value="4">Scheduled For Repair</option>
        <option value="5">Repaired</option>
      </select>
      <input
        type="date"
        v-bind:disabled="![2, 4].includes(status.id)"
        v-model="status.date"
      />
      <input type="submit" value="Submit" :disabled="!submitEnabled" />
    </form>
  </div>
</template>

<script>
import PotholeService from "@/services/PotholeService.js";
import Timeline from "@/components/Timeline.vue";

export default {
  name: "status-form",
  data() {
    return {
      status: { id: null, date: new Date().toISOString().slice(0, 10) },
    };
  },
  components:{
    Timeline

  },
  props: ["pothole"],
  computed: {
    submitEnabled() {
      return this.potholeStatus != "";
    },
  },
  methods: {
    handleSave() {
      PotholeService.createStatus(this.pothole.id, this.status).then(() =>
        location.reload()
      );
      this.status = { id: null, date: new Date().toISOString().slice(0, 10) };
    },
  },
};
</script>

<style>
form.status-form {
  display: flex;
  flex-direction: column;
  gap: 1em;
  min-width: 50vw;
  min-height: 40vh;
}
</style>