import { initializeApp, auth as _auth } from 'firebase';

const firebaseConfig = {
    apiKey: "AIzaSyA9HWRw8U5g8-CP265CPxeBWANH3_TzmeU",
    authDomain: "proinv-d0f77.firebaseapp.com",
    databaseURL: "https://proinv-d0f77.firebaseio.com",
    projectId: "proinv-d0f77",
    storageBucket: "proinv-d0f77.appspot.com",
    messagingSenderId: "485967196840",
    appId: "1:485967196840:web:d8129b78ef6aa0fe2a889d"
};

initializeApp(firebaseConfig);
const auth = _auth()

module.exports = {
    auth
}