import {IHomeState} from "../../../reducers";
import {cardTypes} from "../../card/all_card/actions/card.actions";
import {authTypes} from "../../auth/actions/auth.actions";

const initialState: IHomeState = {
    cardByNumber: {
        id: 0,
        number: '',
        reasons: [],
        scopes: [],
        types: [],
        status: {
            name: ''
        },
        startDate: '',
        expirationDate: '',
        user: {
            id: 0,
            firstName: '',
            lastName: '',
            middleName: '',
            shortName: '',
            birth: '',
            email: '',
            photoPath: '',
            address: null,
            roles: [],
            createDate: ''
        },
        createDate: '',
        description: ''
    }
};

export const homeReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case cardTypes.CARD_BY_NUMBER:
            return {
                ...state,
                cardByNumber: action.payload.cardByNumber
            };
        case authTypes.LOGOUT:
            return initialState;
    }
    return state;
};
