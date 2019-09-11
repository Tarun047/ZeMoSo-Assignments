import React,{useState} from 'react';
import {makeStyles} from '@material-ui/core/styles'
import auth from './firebase/manager'
import {Box,TextField,Typography,Button} from '@material-ui/core'
import logo from './logo.svg'
import { statement } from '@babel/template';


const useStyles = makeStyles(theme => 
    ({
        root: {
            position: "absolute",
            width:'100%',
            height:'100%',
            display: 'flex',
            flexWrap: 'wrap',
            flexDirection: 'column',
            alignItems:'center',
            alignSelf:'center',
            overflow: 'hidden',
            backgroundImage:'url(http://bit.ly/2gPLxZ4)',
            backgroundRepeat: 'no-repeat',
            backgroundAttachment:'fixed',
            backgroundSize:'cover'
            

          },
        textField: {
            marginLeft: theme.spacing(1),
            marginRight: theme.spacing(1),
          },
        login: 
        {
            "&:before":
            {
                content:"Stocks",
                background: 'inherit',
               
            },
            display: 'flex',
            flexWrap: 'wrap',
            flexDirection: 'column',
            boxShadow: 'inset 0 0 0 3000px rgba(255,255,255,0.3)',
            filter: 'blur(10px)',
            margin:100
            
        }
    }));



export default function Login(props)
{

    const classes = useStyles();
    const [credentials,setCredentials] = useState({
        userName:'',
        password:''
    })

    function handleChange(event)
    {
        setCredentials({...credentials,[event.target.name]:event.target.value})
    }

    function handleSubmit(event)
    {
        const re =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/

        if(!re.test(credentials.userName.toLowerCase()))
            alert('Invalid username')
        else if(credentials.password=='')
            alert('Password can\'t be blank')
        else
        {
            auth.signInWithEmailAndPassword(credentials.userName, credentials.password)
            .then((result)=>props.onLogin(result))
            .catch(function(error) {
                // Handle Errors here.
                var errorCode = error.code;
                var errorMessage = error.message;
                console.log(errorMessage);
              });
        }
    }
    return(
        <Box className={classes.root}>
            <form className={classes.login}>
                <Typography variant="h3" component="h3" gutterBottom>
                    Stocks Application
                </Typography>
                <TextField 
                    id="outlined-required"
                    label="User Name"
                    name="userName"
                    className={classes.textField}
                    margin="normal"
                    variant="outlined"
                    autoComplete="off"
                    onChange={handleChange}
                    value={credentials.userName}
                 />
                 <TextField
                    id="standard-password-input"
                    label="Password"
                    name="password"
                    className={classes.textField}
                    value={credentials.password}
                    type="password"
                    onChange={handleChange}
                    autoComplete="current-password"
                    margin="normal"
                    />
                <Button variant="outlined" color="primary" className={classes.button} onClick={handleSubmit}>
                    Login
                </Button>
            </form>
        </Box>
    )
}