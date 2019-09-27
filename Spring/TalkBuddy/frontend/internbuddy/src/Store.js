import {createStore} from 'redux'
import rootReducer from './reducers/rootReducer.js'

const Store = createStore(rootReducer);

export default Store;