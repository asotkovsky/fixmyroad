<template>
  <div id="report-form">
    <report-map/> 
    <report-form/>

  </div>
</template>

<script>
import ReportForm from '../components/ReportForm.vue'
import ReportMap from '../components/ReportMap.vue'

export default {
  components: { ReportForm,ReportMap },

}
</script>

<style scoped>
#report-form {
  border-style: solid;
  border-width: 3px;
  border-color: #737373;
  border-radius:5px;
  padding: 5px;
  background-color: white;
}
</style>