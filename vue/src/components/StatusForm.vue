<template>
  <div>
    <div class="modal_image_holder"><img class="modal_image" v-for="Url in pothole.imageUrl" :key="Url" v-bind:src="Url" /> </div>
    <timeline id="timeline" :statuses="pothole.statuses"/>
    <button v-show="checkIfUser" v-on:click="noticePothole()" > Notice pothole </button>
<div class="status_holder">
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
  </div>
</template>

<script>
import PotholeService from "@/services/PotholeService.js";
import Timeline from "@/components/Timeline.vue";
import emailjs from 'emailjs-com';

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
      
      emailjs.send('status_update_service','status_update',
        {
          status: "Repaired",
          road_name: "Cleveland Ave",
          neighborhood: "Linden",
          city: "Columbus",
          state: "OH",
          user_email: "fixmyroad.app@gmail.com",
          }, 'bRb3nMl54vtbGGDIK')
          .then((result) => {
            console.log('SUCCESS!', result.text);
        }, (error) => {
            console.log('FAILED...', error.text);
        });

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
  align-items: center;
  max-height: 25vh;
  line-height: 20%;
  width: 50%;
}

div.modal_image_holder{
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
}
.modal_image{
  max-width: 35vw;
  max-height: 35vh;
}
.status_holder{
  display: flex;
justify-content: center;
font-size: 15px;
}



</style>