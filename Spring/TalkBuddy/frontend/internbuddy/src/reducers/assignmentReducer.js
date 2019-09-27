const initialState = {
    taskList:[],
    showList:[],
    searchTerm: ""
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
            return {
                ...state,
                showList:state.taskList.filter(value=>value.task.taskName.toLowerCase().includes(action.payload)),
                searchTerm: action.payload
            };
        }
        case "UPDATE_ASSIGNMENT_STATUS":
            {
                return{
                    ...state,
                    taskList: [...state.taskList.slice(0,action.payload.id),{...state.taskList[action.payload.id],status:action.payload.status},...state.taskList.slice(action.payload.id+1)],
                    showList: state.taskList
                }
            }
        default:{
            return {...state};
        }
    }
}