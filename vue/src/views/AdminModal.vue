<template>
  <div class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <span> Pothole ID: {{ pothole.id }} </span>
        <button type="button" class="btn-close" @click="close()">x</button>
      </header>

      <section class="modal-body">
        <slot name="body">
          <status-form :pothole="pothole" />
          <div class="delete_pothole">
            <delete-pothole
              class="delete"
              v-if="checkIfAdmin"
              :pothole="pothole"
            />
          </div>
        </slot>
      </section>
    </div>
  </div>
</template>

<script>
import DeletePothole from "../components/DeletePothole.vue";
import StatusForm from "../components/StatusForm.vue";

export default {
  name: "Modal",
  components: { StatusForm, DeletePothole },
  props: ["pothole"],
  methods: {
    close() {
      this.$emit("close");
    },
  },
  computed: {
    checkIfAdmin() {
      let roleIsAdmin = false;
      if (this.$store.state.user.authorities)
        this.$store.state.user.authorities.forEach((authority) => {
          if (authority.name == "ROLE_ADMIN") {
            roleIsAdmin = true;
          }
        });
      return roleIsAdmin;
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
  background-color: rgba(236, 227, 227, 0);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  box-shadow: 2px 2px 20px 1px;
  background-color: #f5f5f5;
  width: 55%;
  display: flex;
  flex-direction: column;
  font-family: sans-serif;
  min-width: 55%;
  border-radius: 5px;
}

.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
  align-items: center;
}

.modal-header {
  background-color: #737373;
  position: relative;
  border-bottom: 1px solid #eeeeee;
  color: #fad52f;
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
  font-family: sans-serif;
  font-size: 0.75em;
  background-color: whitesmoke;
  max-height: 80vh;
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
  color: #737373;
  background: #fad52f;
  border-radius: 5px;
  height: 25px;
  padding-top: .3px;
}
.delete {
  width: 50%;
}
.delete_pothole {
  display: flex;
  justify-content: center;
}
</style>