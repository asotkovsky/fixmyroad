<template>
  <div>
    <section>
      <div class="rt-container">
        <div class="col-rt-12">
          <div class="Scriptcontent">
            <ul class="timeline">
              <li
                v-for="status in filteredStatuses"
                :key="status.dnu"
               v-bind:data-year="status.date"
                v-bind:data-text="status.name + ' by ' + status.fullName"
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
  props: ["statuses"],
  computed: {
    filteredStatuses(){
      let filteredStatuses = [];
      this.statuses.forEach((status) => {
        if (status.public == true){
          filteredStatuses.push(status);
        }
      });
      return filteredStatuses;
    }
  }
};
</script>

<style>
.timeline {
  height: 20px;
  list-style: none;
  text-align: justify;
  margin: 90px auto;
  display: flex;
  max-width: 88%;
  justify-content: space-between;

  background: linear-gradient(
    to bottom,
    rgba(15, 15, 15, 0) 0%,
    rgba(255, 255, 255, 0) 45%,
    rgb(115,115,115) 50%,
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
  background: #0079BF;
  text-align: center;
  line-height: 1.2;
  position: relative;
  border-radius: 50%;
  margin-right: 40px;
}

.timeline li:before {
  display: inline-block;
  content: attr(data-year);
  position: absolute;
  left: 50%;
  transform: translateX(-65%);
  font-size: 15px;
 white-space: nowrap;
}

.timeline li:nth-child(odd):before {
  top: -50px;
 
}
.timeline li:nth-child(even):before {
  bottom: -40px;
}

.timeline li:after {
  display: inline-block;
  content: attr(data-text);
  position: absolute;
  left: 50%;
  transform: translateX(-65%);
  font-size: 15px;
  margin-left: 35px;
  margin-right: 35px;


}

.timeline li:nth-child(odd):after {
  bottom: 0;
  margin-bottom: -10px;
  transform: translate(-65%, 100%);

}
.timeline li:nth-child(even):after {
  top: 0;
  margin-top: -10px;
  transform: translate(-65%, -100%);
}

</style>