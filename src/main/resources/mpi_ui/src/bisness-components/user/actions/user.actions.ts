import {userClient} from "../axios/client/user.client";
import {IUser} from "../model/user.model";
import {authTypes} from "../../auth/actions/auth.actions";

export const userTypes = {
    CURRENT: '[USER] CURRENT',
    GET_LIST: '[USER] GET_LIST',
    SAVE_USER: '[USER] SAVE_USER',
};

export const current = (token: string) => async (dispatch) => {
    try {
        const response = await userClient.current(token);
        dispatch({
                payload: {
                    user: response.data
                },
                type: userTypes.CURRENT
            })
    } catch (err) {
        console.log('error get curren user');
        dispatch({
            payload: {
            },
            type: ''
        })
    }
};

export const saveUser = (user: IUser) => async (dispatch: any) => {
    try {
        const response = await userClient.saveUser(user);
        dispatch({
            payload: {
                currentUser: response.data
            },
            type: authTypes.UPDATE_CURRENT_USER
        })
    } catch (err) {
        console.log('error save the user');
        dispatch({
            payload: {},
            type: ''
        })
    }

};
