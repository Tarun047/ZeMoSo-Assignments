import React from 'react';
import logo from './logo.svg';
import './App.css';
import {makeStyles} from '@material-ui/core/styles'
import {Box, Container, TextField} from '@material-ui/core'
const useStyles = makeStyles(
  theme=>(
    {
      root:
      {
        height:'100%',
        width:'100%',
        display:'flex',
        flexWrap:'wrap',
        backgroundColor:'#F94208',
      }
    })
)
function App() {
  const classes = useStyles();

  return (
        <Container fixed className={classes.root}>

        </Container>
  );
}

export default App;
