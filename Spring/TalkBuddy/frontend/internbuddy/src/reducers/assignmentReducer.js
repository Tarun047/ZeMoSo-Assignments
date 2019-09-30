const initialState = {
    taskList:[],
    showList:[],
    searchTerm: "",
    visibility:'ALL'
}

export default (state = initialState, action) =>
{
    switch(action.type)
    {
        case "UPDATE_DATA":{
            return {
                ...state,
                showList:action.payload,
                taskList:action.payload,
            };
        }
        case "CHANGE_SEARCH":{
            if(state.visibility==='ALL')
            {
                return {
                    ...state,
                    showList:state.taskList.filter(value=>value.task.taskName.toLowerCase().includes(action.payload)),
                    searchTerm: action.payload
                };
            }
            else
            {
                return {
                    ...state,
                    showList:state.taskList.filter(value=>value.status==state.visibility && value.task.taskName.toLowerCase().includes(action.payload)),
                    searchTerm: action.payload
                }
            }
            
        }
        case "CHANGE_VISIBILITY_FILTER":
            {
                if(action.payload==='ALL')
                    return{
                        ...state,
                        showList:state.taskList,
                        visibility:action.payload
                    }
                return {
                    ...state,
                    visibility:action.payload,
                    showList:state.taskList.filter(value=>value.status===action.payload)
                }
            }
        case "UPDATE_ASSIGNMENT_STATUS":
            {
                const updatedList = [...state.taskList.slice(0,action.payload.id),
                    {
                     id:state.taskList[action.payload.id].id,
                     status:action.payload.status,
                     task:state.taskList[action.payload.id].task
                    }
                    ,...state.taskList.slice(action.payload.id+1)]
                return{
                    ...state,
                    taskList: updatedList,
                    showList: updatedList
                }
            }
        default:{
            return {...state};
        }
    }
}