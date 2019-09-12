import React from 'react';
import logo from './logo.svg';
import './App.css';
import {makeStyles} from '@material-ui/core/styles'
import {Box, Container, TextField} from '@material-ui/core'
import BasicForm from './FormMaker/BasicFormMaker';
const useStyles = makeStyles(
  theme=>(
    {
      root:
      {
        height:'100%',
        width:'100%',
        display:'flex',
        flexWrap:'wrap',
        textAlign:'center'
      }
    })
)
function App() {
  const classes = useStyles();

  return (
        <Container maxWidth="sm" className={classes.root}>
          <BasicForm fields={{name:"FirstName"}} />
        </Container>
  );
}

export default App;
