import React from 'react'
import {Avatar,ListItem,ListItemAvatar,Divider,Typography,Toolbar,AppBar,IconButton,Button,List,ListItemText,Dialog,Slide} from '@material-ui/core'
import {makeStyles} from '@material-ui/core/styles'
import CloseIcon from '@material-ui/icons/CloseRounded'
import TaskSelector from '../Layout/TaskSelector'
const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
  });

const useStyles = makeStyles((theme)=>({
    appBar: {
        position: 'relative',
      },
      title: {
        marginLeft: theme.spacing(2),
        flex: 1,
      },
      taskView:{
          padding: theme.spacing(1),
      },
      avatarColor: {
        color: '#fff',
        backgroundColor: `rgb(${[1,2,3].map(x=>Math.random()*256|0)})`,
      }
}))

export default function NewIntern(props)
{

    const classes = useStyles()
    const [expanded,setExpanded] = React.useState(false)
    const handleExpandClick = () => setExpanded(!expanded)
    const [intern,setIntern] = React.useState(props.intern)
    const [allTasks,setAllTasks] = React.useState([])
    const [selections,setSelections] = React.useState([])

    const onRemoveTask=(internId,assignmentId)=>{
        fetch('/api/interns/'+internId+"/remove_task"+'?taskIds='+assignmentId,{method:'DELETE'});
        const assignmentFilter = assignment=>assignment.id!==assignmentId;
        const newAssignments = intern.assignments.filter(assignmentFilter);
        setIntern({...intern,assignments:newAssignments});
    }

    const postAssignments=async (selections)=>
    {
       console.log(selections)
        if(selections){
             const response = await fetch('/api/interns/'+intern.id+"/assign_task"+'?taskIds='+selections.join(),{method:'POST'});
             const body = await response.json();
             console.log(body)
             setIntern({...intern,assignments:body.assignments})
        }
    }

    const onSelectTask = (choices)=>
    {
         if(choices)
            setSelections(choices.map(choice=>choice.value));
         else
             setSelections([])
         //console.log(this.state.selections);
    }

    React.useEffect(()=>
    {
      async function fetchCurrentTasks() {
        const response = await fetch('/api/tasks/');
        let allTasks = await response.json()
            
            //Create a Map of Tasks
        const taskMap = new Map();
        allTasks.forEach(task=>taskMap.set(task.id,task));
            
            //Remove assignments for current intern which are already assigned
        intern.assignments.forEach(assignment=>taskMap.delete(assignment.task.id))

            //Convert to desired format 
        allTasks = []
        //taskMap.forEach(task=>allTasks.push({value:task.id,label:task.taskName}))
        taskMap.forEach(task=>allTasks.push(task))
        setAllTasks(allTasks)
        console.log(allTasks)
   }
   fetchCurrentTasks()
  }, [])


    return(
        <React.Fragment key={props.intern.id}>
          <ListItem button onClick={handleExpandClick}>
          <ListItemAvatar>
            <Avatar className={classes.avatarColor}>{intern.name[0]}</Avatar>
          </ListItemAvatar>
          <ListItemText primary={props.intern.name}/>  
          </ListItem>
         <Divider />
        <Dialog fullScreen open={expanded} onClose={handleExpandClick} TransitionComponent={Transition}>
        <AppBar className={classes.appBar}>
        <Toolbar>
          <IconButton edge="start" color="inherit" onClick={handleExpandClick} aria-label="close">
            <CloseIcon />
          </IconButton>
          <Typography variant="h6" className={classes.title}>
            Intern Description
          </Typography>
        </Toolbar>
      </AppBar>
      <List>
        <ListItem button>
          <ListItemText primary={intern.name} secondary={`Rating is ${props.intern.rating}`} />
        </ListItem>
        <Divider />
        <ListItem button>
          <ListItemText primary={"Total Assignments alloted"} secondary={intern.assignments.length} />
        </ListItem>
        <ListItem>

            <List>
                {
                intern.assignments.length!==0?intern.assignments.map(
                    assignment=>
                    <React.Fragment key={assignment.id}>
                    <ListItem>
                        <ListItemText primary={assignment.task.taskName} secondary={assignment.status}/>
                    </ListItem>
                    <ListItem>
                      <Button color="primary" onClick={()=>onRemoveTask(intern.id,assignment.id)}>Remove Task</Button>
                    </ListItem>
                    </React.Fragment>
                ):<Typography>No Assignments Here </Typography>
                }
            </List>
        </ListItem>
        <Divider />
        <ListItem className={classes.taskView}>
            <ListItemText primary={"Add new assignment"} />
        </ListItem>
        <ListItem component="nav">
          <TaskSelector assigned={props.intern.assignments.map(assignment=>assignment.task)} unassigned={allTasks} onPostAssignments={postAssignments} />
        </ListItem>
        </List>
        </Dialog>
        </React.Fragment>
    )
}