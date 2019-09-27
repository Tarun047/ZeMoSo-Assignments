import React,{useState} from 'react'
import {makeStyles} from '@material-ui/core/styles'
import {Card,CardContent,Typography, CardActions,IconButton,Collapse,FormControl,Select,MenuItem,InputLabel} from '@material-ui/core'
import ExpandMoreIcon from '@material-ui/icons/ExpandMore'
import clsx from 'clsx'
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
    title: {
      fontSize: 14,
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
    }
  }));
export default function Task(props)
{
    const [expanded,setExpanded] = useState(false);
    const classes = useStyles();
    const handleExpandClick = () => {
        setExpanded(!expanded);
      };
    return(
        <Card className={classes.card}>
            <CardContent>
                <Typography className={classes.title}>
                    {props.title}
                </Typography>
                <Typography className={classes.title}>Deadline: {props.deadline}</Typography>
            </CardContent>
            <CardActions>
                <Typography className={classes.bullet}>
                    <FormControl className={classes.formControl}>
                          <InputLabel>Status</InputLabel>
                            <Select value={props.status} onChange={(event)=>props.onChange(props.key,event.target.value)}>
                            <MenuItem value={'OPEN'}>Open</MenuItem>
                            <MenuItem value={'IN_PROGRESS'}>In Progress</MenuItem>
                            <MenuItem value={'CLOSED'}>Closed</MenuItem>
                          </Select>
                    </FormControl>
                </Typography>
                <IconButton
                    className={clsx(classes.expand, {
                        [classes.expandOpen]: expanded,
                      })}
                    onClick={handleExpandClick}
                    aria-expanded={expanded}
                    aria-label="show more">
                    <ExpandMoreIcon />
                </IconButton>
            </CardActions>
            <Collapse in={expanded} timeout="auto" unmountOnExit>
                <CardContent>
                    <Typography className={classes.content}>Description: {props.description}</Typography>
                </CardContent>
            </Collapse>
        </Card>
    );
}