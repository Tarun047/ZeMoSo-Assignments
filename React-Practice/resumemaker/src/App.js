import React from 'react';
import logo from './logo.svg';
import './App.css';
import {makeStyles} from '@material-ui/core/styles'
import {Container, Toolbar,AppBar, IconButton,Typography,Button} from '@material-ui/core'
import MenuIcon from '@material-ui/icons/Menu'
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
        flexDirection:'column',
        alignItems:'center',
      },
      menuButton: {
        marginRight: theme.spacing(2),
      },
      title: {
        flexGrow: 1,
      },
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
