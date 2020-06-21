import {IAuth} from "../../model/auth.model";
import {AxiosPromise} from "axios";
import {client} from "../../../../axios/client";

const authContext = '/user';
const urls = {
  token: '/public/token',
  auth: authContext + '/public/auth',
  currentUser: '/user/current'
};

export const authClient = {
  getToken(auth: IAuth): AxiosPromise<string> {
    return client.post(urls.token, auth);
  },

  login(auth: IAuth): AxiosPromise<IAuth> {
    return client.post(urls.auth, auth);
  },

  currentUser(): AxiosPromise<void> {
    return client.get(urls.currentUser);
  }
};
