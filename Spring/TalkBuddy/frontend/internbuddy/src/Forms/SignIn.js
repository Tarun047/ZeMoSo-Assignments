import React from 'react';
import {createMuiTheme,Box,Grid,Link,TextField,CssBaseline,Avatar,Button,Checkbox,FormControlLabel} from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles, ThemeProvider, } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import createPalette from '@material-ui/core/styles/createPalette';
import createTypography from '@material-ui/core/styles/createTypography'
import  { firebase,auth,startFirebaseUI  } from '../Login/firebase'
import ErrorMessage from '../ErrorMessages/ErrorMessage'
import Background from '../static/images/Background.png'

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
  bg: {
    backgroundImage: `url(${Background})`,
    height: "100%",
    width: "100%",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover"
  }
}));

function validate(email)
{
  return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)
}
export default function SignIn() {
  const classes = useStyles();
  const inputRefs = {first_name:React.useRef(''),last_name:React.useRef(''),email:React.useRef(''),password:React.useRef('')}
  const [errorStatus,setErrorStatus] = React.useState({email:'',password:''})
  
  const signInUser =  (e) =>
  {
      e.preventDefault()
      if(!validate(inputRefs.email.current.value))
      {
        setErrorStatus({email:ErrorMessage.EMAIL_ERROR,password:''})
      }
      else
        auth.signInWithEmailAndPassword(inputRefs.email.current.value,inputRefs.password.current.value).catch(e=>setErrorStatus({email:' ',password:ErrorMessage.AUTHENTICATION_ERROR}))
      console.log(auth.currentUser)
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
          Sign In
        </Typography>
        <form className={classes.form}>
          <Grid container spacing={2}>
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
            onClick={signInUser}
            className={classes.submit}
          >
            Sign In
          </Button>
          
        </form>
        
      </div>
    </Container>
    </ThemeProvider>)
}