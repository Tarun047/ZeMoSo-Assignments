import auth from './firebase/manager'
import Login from'./Login'
import App from './App'
import React from 'react'
import theme from './theme'
import MuiThemeProvider from '@material-ui/styles/ThemeProvider';
export default function Main()
{
    if(!auth.user)
        return (
            <MuiThemeProvider theme={theme}>
                <Login />
            </MuiThemeProvider>
        )
    else
        return (<App />)
}