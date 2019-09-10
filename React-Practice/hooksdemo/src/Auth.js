import {React,useState} from 'react';
import {makeStyles} from '@material-ui/core/styles'
import {auth} from './firebase/manager'
import {Container,TextField} from '@material-ui/core'


const useStyles = makeStyles(theme => 
    ({
        textField: {
            marginLeft: theme.spacing(1),
            marginRight: theme.spacing(1),
          }
    }));


export default function Auth()
{
    const [userName,setUserName] = useState(null)
    const [password,setPassword] = useState(null);


    return(
        <Container maxWith="sm">
            <form>
                <TextField 
                    id="outlined-required"
                    label="Stock Code"
                    className={classes.textField}
                    margin="normal"
                    variant="outlined"
                    value={userName}
                >

                 </TextField>
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