import React,{Component} from 'react';
import MentorApp from './MentorApp'
import AdminApp from './AdminApp'
import InternApp from './InternApp'
import  { firebase,auth,startFirebaseUI  } from './Auth/firebase.js'
import { Snackbar,Typography,Box,Paper,Link } from '@material-ui/core';
import { createMuiTheme, makeStyles } from '@material-ui/core/styles';
import createPalette from '@material-ui/core/styles/createPalette';
import createTypography from '@material-ui/core/styles/createTypography'
import BasicForm from './BasicFormMaker'
import {Provider} from 'react-redux'
import Store from './Store'
import SignUp from './Forms/SignUp'
import SignIn from './Forms/SignIn'
import { ThemeProvider } from '@material-ui/styles';
import './App.css'
function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {'Copyright © '}
        <Link color="inherit" href="https://www.zemosolabs.com">
          ZeMoSo Labs
        </Link>{' '}
        {new Date().getFullYear()}
        {'.'}
      </Typography>
    );
  }

const theme = createMuiTheme({
    typography: createTypography(createPalette({}), {
        fontFamily: '"IRANSansWeb"'
      })
})

class Main extends React.Component
{
    state={
        user:null,
        role:null,
        open:false,
        screen:"LOADING",
        errorStatus:localStorage.getItem("ExternalError"),
        };
    classes={
            root:
            {
                display:'flex',
                flexWrap:'wrap',
                flexDirection:'column',
                alignItems:'center',
            },
            main:
            {
            }
    }
    constructor(props)
    {
        super(props);
        console.log(this.state)
        auth.onAuthStateChanged((user)=>this.setRole(user));
    }

    async setRole(user)
    {
        this.setState({user:user,screen:user===null?"SIGN_UP":"LOADING"})
        if(user!==null){
        const user_role = await fetch('/api/roles/rolelevel',{headers: {'uid':user.uid}});
        const profile = await user_role.json()
        console.log(profile.role)
        if(profile.role=='UNRECOGNIZED_USER'){
          this.setState({screen:"SIGN_UP"})
          localStorage.setItem("ExternalError","Please Sign up we don't recognize you yet")
          auth.signOut()
        }
        else if(profile.role=='INTERN')
             this.setState({role:profile.intern})
        else if(profile.role=="MENTOR")
             this.setState({role:profile.mentor})
        else 
            this.setState({role:"ADMIN"})
        }
    }
    
    componentDidUpdate()
    {
        if(this.state.user==null && this.state.screen==='SIGN_IN')
            startFirebaseUI("#firebaseui-auth-container");
            
    }

   
    render()
    {
        if(this.state.user!==null && this.state.role!=null)
        {
                if(this.state.role=='ADMIN')
                    return <Provider store={Store}><ThemeProvider theme={theme}><MentorApp user={this.state.user}/></ThemeProvider></Provider>;
                if(this.state.role=='ADMIN')
                    return <AdminApp user={this.state.user}/>
                else
                    return <Provider store={Store}><ThemeProvider theme={theme}><InternApp user={this.state.user} intern={this.state.role} /></ThemeProvider></Provider>
        }
        else
        {
            if(this.state.screen==='SIGN_UP')
            {
                return(
                    <Box>
                        <SignUp />
                        <Typography variant="body2" align="center">
                            <Link color="inherit" onClick={()=>this.setState({screen:"SIGN_IN"})}>
                                Been Here Before ? Sign In.
                            </Link>
                        </Typography>
                        <Box mt={5}>
                            <Copyright />
                        </Box>
                        <Snackbar
                        anchorOrigin={{
                        vertical: 'bottom',
                        horizontal: 'left',
                         }}
                        open={this.state.errorStatus!==null}
                        autoHideDuration={1}
                        message={this.state.errorStatus}
                        />
                    </Box>);
            }
            else if(this.state.screen==='SIGN_IN')
            {
                return(
                    <Box >
                    <SignIn />
                    <div id="firebaseui-auth-container"></div>
                    <Typography variant="body2" align="center">
                            <Link color="inherit" onClick={()=>this.setState({screen:"SIGN_UP"})}>
                                First time here ? Sign Up
                            </Link>
                        </Typography>
                    </Box>
                );
            }
            else
            {
                return(<div> 
                <Typography>Loading...</Typography>
                </div>)
            }
        }
    }
}

export default Main