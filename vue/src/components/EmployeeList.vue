<template>
  <div>
      <employee v-for="employee in employeesList" v-bind:key="employee.id" v-bind:employee="employee"/>

  </div>
</template>

<script>
import PotholeService from "@/services/PotholeService.js"
import Employee from "@/components/Employee.vue";
export default {
    components:{
    Employee
    },
      data() {
        return {
            employeesList:[]
        }

    },
    mounted(){
        PotholeService.getEmployees().then(response => {
            console.log(response.data)
            this.employeesList=response.data


        })

    }

}
</script>

<style>

</style>