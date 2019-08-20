import StyledFirebaseAuth from 'react-firebaseui/StyledFirebaseAuth';
import React from 'react';
import firebase from 'firebase';
const config = {
                 apiKey: "AIzaSyALHRUe4e80ZTgi7EuqwBzvlPEhjiDEr7U",
                 authDomain: "internbuddy-c29f8.firebaseapp.com",
                 databaseURL: "https://internbuddy-c29f8.firebaseio.com",
                 projectId: "internbuddy-c29f8",
                 storageBucket: "",
                 messagingSenderId: "207196259614",
                 appId: "1:207196259614:web:f6bfbb8492b0113f"
               };

firebase.initializeApp(config);
class Login extends React.Component
{


    constructor(props)
    {
        super(props);
        this.state = {
             uiConfig:{
                         // Popup signin flow rather than redirect flow.
                         signInFlow: 'popup',
                         // We will display Google and Facebook as auth providers.
                         signInOptions: [
                           firebase.auth.EmailAuthProvider.PROVIDER_ID,
                         ],
                         callbacks: {
                           // Avoid redirects after sign-in.
                           signInSuccessWithAuthResult: ()=>{ props.onLogin(); return false}
                         }
                       }
        };

    }

    render()
    {
        return (<StyledFirebaseAuth uiConfig={this.state.uiConfig} firebaseAuth={firebase.auth()} />);
    }
}

export default Login;