import { makeStyles } from '@material-ui/core/styles'
import React, { useState } from 'react'

const useStyles = makeStyles(
    theme=>(
      {
        root:
        {
          display:'flex',
          flexWrap:'wrap',
          flexDirection:'column',
          alignItems:'center',
        },
      })
  )

export default function BasicForm(props)
{
    const classes = useStyles()
    const [state,setState] = useState({})
    function handleChange(event)
    {
        setState({...state,[event.target.id]:event.target.value})
    }

    return (
        <form className={classes.root}>
           {
              Object.entries(props.fields).map(
                  ([key,value])=>
                    React.createElement(value,{id:key,
                        label:key,
                        value:state[key],
                        onChange:handleChange,
                        margin:"normal",
                        variant:"outlined"})
              )
           }
        </form>
    )
}

