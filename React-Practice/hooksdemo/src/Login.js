import React,{useState} from 'react';
import {makeStyles} from '@material-ui/core/styles'
import {auth} from './firebase/manager'
import {Container,TextField} from '@material-ui/core'


const useStyles = makeStyles(theme => 
    ({
        root: {
            display: 'flex',
            flexWrap: 'wrap',
            flexDirection: 'column',
            alignItems:'center',
            overflow: 'hidden',
            margin: 16
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
            <form>
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
            </form>
        </Container>
    )
}