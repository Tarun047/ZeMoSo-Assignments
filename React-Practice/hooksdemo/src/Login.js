import React,{useState} from 'react';
import {makeStyles} from '@material-ui/core/styles'
import {auth} from './firebase/manager'
import {Box,TextField,Typography,Button} from '@material-ui/core'
import logo from './logo.svg'

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
            before:
            {
                boxShadow: 'inset 0 0 0 3000px rgba(255,255,255,0.3)',
                filter: 'blur(10px)',
            },
            display: 'flex',
            flexWrap: 'wrap',
            flexDirection: 'column',
            background:'inherit'
        }
    }));



export default function Login()
{

    const classes = useStyles();
    const [userName,setUserName] = useState(null);
    const [password,setPassword] = useState(null);

    function handleInput(event)
    {
        
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
                    className={classes.textField}
                    margin="normal"
                    variant="outlined"
                    value={userName}
                 />
                 <TextField
                    id="standard-password-input"
                    label="Password"
                    className={classes.textField}
                    value={password}
                    type="password"
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