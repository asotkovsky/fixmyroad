import axios from 'axios';

export default {


    getPotholes() {
        return axios.get('/potholes')
      },
    getLocation(latitude, longitude)  {
      return axios.get('/location', {params:{latitude, longitude}})

    },
    createPothole(pothole){
      return axios.post('/potholes', pothole)
    },

    getPotholeStatuses(potholeId) {
      return axios.get(`/pothole/${potholeId}/statuses`, {params:{potholeId}})
    },
    deletePothole(potholeId){
      return axios.delete(`/potholes/${potholeId}`)
    },

    createStatus(potholeId, status){
      return axios.post(`/pothole/${potholeId}/statuses`, status)
    },
    getEmployees(){
      return axios.get('/employees')
    }

    



   

}