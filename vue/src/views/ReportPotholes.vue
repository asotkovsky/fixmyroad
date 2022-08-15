<template>
<div id="container">
<div id="report-form">

    <report-map/> 
    <report-form/>

  </div>
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

#container {
  display: flex;
  justify-content: center;
  align-content: space-around;
}

#report-form{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;  
  animation-duration: 2s;
    animation-name: slideIn;
    animation-iteration-count: 1;
    width: 800px;
    border-style: solid;
    border-color: #737373;
    border-radius: 5px;
    background-color: white;
 }
 
 form {
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