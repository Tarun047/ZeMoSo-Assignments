import React,{useState} from 'react'
import { TextField } from '@material-ui/core'
export default function BasicForm(props)
{
    const [state,setState] = useState({})
    function handleChange(event)
    {
        setState({...state,[event.target.id]:event.target.value})
    }

    return (
        <form>
           {
              Object.entries(props.fields).map(
                  ([key,value])=>
                    <TextField 
                        id={key}
                        label={key}
                        value={state[key]}
                        onChange={handleChange}
                        margin="normal"
                        variant="outlined"
                    />
              )
           }
        </form>
    )
}