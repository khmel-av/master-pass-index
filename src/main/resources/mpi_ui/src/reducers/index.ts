import { combineReducers } from 'redux';
import {IPassCard} from "../bisness-components/card/all_card/model/card.model";
import {cardReducer} from "../bisness-components/card/all_card/reducer/card.reducer";
import {authReducer} from "../bisness-components/auth/reducer/auth.reducer";
import {IUser} from "../bisness-components/user/model/user.model";
import {userReducer} from "../bisness-components/user/reducer/user.reducer";
import {homeReducer} from "../bisness-components/home/reducer/home.reducer";
import {ITypeFeedback} from "../bisness-components/feedback/model/type.feedback.modal";
import {typeFeedbackReducer} from "../bisness-components/feedback/reducer/type.feedback.reducer";

export interface IAuthState {
  currentUser: IUser
}

export interface IUserState {
  user: IUser
}

export interface IHomeState {
  cardByNumber: IPassCard
}

export interface IPassCardState {
  cards: IPassCard[]
}

export interface ITypeFeedbackState {
  typeFeedbacks: ITypeFeedback[]
}

export interface IState {
  auth: IAuthState,
  user: IUserState,
  cards: IPassCardState,
  home: IHomeState,
  typeFeedbacks: ITypeFeedbackState
}

export const state = combineReducers<IState>({
  auth: authReducer,
  user: userReducer,
  cards: cardReducer,
  home: homeReducer,
  typeFeedbacks: typeFeedbackReducer
});