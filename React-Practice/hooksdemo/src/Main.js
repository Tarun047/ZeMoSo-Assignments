import auth from './firebase/manager'
import Login from'./Login'
import App from './App'
import React from 'react'
export default function Main()
{
    if(!auth.user)
        return (
            <Login />
        )
    else
        return (<App />)
}