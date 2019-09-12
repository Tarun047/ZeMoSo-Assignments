import React from 'react';
import logo from './logo.svg';
import './App.css';
import {makeStyles} from '@material-ui/core/styles'
import {Container, Toolbar,AppBar, IconButton,Typography,Button, Box, TextField} from '@material-ui/core'
import MenuIcon from '@material-ui/icons/Menu'
import BasicForm from './FormMaker/BasicFormMaker';
const useStyles = makeStyles(
  theme=>(
    {
      appBar:
      {
        flexGrow:1,
      },
      root:
      {
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
        <Container maxWidth="sm">
          <AppBar className={classes.appBar}>
            <Toolbar>
              <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
                <MenuIcon />
              </IconButton>
              <Typography variant="h6" className={classes.title}>
                Temp
              </Typography>
              <Button color="inherit">Logout</Button>
            </Toolbar>
          </AppBar>
          <Toolbar />
          <Box className={classes.root}>
            <BasicForm fields={{"First Name":TextField,"Last Name":TextField}} />
          </Box>
        </Container>
  );
}

export default App;
