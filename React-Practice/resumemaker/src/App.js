import React from 'react';
import logo from './logo.svg';
import './App.css';
import classes from '*.module.sass';
useStyles = makeStyles(
  theme=>(
    {
      root:
      {
        height:'100%',
        width:'100%',
        display:flex,
        flexWrap:'',
      }
    })
)
function App() {
  classes = useStyles();

  return (
    <div className="App">
      <Box className={classes.root}>

      </Box>
    </div>
  );
}

export default App;
