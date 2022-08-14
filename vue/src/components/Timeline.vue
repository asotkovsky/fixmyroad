<template>
  <div>
    <section>
      <div class="rt-container">
        <div class="col-rt-12">
          <div class="Scriptcontent">
            <ul class="timeline">
              <li 
              v-for="status in statuses" :key="status.id"
         v-bind:data-year="status.date"
            v-bind:data-text="status.name + ' by ' + status.email"
              ></li>
              
            </ul>
           
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
    props: ["statuses"]

};
</script>

<style>
 

.timeline {
  
  height: 20px;
  list-style: none;
  text-align: justify;
  margin: 90px auto;
  display: flex;
  justify-content: space-between;
 
  
  background: linear-gradient(
    to bottom,
    rgba(15, 15, 15, 0) 0%,
    rgba(255, 255, 255, 0) 45%,
    rgba(191, 128, 11, 1) 51%,
    rgba(255, 255, 255, 0) 57%,
    rgba(255, 255, 255, 0) 100%
  );
}

.timeline:after {
  width: 10%;

}

.timeline li {
  width: 20px;
  height: 20px;
  background: #13f2e7;
  text-align: center;
  line-height: 1.2;
  position: relative;
  border-radius: 50%;
  margin-right: 40px;
}

.timeline li:before {
  display: inline-block;
  content: attr(data-year);
  font-size: 26px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  
  
}

.timeline li:nth-child(odd):before {
  font-size: 20px;

  top: -40px;
}
.timeline li:nth-child(even):before {
  bottom: -40px;
   font-size: 20px;
}

.timeline li:after {
  display: inline-block;
  content: attr(data-text);
  font-size: 16px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  
}

.timeline li:nth-child(odd):after {
  bottom: 0;
  margin-bottom: -10px;
  transform: translate(-50%, 100%);
   font-size: 15px;
  
 

  
}
.timeline li:nth-child(even):after {
 
  top: 0;
 margin-top:  -10px;
transform: translate(-50%, -100%);
font-size: 15px;

}



</style>