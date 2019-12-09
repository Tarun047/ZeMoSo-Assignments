import {combineReducers} from 'redux'

import assignment from './assignmentReducer'
import mentor from './mentorReducer'
const rootReducer = combineReducers(
    {
        assignment,
        mentor
    }
);
export default rootReducer