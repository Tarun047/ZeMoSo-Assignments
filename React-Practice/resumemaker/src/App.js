import React from 'react';
import logo from './logo.svg';
import './App.css';
import {makeStyles} from '@material-ui/core/styles'
import {Container, Toolbar,AppBar, IconButton,Typography,Button, Box} from '@material-ui/core'
import MenuIcon from '@material-ui/icons/Menu'
import BasicForm from './FormMaker/BasicFormMaker';
const useStyles = makeStyles(
  theme=>(
    {
      appBarRoot:
      {
        flexGrow: 1,
      },
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
        <Container maxWidth="sm">
          <AppBar className={classes.appBarRoot}>
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
          <Box className={classes.root}>
            <BasicForm fields={{name:"FirstName"}} />
          </Box>
        </Container>
  );
}

export default App;
