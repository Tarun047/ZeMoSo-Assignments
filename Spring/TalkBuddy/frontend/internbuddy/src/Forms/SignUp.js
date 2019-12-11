import React from 'react';
import {createMuiTheme,Box,Grid,Link,TextField,CssBaseline,Avatar,Button,Snackbar} from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import {makeStyles} from '@material-ui/core/styles'
import Container from '@material-ui/core/Container';
import createPalette from '@material-ui/core/styles/createPalette';
import createTypography from '@material-ui/core/styles/createTypography'
import  { firebase,auth,startFirebaseUI  } from '../Auth/firebase'
import ErrorMessage from '../ErrorMessages/ErrorMessage';
import { ThemeProvider } from '@material-ui/styles';



const theme = createMuiTheme({
    typography: createTypography(createPalette({}), {
        fontFamily: '"IRANSansWeb"'
      })
})

const useStyles = makeStyles(theme => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',

  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));


export default function SignUp() {
  const classes = useStyles();
  const inputRefs = {first_name:React.useRef(''),last_name:React.useRef(''),email:React.useRef(''),password:React.useRef('')}
  const [errorStatus,setErrorStatus] = React.useState({first_name:'',last_name:'',email:'',password:'',externalError:''})

  const validate = ()=>
  {
    const newErrorStatus = {first_name:'',last_name:'',email:'',password:'',externalError:''};
    if(!/[A-Z][a-zA-Z][^#&<>\"~;$^%{}?]{1,20}$/.test(inputRefs.first_name.current.value))
    {
      newErrorStatus.first_name=ErrorMessage.NAME_ERROR;
      setErrorStatus(newErrorStatus);
      return false;
    }
    else if(!/[A-Z][a-zA-Z][^#&<>\"~;$^%{}?]{1,20}$/.test(inputRefs.last_name.current.value))
    {
      newErrorStatus.last_name=ErrorMessage.NAME_ERROR;
      setErrorStatus(newErrorStatus);
      return false;
    }
    else if(!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(inputRefs.email.current.value))
    {
      newErrorStatus.email=ErrorMessage.EMAIL_ERROR;
      setErrorStatus(newErrorStatus);
      return false;
    }
    else if(!/^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})/.test(inputRefs.password.current.value))
    {
      newErrorStatus.password=ErrorMessage.WEAK_PASSWORD_ERROR;
      setErrorStatus(newErrorStatus);
      return false;
    }
    return true;
  }

  const signUpUser =  (e) =>
  {
      e.preventDefault()
      if(validate())
        auth.createUserWithEmailAndPassword(inputRefs.email.current.value,inputRefs.password.current.value).then(
    (response)=>{
        const user = response.user
        console.log(user)
        fetch('/api/interns/createintern/'+user.uid, {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
            },
            body:JSON.stringify({
                name:inputRefs.first_name.current.value+" "+inputRefs.last_name.current.value,
                rating:0
            })
          })  
      }).catch(firebase_error=>setErrorStatus({first_name:'',last_name:'',email:'',password:'',externalError:firebase_error.message}))
 }
  
  return (
    <ThemeProvider theme={theme}>
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Typography variant="h2">Intern Buddy</Typography>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        <form className={classes.form}>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                error={errorStatus.first_name.length!==0}
                autoComplete="fname"
                name="firstName"
                variant="outlined"
                required
                fullWidth
                id="firstName"
                label="First Name"
                helperText={errorStatus.first_name}
                inputRef={inputRefs.first_name}
                autoFocus
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                error={errorStatus.last_name.length!==0}
                variant="outlined"
                required
                fullWidth
                id="lastName"
                label="Last Name"
                name="lastName"
                helperText={errorStatus.last_name}
                inputRef={inputRefs.last_name}
                autoComplete="lname"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                error={errorStatus.email.length!==0}
                variant="outlined"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                helperText={errorStatus.email}
                inputRef={inputRefs.email}
                autoComplete="email"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                error={errorStatus.password.length!==0}
                variant="outlined"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                helperText={errorStatus.password}
                inputRef={inputRefs.password}
                autoComplete="current-password"
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            onClick={signUpUser}
            className={classes.submit}
          >
            Sign Up
          </Button>
        </form>
        <Snackbar
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'left',
          }}
          open={errorStatus.externalError.length!==0}
          autoHideDuration={1}
          message={errorStatus.externalError}
        />
      </div>
    </Container>
    </ThemeProvider>
    )
}