<template>
  <div class="employee_card" v-on:dragover.prevent v-on:drop.prevent="handleDrop">
      {{employee.fullName}}
        {{employee.name}}

      
  </div>
</template>

<script>
import PotholeService from "@/services/PotholeService.js";
export default {
    data() {
        return {

            employeesList:[]
        }
    },
    props: {
        employee: Object

    },
    
    methods: {
        handleDrop(event){
        console.log(event.dataTransfer.getData("text/plain"))
        let potholeId = event.dataTransfer.getData("text/plain")
        let status= { id: 8, date: new Date().toISOString().slice(0, 10), email: this.employee.name}
        PotholeService.createStatus(potholeId, status)
        }
       
    }

}
</script>

<style>
.employee_card{
    background-color: #737373;
    padding: 12px;
    border-radius: 5px;
    
    
}
.employee_card:hover{
    filter: brightness(65%)
}
</style>