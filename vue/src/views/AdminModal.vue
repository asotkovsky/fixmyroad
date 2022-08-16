<template>
<div class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
          <span>
          Pothole ID: {{pothole.id}}
          </span>
        <button
          type="button"
          class="btn-close"
          @click="close()"
        >
          x
        </button>
      </header>

      <section class="modal-body">
        <slot name="body">
            <status-form :pothole="pothole"/>
            <delete-pothole v-if="checkIfAdmin" :pothole="pothole"/>
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
      }
    },
    computed: {
    checkIfAdmin(){
    let roleIsAdmin = false;
    this.$store.state.currentUserAuthorities.forEach(authority => {
      console.log(authority.name)
      if (authority.name == 'ROLE_ADMIN') {
        roleIsAdmin =  true;
      }
    });
    return roleIsAdmin;
    }
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
    background-color: rgba(236, 227, 227, 0);
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .modal {

    box-shadow: 2px 2px 20px 1px;
    background-color:#F5F5F5;
    width: 60%;
    display: flex;
    flex-direction: column;
    font-family: sans-serif;
    min-width: 300px;
    border-radius: 5px;
    height: auto;

  }

  .modal-header,
  .modal-footer {
    padding: 15px;
    display: flex;
    align-items: center;
    
  }

  .modal-header {
    background-color: blanchedalmond;
    position: relative;
    border-bottom: 1px solid #eeeeee;
    color: #737373;
    justify-content: space-between;
    font-size: 30px;
    font-weight: bolder;
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
    background-color: whitesmoke;
  }

  .btn-close {
    /* position: absolute; */
    /* top: 0; */
    /* right: 0; */
    border: none;
    font-size: 15px;
    padding: 5px;
    cursor: pointer;
    font-weight: bold;
    color: #FAD52F;
    background: #737373;
    border-radius: 5px;
    height:25px
    
  }

</style>