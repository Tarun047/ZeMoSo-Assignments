const firebase = require('firebase')
const firebaseui = require('firebaseui')

const config = {
                 apiKey: "AIzaSyALHRUe4e80ZTgi7EuqwBzvlPEhjiDEr7U",
                 authDomain: "internbuddy-c29f8.firebaseapp.com",
                 databaseURL: "https://internbuddy-c29f8.firebaseio.com",
                 projectId: "internbuddy-c29f8",
                 storageBucket: "",
                 messagingSenderId: "207196259614",
                 appId: "1:207196259614:web:f6bfbb8492b0113f"
               };
firebase.initializeApp(config)
const auth = firebase.auth()
const uiConfig = ({
  signInOptions: [
    firebase.auth.EmailAuthProvider.PROVIDER_ID,
    firebase.auth.GoogleAuthProvider.PROVIDER_ID,
  ],
  tosUrl: '/terms-of-service' ,// This doesn't exist yet
  callbacks: {
        // Avoid redirects after sign-in.
        signInSuccessWithAuthResult: () => false
      }
})
const ui = new firebaseui.auth.AuthUI(firebase.auth())
const startFirebaseUI = function (elementId) {
  ui.start(elementId, uiConfig)
}

module.exports = {
  firebase,
  auth,
  startFirebaseUI
}