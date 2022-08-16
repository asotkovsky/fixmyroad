<template>
  <div>
    <img class="modal_image" v-for="Url in pothole.imageUrl" :key="Url" v-bind:src="Url" /> 
    <timeline id="timeline" :statuses="pothole.statuses"/>
    <button v-show="checkIfUser" v-on:click="noticePothole()" > Notice pothole </button>

    <form v-if="checkIfAdmin" class="status-form" @submit.prevent="handleSave">
      <p>Update Status:</p>
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
    checkIfAdmin(){
    let roleIsAdmin = false;
    if (this.$store.state.user.authorities)
    this.$store.state.user.authorities.forEach(authority => {
      console.log(authority.name)
      if (authority.name == 'ROLE_ADMIN') {
        roleIsAdmin =  true;
      }
    });
    return roleIsAdmin;
    },
    checkIfUser(){
    if (this.$store.state.user.authorities){
      return this.$store.state.user.authorities.find( authority => {
      return authority.name == "ROLE_USER"  

      })
    }
    return false;
    }
  },
  methods: {
    handleSave() {
      PotholeService.createStatus(this.pothole.id, this.status).then(() =>
        location.reload()
      ).catch((error) => {
          alert(error.message);})
      this.status = { id: null, date: new Date().toISOString().slice(0, 10) };
    },
    noticePothole(){
      this.status.id= 6
      this.handleSave()
    }
    
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
.modal_image{
 max-width: 60vw;

}

</style>