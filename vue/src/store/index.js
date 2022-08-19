import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import  PotholeService  from '../services/PotholeService.js'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    currentPin: {},
    currentUserAuthorities: [],
    selectedPothole: {},
    potholes: [],
    filter: {
      severity: "",
      roadName: "",
      neighborhood: "",
      description: "",
      status: "",
      locationOnRoadway: "",
      city: null,
      state: null,
      numberOfDays: "",
      employeeName: "",
      assigned:0
    }
  },
  getters:{
    currentUserIsAdmin(state){
      if(state.user.authorities){
        return state.user.authorities.find(auth=>auth.name=="ROLE_ADMIN")
      }
    }
  },

  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`

    },
    SET_POTHOLES(state, potholes) {
      state.potholes = potholes


    },
    SET_CURRENT_PIN(state, pin) {
      state.currentPin = pin
    },
    SET_SELECTED_POTHOLE(state, pothole) {
      state.selectedPothole = pothole
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    SET_USER_AUTHORITIES(state, currentUserAuthorities) {
      state.currentUserAuthorities = currentUserAuthorities
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_FILTER_FIELD(state, payload) {
      state.filter[payload.fieldName] = payload.value
    },
    CLEAR_FILTERS(state) {
      state.filter = {
        severity: "",
        roadName: "",
        neighborhood: "",
        description: "",
        status: "",
        locationOnRoadway: "",
        city: null,
        state: null,
        numberOfDays: "",
        employeeName: "",
        assigned:0

      }
    }

  },
  actions: {
reloadPotholes(context){
  PotholeService.getPotholes().then(response => {
    let potholes = response.data
    context.commit("SET_POTHOLES", potholes)
  })
    }
  }
})


