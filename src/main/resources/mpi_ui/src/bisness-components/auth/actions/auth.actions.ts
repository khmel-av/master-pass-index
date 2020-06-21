import {IAuth} from "../model/auth.model";
import {authClient} from "../axios/client/auth.client";
import {client} from "../../../axios/client";

export const authTypes = {
  GET_TOKEN: '[AUTH] GET_TOKEN',
  LOGIN: '[AUTH] LOGIN',
  LOGOUT: '[AUTH] LOGOUT',
  UPDATE_CURRENT_USER: '[AUTH] UPDATE_CURRENT_USER',
};

const updateCurrentUser = () => async (dispatch) => {
  try {
    const response = await authClient.currentUser();
    dispatch({
      payload: {
        currentUser: response.data
      },
      type: authTypes.UPDATE_CURRENT_USER
    })
  } catch (err) {
    console.log('error getting current user');
    dispatch({
      payload: {},
      type: ''
    })
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
      const responseAuth = await authClient.getToken(auth);
      if (responseAuth.data) {
        client.interceptors.request.use((config)  => {
          config.headers.Authorization = `Bearer ` + responseAuth.data;
          return config;
        });
      }
      dispatch(updateCurrentUser());
    }
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
