import React,{useState} from 'react';
import {makeStyles} from '@material-ui/core/styles'
import {auth} from './firebase/manager'
import {Container,TextField,Typography,Button} from '@material-ui/core'
import logo from './logo.svg'

const useStyles = makeStyles(theme => 
    ({
        root: {
            display: 'flex',
            flexWrap: 'wrap',
            flexDirection: 'column',
            alignItems:'center',
            overflow: 'hidden',
            margin: 100
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


    return(
        <Container maxWith="sm" className={classes.root}>
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