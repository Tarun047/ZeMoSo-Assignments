import React,{useEffect} from 'react';
import { CardContent,Card,Box,Typography, Container,Toolbar,AppBar,InputBase,Button } from '@material-ui/core';
import SearchIcon from '@material-ui/icons/Search'
import {makeStyles,fade} from '@material-ui/core/styles'
import Task from './Models/Task'
import {useSelector,useDispatch } from "react-redux";
import Store from "./Store";
import { auth  } from './Login/firebase.js'

const useStyles = makeStyles(theme=>({
    pos: {
      marginBottom: 12,
    },
    root:
    {
        display:'flex',
        flexWrap:'wrap',
        flexDirection:'row',
        alignItems:'center',
    },
    search: 
    {
        position: 'relative',
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
        '&:hover': {
          backgroundColor: fade(theme.palette.common.white, 0.25),
          },
        marginRight: theme.spacing(2),
        marginLeft: 0,
        width: '100%',
        [theme.breakpoints.up('sm')]: {
          marginLeft: theme.spacing(3),
          width: 'auto',
        },
      },
      searchIcon: {
        width: theme.spacing(7),
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
      },
      inputRoot: {
        color: 'inherit',
      },
      tools:
      {
        flexGrow:1,
        display:'flex'
      },
      inputInput: {
        padding: theme.spacing(1, 1, 1, 7),
        transition: theme.transitions.create('width'),
        width: '100%',
        [theme.breakpoints.up('md')]: {
          width: 200,
        }
      },
      banner: {
        textAlign:"center"
        }
  }));
export default function InternApp(props)
{

    const classes = useStyles();
    const taskList = useSelector(state=>state.assignment.showList)
    const searchTerm = useSelector(state=>state.assignment.searchTerm)
    const dispatch = useDispatch()

    useEffect(()=>
      {
      dispatch({type:'UPDATE_DATA',payload:props.intern.assignments})
     },props.intern
    );
    
    

    
    console.log(taskList)
    return(
      
      <Container>
      <AppBar>
        <Toolbar>
          <Typography className={classes.title} variant="h6" noWrap>
            Intern Application
          </Typography>
          <div className={classes.tools}>
            <div className={classes.search}>
              <div className={classes.searchIcon}>
                <SearchIcon />
              </div>
              <InputBase
                placeholder="Search…"
                classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
                }}
                value={searchTerm}
                onChange={(event)=>dispatch({type:'CHANGE_SEARCH',payload:event.target.value})}
                inputProps={{ 'aria-label': 'search' }}

              />
            </div>
          </div>
          <Button color="inherit" onClick={()=>auth.signOut()}>Logout</Button>
        </Toolbar>
      </AppBar>
      <Toolbar></Toolbar>
      <div className={classes.banner}><Typography>Welcome {props.intern.name} </Typography></div>
      <Box className = {classes.root}>
        {
         taskList.map(
           (assignment,idx)=>
           <Task key={idx} title={assignment.task.taskName} description={assignment.task.description} deadline={assignment.task.deadline} status={assignment.status} />
         )
        }
      </Box>
      </Container>
      
    )
}