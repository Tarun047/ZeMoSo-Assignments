import React,{useEffect} from 'react'
import {useSelector,useDispatch } from "react-redux";
import {makeStyles,fade} from '@material-ui/core/styles'
import {Dialog,List,DialogTitle,TextField,DialogContent,DialogContentText,DialogActions,Box,Typography, Container,Toolbar,AppBar,InputBase,Button,FormControlLabel,FormLabel,RadioGroup,Radio,FormControl, CardContent } from '@material-ui/core'
import {auth} from './Login/firebase'
import SearchIcon from '@material-ui/icons/Search'
import NewIntern from './Models/NewIntern';
import {MuiPickersUtilsProvider,KeyboardDatePicker} from '@material-ui/pickers'
import DateFnsUtils from '@date-io/date-fns';
const useStyles = makeStyles(theme=>({
    pos: {
      minWidth:500,
    },
    root:
    {
        display:'flex',
        flexWrap:'wrap',
        flexDirection:'row',
        alignItems:'center',
    },
    placeholder: {
        height: 40,
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
        },
      statusfilter:
      {
        margin:theme.spacing(3),
      }
  }));
export default function MentorApp(props)
{

    const classes = useStyles();
    const dispatch = useDispatch();
    //Declaration Area
    const interns = useSelector((state)=>state.mentor.interns);
    const currentScreen = useSelector((state)=>state.mentor.currentScreen);
    const user = useSelector((state)=>state.mentor.user);
    const isLoading = useSelector((state)=>state.mentor.isLoading);

    const [inputRefs,setInputRefs] = React.useState({name:React.useRef(''),description:React.useRef(''),deadline:new Date()})
    //Initial Call to hit api and load data
    useEffect(()=>{
        async function fetchData(){
            const response = await fetch('/api/interns/')
            const interns = await response.json()
            console.log(interns)
            dispatch({type:'UPDATE_INTERN_DATA',payload:interns})
            dispatch({type:'CHANGE_LOADING_STATUS'})
        }
        fetchData();
    },[]);

    const addNewTask = async () =>{
      await fetch('/api/tasks/add_task', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({taskName:inputRefs.name.current.value,description:inputRefs.description.current.value,deadline:inputRefs.deadline})
      })
    }
    return(
        <Container>
        <AppBar>
        <Toolbar>
          <Typography className={classes.title} variant="h6" noWrap label="Application Label">
            Mentor Application
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
                onChange={(event)=>dispatch({type:'CHANGE_SEARCH',payload:event.target.value})}
                inputProps={{ 'aria-label': 'search' }}

              />
            </div>
          </div>
          <Button color="inherit" onClick={()=>dispatch({type:'SWITCH_SCREEN',payload:'ADD_TASK'})}>Add Task</Button>
          <Button color="inherit" onClick={()=>auth.signOut()}>Logout</Button>
        </Toolbar>
      </AppBar>
      <Toolbar></Toolbar>
      <Box data-testid="intern-container" width={1}>
        <Typography variant="h3">
          Interns List
        </Typography>
          <List>
              {
                  interns.map(intern=>
                  <NewIntern key={intern.id} intern={intern} />
              )}
          </List>
      </Box>
      <Dialog open={currentScreen==='ADD_TASK'} aria-labelledby="form-dialog-title" onClose={()=>dispatch({type:'SWITCH_SCREEN',payload:'INTERNS_LIST'})}>
        <DialogTitle id="form-dialog-title">Add a new Task</DialogTitle>
        <DialogContent>
          <DialogContentText>
           To add a new Task, enter the task information below
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="taskName"
            label="Name"
            inputRef={inputRefs.name}
            fullWidth
          />
          <TextField
            autoFocus
            margin="dense"
            multiline
            id="taskDescription"
            label="Description"
            inputRef={inputRefs.description}
            fullWidth
          />
          <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardDatePicker
          disableToolbar
          variant="inline"
          format="MM/dd/yyyy"
          margin="normal"
          id="date-picker-inline"
          label="Date picker inline"
          value={inputRefs.deadline}
          onChange={(newDate)=>setInputRefs({...inputRefs,deadline:newDate})}
          KeyboardButtonProps={{
            'aria-label': 'change date',
          }}
        />
        </MuiPickersUtilsProvider>
        </DialogContent>
        <DialogActions>
          <Button color="primary" onClick={()=>{addNewTask();dispatch({type:'SWITCH_SCREEN',payload:'INTERNS_LIST'})}}>
            Confirm
          </Button>
        </DialogActions>
      </Dialog>
      </Container>
    )
}