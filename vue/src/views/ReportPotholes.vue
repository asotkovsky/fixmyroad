<template>
<div class="report-form">
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

<style>
.report-form{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;  
  animation-duration: 2s;
    animation-name: slideIn;
    animation-iteration-count: 1;
 }
 
 form{
   display: flex;
    flex-direction: column;
    
 }


 @keyframes slideIn {
    from {
        margin-left: -300%;
    }
    to {
        margin-left: 0%;
     }
}

</style>