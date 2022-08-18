<template>
<div id="details">
<h1>Details
</h1>
 <p>Severity: <img
        class="severity-icon"
        v-if="pothole.severity != 0"
        v-bind:src="
          require('../assets/severity-icon-' + pothole.severity + '.png')
        "
      /></p>
<p>
    Last Status: {{getlastStatusName()}}
</p>

<p> 
    Road Name: {{pothole.roadName}}
</p>

<p> 
    Neighborhood: {{pothole.neighborhood}}
</p>

<p> 
    City: {{pothole.city}}
</p>

<p> 
    State: {{pothole.state}}
</p>

<p> 
    Description: {{pothole.description}}
</p>

<p>
    This pothole has been noticed {{getNoticedCount()}} {{noticedText}}!
</p>

</div>
</template>

<script>
export default {
    name: "pothole-details", 
    props: ["pothole"],
    methods: {
        getlastStatusName(){
      let filteredStatuses = [];
      this.pothole.statuses.forEach((status) => {
        if (status.public == true){
          filteredStatuses.push(status);
        }
      });
      return filteredStatuses[filteredStatuses.length -1].name
    },
    getNoticedCount(){
        let noticedStatuses = [];
        this.pothole.statuses.forEach((status)=> {
            if(status.name == "Noticed"){
                noticedStatuses.push(status);
            }
        })
        if(noticedStatuses.length != 1){
            this.noticedText = "times"
        }
        return noticedStatuses.length 
    }
    },
    data(){
        return{
            noticedText: "time"
        }
    }



}
</script>

<style>
#details {
    display: flex;
    flex-direction: column;
    text-align: left;
}

#details p{
    font-size: 1.2em;
    margin: 2px;
    padding-right: 20px;
    justify-content: center;
    font-weight: 700;
}

#details h1{
    margin: 0px;
}

#details img {
    margin-left: -5px;
    height: 30px;
    width: 30px;
    /* margin-top: 5px;
    margin-bottom: 5px; */
}
</style>