<template>
  <div>
    <div class="modal_image_holder"><img class="modal_image" v-for="Url in pothole.imageUrl" :key="Url" v-bind:src="Url" /> </div>
    <timeline id="timeline" :statuses="pothole.statuses"/>
    <button class="status-buttons" id="noticed-button" v-show="checkIfUser && !isNoticed" v-on:click="noticePothole()" > Notice Pothole </button>
    <button class="status-buttons" id="subscription-button" v-on:click="manageSubscriptions()">{{subscribeButtonText}}</button>
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
import EmailService from "@/services/EmailService.js";
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
    isNoticed(){
      let isNoticed = false;
      this.pothole.statuses.forEach((status) => {
        if ((status.name == 'Noticed') && (this.$store.state.user.username == status.email)){
          isNoticed = true;
        }
      })
      return isNoticed;
    },
    isSubscribed(){
      let isSubscribed = false;
      this.pothole.statuses.forEach((status) => {
        if ((status.name == 'Subscribed') && (this.$store.state.user.username == status.email)){
          isSubscribed = true;
        }
      })
      return isSubscribed;
    },
    subscribeButtonText(){
      if (this.isSubscribed) {
        return "Unsubscribe"
      }
      else {return "Subscribe To Email Updates"}
    },
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

      PotholeService.createStatus(this.pothole.id, this.status).then(() =>{
      if ((this.status.id != 6) && (this.status.id != 7)){
      EmailService.sendStatusUpdateEmail(this.pothole)}
      }).catch((error) => {
          alert(error.message);})
      this.$store.dispatch("reloadPotholes");
      this.status = { id: null, date: new Date().toISOString().slice(0, 10) };
    },
  
    noticePothole(){
      this.status.id= 6
      this.handleSave();
      this.$store.dispatch("reloadPotholes");
    },
    manageSubscriptions(){

      if (!this.isSubscribed){
        const subscribeStatus = { id: 7, date: new Date().toISOString().slice(0, 10) };
        PotholeService.createStatus(this.pothole.id, subscribeStatus).then(() =>{
        alert("You have subscribed to status updates! To unsubscribe, visit the pothole details page.");
        this.$store.dispatch("reloadPotholes")});
      }

      if(this.isSubscribed){
        let statusIDToDelete = "";
        this.pothole.statuses.forEach((status) => {
          if (status.name == "Subscribed"){
            statusIDToDelete = status.id
          }
        })
        PotholeService.deleteStatus(statusIDToDelete).then(() =>{
          alert("You have unsubscribed from email updates on this pothole.");
          this.$store.dispatch("reloadPotholes")});
      }
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


.status-buttons{
  border-style: solid;
  border-color: #737373;
  border-radius: 5px;
  background-color: #737373;
  color: white;
  padding: 10px;
  font-family: sans-serif;
  font-weight: 750;
}

#noticed-button{
  margin-right: 10px;
}



</style>