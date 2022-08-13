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
  font-size: 1em;
  background-color:#E74C3C;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  display:flex; 
  padding: .2em;
  justify-content: center;
  align-items: center;

}
</style>