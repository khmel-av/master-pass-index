import {IUser} from "../../user/model/user.model";
import {IAuth} from "../model/auth.model";
import {authClient} from "../axios/client/auth.client";
import {client} from "../../../axios/client";

export const authTypes = {
    LOGIN: '[AUTH] LOGIN',
    LOGOUT: '[AUTH] LOGOUT',
    UPDATE_CURRENT_USER: '[AUTH] UPDATE_CURRENT_USER',
};

export const updateCurrentUser = (currentUser: IUser) => {
    return {
        payload: {
            currentUser
        },
        type: authTypes.UPDATE_CURRENT_USER
    }
};

export const setup = (username: string, password: string) => async (dispatch) => {
    try {
        if (username !== null && username !== '' &&
            password !== null && password !== '') {

            const auth: IAuth = {
                login: username,
                password: password

            }

            // const responseAuth = await authClient.login(auth);
            // if (responseAuth.data) {
            //     client.interceptors.request.use((config)  => {
            //         config.headers.Authorization = `Bearer ` + responseAuth.data;
            //         return config;
            //     });
            // }
            // const response = await authClient.currentUser();
            const response = await authClient.login(auth);
            dispatch({
                payload: {
                    currentUser: response.data
                },
                type: authTypes.UPDATE_CURRENT_USER
            });
        };
    } catch (err) {
        console.log('error autentication');
        dispatch({
            payload: {
            },
            type: ''
        })
    }
};

export const logout = () => {
    return {
        payload: {},
        type: authTypes.LOGOUT
    }
};
