import React,{useState} from 'react'
import {makeStyles} from '@material-ui/core/styles'
import {Card,CardContent,Typography, ButtonBase,IconButton,Dialog,List,ListItem,ListItemText,Divider,Toolbar,AppBar,Slide,FormControl,Select,MenuItem,InputLabel, Button} from '@material-ui/core'
import CloseIcon from '@material-ui/icons/CloseRounded'
const useStyles = makeStyles((theme)=>({
    card: {
        width:250,
        display: 'block',
        margin:8,
        transitionDuration: '0.3s',
    },
    bullet: {
      display: 'inline-block',
      margin: '0 2px',
      transform: 'scale(0.8)',
    },
    content:{
        display:'block',
        overflow:'hidden'
    },
    pos: {
      marginBottom: 12,
    },
    expand: {
        transform: 'rotate(0deg)',
        marginLeft: 'auto',
        transition: theme.transitions.create('transform', {
          duration: theme.transitions.duration.shortest,
        }),
      },
    expandOpen: {
        transform: 'rotate(180deg)',
    },
    formControl: {
      margin: theme.spacing(1),
      minWidth: 120,
    },
    appBar: {
      position: 'relative',
    },
    title: {
      marginLeft: theme.spacing(2),
      flex: 1,
    }
  }));
const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
  });
export default function Task(props)
{
    const [expanded,setExpanded] = useState(false);
    const [status,setStatus] = useState(props.status)
    const classes = useStyles();
    const handleExpandClick = () => {
        setExpanded(!expanded);
      };
    return(
        <React.Fragment>
        <Card className={classes.card} onClick={handleExpandClick}>
            <ButtonBase>
            <CardContent>
                <Typography className={classes.title}>
                    {props.title}
                </Typography>
                <Typography className={classes.title}>Deadline: {props.deadline}</Typography>
                <Typography className={classes.title}>Status: {props.status}</Typography>
            </CardContent>
            </ButtonBase>
        </Card>
        <Dialog fullScreen open={expanded} onClose={handleExpandClick} TransitionComponent={Transition}>
        <AppBar className={classes.appBar}>
        <Toolbar>
          <IconButton edge="start" color="inherit" onClick={handleExpandClick} aria-label="close">
            <CloseIcon />
          </IconButton>
          <Typography variant="h6" className={classes.title}>
            Task Description
          </Typography>
          <Button autoFocus color="inherit" onClick={()=>{
            props.onChange(props.id,status);
            props.onAssignmentChange(props.id,status);
            handleExpandClick()}}>
          save
          </Button>
        </Toolbar>
      </AppBar>
      <List>
        <ListItem button>
          <ListItemText primary={props.title} secondary={props.deadline} />
        </ListItem>
        <Divider />
        <ListItem>
              <FormControl className={classes.formControl}>
                          <InputLabel>Status</InputLabel>
                            <Select value={status} onChange={
                              (event)=>setStatus(event.target.value)}>
                            <MenuItem value={'OPEN'}>Open</MenuItem>
                            <MenuItem value={'IN_PROGRESS'}>In Progress</MenuItem>
                            <MenuItem value={'CLOSED'}>Closed</MenuItem>
                          </Select>
              </FormControl>
        </ListItem>
        <Divider />
        <ListItem button>
        <ListItemText primary={props.description} />
      </ListItem>
      </List>
    </Dialog>
    </React.Fragment>
    );
}