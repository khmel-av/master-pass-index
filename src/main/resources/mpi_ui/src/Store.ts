import {Store, createStore, compose, applyMiddleware} from 'redux';
import reduxThunk from 'redux-thunk';
import logger from 'redux-logger';
import {IAuthState, IHomeState, IPassCardState, ITypeFeedbackState, IUserState, state} from './reducers';

const a: any = window;
const composeEnhancers = a.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

export interface IStore {
    auth?: IAuthState,
    user?: IUserState,
    home?: IHomeState,
    card?: IPassCardState,
    typeFeedback?: ITypeFeedbackState,
}

const enhancer = composeEnhancers(
    applyMiddleware(reduxThunk, logger)
);

export const store: Store<any> = createStore(
    state,
    enhancer
);