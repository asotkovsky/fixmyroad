<template>
  <div class="employee_card" v-on:dragover.prevent v-on:drop.prevent="handleDrop">
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
        PotholeService.createStatus(potholeId, {id: 8})
        }
       
    }

}
</script>

<style>
.employee_card{
    background-color: rgb(43, 226, 141);
    height: 100px;
    width: 100px;
}
</style>