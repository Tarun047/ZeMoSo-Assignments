import React,{useState} from 'react';
import {makeStyles} from '@material-ui/core/styles'
import {auth} from './firebase/manager'
import {Container,TextField,Typography,Button} from '@material-ui/core'
import logo from './logo.svg'

const useStyles = makeStyles(theme => 
    ({
        root: {
            position: "absolute",
            top:0,
            left:0,
            bottom:0,
            right:0,
            display: 'flex',
            flexWrap: 'wrap',
            flexDirection: 'column',
            alignItems:'center',
            overflow: 'hidden',
            backgroundColor: '#08F9DC',
          },
        textField: {
            marginLeft: theme.spacing(1),
            marginRight: theme.spacing(1),
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
        <Container maxWith="lg" className={classes.root}>
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
            
        </Container>
    )
}