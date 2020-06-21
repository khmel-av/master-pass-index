import {IAuthState} from "../../../reducers";
import {authTypes} from "../actions/auth.actions";

const initialState: IAuthState = {
  currentUser: {
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

export const authReducer = (state = initialState, action: any) => {
  switch (action.type) {
    case authTypes.LOGIN:
      return {
        ...state,
        auth: action.payload.auth
      };
    case authTypes.UPDATE_CURRENT_USER:
      return {
        ...state,
        currentUser: action.payload.currentUser
      };
    case authTypes.LOGOUT:
      return initialState;
  }
  return state;
};
