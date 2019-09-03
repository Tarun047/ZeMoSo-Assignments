import React,{Component} from 'react';
import App from './App'
import { auth, startFirebaseUI  } from './Login/firebase.js'
class Main extends React.Component
{
    state={
        user:null};
    constructor(props)
    {
        super(props);
        this.handleLogin=this.handleLogin.bind(this);
        auth.onAuthStateChanged((user)=>this.handleLogin(user));
    }

    handleLogin(user)
    {
        this.setState({user:user});
        if(user==null)
            startFirebaseUI("#firebaseui-auth-container");
    }

    render()
    {
        if(this.state.user!==null)
            return <App user={this.state.user}/>;
        else
            return <div id="firebaseui-auth-container"></div>;
    }
}

export default Main