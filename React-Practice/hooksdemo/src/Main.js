import auth from './firebase/manager'
import Login from'./Login'
import App from './App'
import React,{useState,useEffect} from 'react'
import theme from './theme'
import MuiThemeProvider from '@material-ui/styles/ThemeProvider';
export default function Main()
{
    const [user,setUser] = useState(auth.user)
    useEffect(() => {
        auth.onAuthStateChanged((user)=>{setUser(user)})
    }, [user])

    if(!auth.user)
        return (
            <MuiThemeProvider theme={theme}>
                <Login />
            </MuiThemeProvider>
        )
    else
        return (<App />)
}