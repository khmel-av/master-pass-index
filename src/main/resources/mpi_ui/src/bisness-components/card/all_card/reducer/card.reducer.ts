import { IPassCardState } from '../../../../reducers';
import {cardTypes} from "../actions/card.actions";
import {authTypes} from "../../../auth/actions/auth.actions";

const initialState: IPassCardState = {
    cards: []
};

export const cardReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case cardTypes.CARD_LIST:
            return {
                ...state,
                cards: action.payload.cards
            };
        case cardTypes.FILTER_CARDS:
            return {
                ...state,
                cards: action.payload.cards
            };
        case cardTypes.DELETE_CARD:
            return initialState;
        case cardTypes.UPDATE_CARD:
            return initialState;
        case cardTypes.SAVE_CARD:
            return initialState;
        case authTypes.LOGOUT:
            return initialState;
    }

    return state;
};
