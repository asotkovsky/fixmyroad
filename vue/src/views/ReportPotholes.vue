<template>
<div class="report-form">
>>>>>>> a1a39300c7b3c892abb8b37c5b200ab60ba3ad40
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
    border-style: solid;
    border-width: 3px;
    border-color: #737373;
    border-radius:5px;
    padding: 5px;
    background-color: white;
   
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