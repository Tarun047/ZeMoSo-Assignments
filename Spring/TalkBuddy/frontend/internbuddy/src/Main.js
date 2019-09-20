import React,{Component} from 'react';
import MentorApp from './MentorApp'
import AdminApp from './AdminApp'
import { auth, startFirebaseUI  } from './Login/firebase.js'
class Main extends React.Component
{
    state={
        user:null,
        role:null
        };
    constructor(props)
    {
        super(props);
        this.handleLogin=this.handleLogin.bind(this);
        auth.onAuthStateChanged((user)=>this.handleLogin(user));
    }

    async handleLogin(user)
    {
        this.setState({user:user});
        if(user==null)
            startFirebaseUI("#firebaseui-auth-container");
        else
        {
           const user_role = await fetch('/api/roles/rolelevel',{headers: {'uid':user.uid}});
           const newLevel = await user_role.text()
           //console.log(newLevel);
           this.setState({role:newLevel.slice(1,-1)})
        }
    }

    render()
    {
        if(this.state.user!==null && this.state.role!=null)
        {
                if(this.state.role=='ADMIN')
                    return <MentorApp user={this.state.user}/>;
                if(this.state.role=='ADMIN')
                    return <AdminApp user={this.state.user}/>
                else
                    return <MentorApp user={this.state.user} />
        }
        else
            return <div id="firebaseui-auth-container"></div>;
    }
}

export default Main