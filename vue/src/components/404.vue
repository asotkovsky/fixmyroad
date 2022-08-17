<template>
<div>
  <div id="page-not-found-view">
        <img class="not-found-icons" v-bind:src="require('../assets/four.png')">
        <img class="not-found-icons" v-bind:src="require('../assets/punctured-tire.png')">
        <img class="not-found-icons" v-bind:src="require('../assets/four.png')">
    </div>
    <h1 id="not-found-error-message">Oh no! You've hit a bump in the road.</h1>
</div>
</template>

<script>
export default {

}
</script>

<style>
#page-not-found-view {
display: flex;
height: 300px;
margin-top: 60px;
justify-content: center;
}

#not-found-error-message {
color: #FAD62B;
text-align: center;
}

.not-found-icons {
    margin-top: 30px;
}

</style>