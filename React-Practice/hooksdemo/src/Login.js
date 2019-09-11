import React,{useState} from 'react';
import {makeStyles} from '@material-ui/core/styles'
import {auth} from './firebase/manager'
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
            backgroundImage: 'url(http://bit.ly/2gPLxZ4)',
            backgroundRepeat: 'no-repeat',
            backgroundAttachment: 'fixed',
            backgroundSize: 'cover',

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
                boxShadow: 'inset 0 0 0 3000px rgba(255,255,255,0.3)',
                filter: 'blur(10px)',
            },
            display: 'flex',
            flexWrap: 'wrap',
            flexDirection: 'column',
            
        }
    }));



export default function Login()
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

    return(
        <Box className={classes.root}>
            <div className={classes.login}>
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
                <Button variant="outlined" color="primary" className={classes.button}>
                    Login
                </Button>
            </div>
        </Box>
    )
}