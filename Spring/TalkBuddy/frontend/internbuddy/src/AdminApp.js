import React,{Component} from 'react';
import { auth  } from './Auth/firebase.js'
import {Container,AppBar,Button,Toolbar,Typography} from '@material-ui/core'


const theme = {
  spacing: 8,
}

class AdminApp extends Component
{
    state = {
    user:null
    }
    constructor(props)
    {
        super(props);

        this.logout = ()=>{auth.signOut()}
    }
    componentWillMount()
    {
       this.setState({user:this.props.user});
    }

    render()
    {
        return(<AppBar position="static">
                 <Toolbar>
                   <Typography type="title" color="inherit" style={{ flex: 1 }}>
                     Welcome {this.state.user.displayName}
                   </Typography>
                   <div>
                     <Button raised color="accent" onClick={this.logout}>
                       Logout
                     </Button>
                   </div>
                 </Toolbar>
               </AppBar>
               )
    }
}

export default AdminApp;