// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getStorage } from "firebase/storage"

const firebaseConfig = {
  apiKey: "AIzaSyDPb-N2fvZlh5lzOZ_7xZkl2EBUkH6KZnI",
  authDomain: "zoomcar-clone-ba841.firebaseapp.com",
  projectId: "zoomcar-clone-ba841",
  storageBucket: "zoomcar-clone-ba841.appspot.com",
  messagingSenderId: "237741613834",
  appId: "1:237741613834:web:6d3514b423b0cc453590f3",
  measurementId: "G-6D4XW0S0ER"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

export const storage = getStorage(app);