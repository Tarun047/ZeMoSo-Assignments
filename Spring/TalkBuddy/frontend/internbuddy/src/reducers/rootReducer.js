import {combineReducers} from 'redux'

import assignment from './assignmentReducer'
const rootReducer = combineReducers(
    {
        assignment
    }
);
export default rootReducer