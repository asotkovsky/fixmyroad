<template>
  <div :pothole="pothole" @click="handleDelete()" class="delete-button"><span>Delete Pothole</span></div>
</template>

<script>
import PotholeService from '@/services/PotholeService.js'

export default {
name: "delete-button",
props: {
    pothole: Object,
  },
methods:{
    handleDelete(){
     if (confirm("Are you sure you want to delete this pothole?")) {
    PotholeService.deletePothole(this.pothole.id)
      .then(()=>location.reload())
      .catch((error) => {
          alert(error.message);
    })
    }
  }
}}
</script>

<style>
div.delete-button{
  font-family: sans-serif;
  font-size: 1.5em;
  background-color: #737373;
  color: #FAD52F;
  text-decoration: none;
  border-radius: 5px;
  display:flex; 
  padding: .1em;
  justify-content: center;
  align-items: center;

}
</style>