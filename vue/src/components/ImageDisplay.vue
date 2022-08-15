<template>
  <div id="fileupload">
      <label for="imageUpload">Select an Image:</label>
      <input @change="previewImage($event)" type="file" id="imageUpload" accept="image/*">
    
        <div v-if="previewUrl">
            <img :src="previewUrl" alt="">
        </div>


  </div>
</template>

<script>
import { uploadBytes } from "firebase/storage";
import { initializeApp } from "firebase/app";
import { getStorage, ref } from "firebase/storage";

const firebaseConfig = {
  apiKey: "AIzaSyACfSa2Z-kX1aazaVW76X-lDXRMFTnbRHI",
  authDomain: "te-vue-image-example.firebaseapp.com",
  projectId: "te-vue-image-example",
  storageBucket: "te-vue-image-example.appspot.com",
  messagingSenderId: "405746080437",
  appId: "1:405746080437:web:0e080d6c65345546d57cfc",
  measurementId: "G-YD808HPXYY"
  };
  const app = initializeApp(firebaseConfig);
  const storage = getStorage(app);
  const storageRef = ref(storage, 'images');

export default {
    name: 'image-display',
    date() {
        return {
            previewUrl: '',
            imageUrl: '',
            imageData: null
        }
    },
    methods: {
        previewImage(event) {
            this.imageData = event.target.files[0];
            this.previewUrl = URL.createObjectURL(this.imageData)
            console.log(this.imageData)
            this.uploadImage()
        },

        uploadImage() {
            uploadBytes(storageRef, this.imageData).then((snapshot) => {
                console.log(snapshot)
                console.log('Uploaded a blob or file!');
            });
        }
    }
}
</script>

<style>

</style>