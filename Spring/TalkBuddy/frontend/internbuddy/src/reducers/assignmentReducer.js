const initialState = {
    taskList:[],
    filters:{},
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
            const new_filters = {...state.filters,search:value=>value.task.taskName.toLowerCase().includes(action.payload)}
            return {
                ...state,
                filters:new_filters,
                searchTerm: action.payload
            };
        }
        case "CHANGE_VISIBILITY_FILTER":
            {
                const new_filters = {...state.filters,visibility:value=>action.payload==='ALL' || value.status===action.payload}
                return {
                    ...state,
                    visibility:action.payload,
                    filters:new_filters
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
                console.log(updatedList[action.payload.id])
                return{
                    ...state,
                    taskList: updatedList
                }
            }
        default:{
            return {...state};
        }
    }
}