import React,{Component} from 'react';
import MentorApp from './MentorApp'
import AdminApp from './AdminApp'
import InternApp from './InternApp'
import { auth, startFirebaseUI  } from './Login/firebase.js'
import { Snackbar,TextField,Box } from '@material-ui/core';
import BasicForm from './BasicFormMaker'
import {Provider} from 'react-redux'
import Store from './Store'
class Main extends React.Component
{
    state={
        user:null,
        role:null,
        open:false
        };
    classes={
            root:
            {
                display:'flex',
                flexWrap:'wrap',
                flexDirection:'column',
                alignItems:'center',
            }
    }
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
           const profile = await user_role.json()
           if(profile.role==='UNRECOGNIZED_USER' && user!=null)
           {
              
               const response = await fetch('/api/interns/createintern/'+user.uid, {
                method: 'POST',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json',
                },
                body:JSON.stringify({
                    name:user.email,
                    rating:0
                })
              });
              const intern = await response.json();
               this.setState({role:intern});
           }
           else
           {
            if(profile.role=='INTERN')
                this.setState({role:profile.intern})
            else if(profile.role=="MENTOR")
                this.setState({role:profile.mentor})
            else
                this.setState({role:"ADMIN"})
           }
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
                    return <Provider store={Store}><InternApp user={this.state.user} intern={this.state.role} /></Provider>
        }
        else
            return(
                <div id="firebaseui-auth-container"></div>
            );
    }
}

export default Main