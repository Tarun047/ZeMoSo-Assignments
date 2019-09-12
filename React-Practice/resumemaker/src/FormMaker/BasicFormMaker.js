import React,{useState} from 'react'
import { TextField } from '@material-ui/core'
export default function BasicForm(props)
{
    [state,setState] = useState({})
    function handleChange(event)
    {
        setState({...state,[event.target.id]:event.target.value})
    }

    return (
        <form>
           {
               props.fields.map(
                   fieldEntry=>
                    <TextField 
                        id={fieldEntry.name}
                        name={filedEntry.name}
                        value={`state.${fieldEntry.name}`}
                        margin="normal"
                        variant="outlined" 
                    />
               )
           }
        </form>
    )
}