const initialState = {
    interns:[],
    currentScreen:'MAIN_WINDOW',
    user:{},
    isLoading:true,
}

export default (state=initialState,action)=>{
    switch(action.type){
        case 'UPDATE_INTERN_DATA':{
            console.log(action.payload)
            return{
                ...state,
                interns:action.payload
            }
        }
        case 'SWITCH_SCREEN':
            return{
                ...state,
                currentScreen:action.payload
            }
        case 'CHANGE_LOADING_STATUS':
            return{
                ...state,
                isLoading:!state.isLoading
            }
        case 'SET_USER':
            return {
                ...state,
                user:action.payload
            }
        default:
            return {
                ...state
            }
    }
}