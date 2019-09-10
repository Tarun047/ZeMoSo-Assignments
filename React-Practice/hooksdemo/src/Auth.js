import {React,useState} from 'react';
import {makeStyles} from '@material-ui/core/styles'




const useStyles = makeStyles(theme => 
    ({
        textField: {
            marginLeft: theme.spacing(1),
            marginRight: theme.spacing(1),
          }
    }));


export default function Auth()
{
    [userName,setUserName] = useState()
    [password,setPassword] = useState();


    return(
        <Container maxWith="sm">
            <form>
                <TextField 
                    id="outlined-required"
                    label="Stock Code"
                    className={classes.textField}
                    margin="normal"
                    variant="outlined"
                    value={intrest}
                    onChange={handleIntrestChange} >

                 </TextField>
            </form>
        </Container>
    )
}