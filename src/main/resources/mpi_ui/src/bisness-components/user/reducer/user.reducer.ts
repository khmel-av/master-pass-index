import {IUserState} from "../../../reducers";
import {userTypes} from "../actions/user.actions";
import {authTypes} from "../../auth/actions/auth.actions";

const initialState: IUserState = {
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
    }
};

export const userReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case userTypes.CURRENT:
            return {
                ...state,
                user: action.payload.user
            };
        case authTypes.LOGOUT:
            return initialState;
    }
    return state;
};
