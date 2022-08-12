<template>
<div class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header">
          Update Pothole ID: 
        </slot>
        <button
          type="button"
          class="btn-close"
          @click="close"
        >
          x
        </button>
      </header>

      <section class="modal-body">
        <slot name="body">
            <p>Update Status:</p>
            <status-form :pothole="pothole"/>
            <br/>
            <br/>
            <delete-pothole :pothole="pothole"/>
        </slot>
       </section>
    </div>
  </div>
</template>

<script>
import DeletePothole from '../components/DeletePothole.vue';
import StatusForm from '../components/StatusForm.vue'

export default {
    name: 'Modal',
    components: { StatusForm, DeletePothole },
    props: ["pothole"],
    methods: {
      close() {
        this.$emit('close');
      },
    },
  };

</script>

<style>
  .modal-backdrop {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .modal {
    background: #FFFFFF;
    box-shadow: 2px 2px 20px 1px;
    overflow-x: auto;
    display: flex;
    flex-direction: column;
  }

  .modal-header,
  .modal-footer {
    padding: 15px;
    display: flex;
  }

  .modal-header {
    position: relative;
    border-bottom: 1px solid #eeeeee;
    color: #737373;
    justify-content: space-between;
  }

  .modal-footer {
    border-top: 1px solid #eeeeee;
    flex-direction: column;
    justify-content: flex-end;
  }

  .modal-body {
    position: relative;
    padding: 20px 10px;
    color: #737373;
    font-family: sans-serif;
    font-size: 0.75em;
  }

  .btn-close {
    position: absolute;
    top: 0;
    right: 0;
    border: none;
    font-size: 20px;
    padding: 10px;
    cursor: pointer;
    font-weight: bold;
    color: #737373;
    background: transparent;
  }

  .btn-green {
    color: white;
    background: #4AAE9B;
    border: 1px solid #4AAE9B;
    border-radius: 2px;
  }
</style>