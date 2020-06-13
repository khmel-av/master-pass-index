import {IAuth} from "../../model/auth.model";
import {AxiosPromise} from "axios";
import {client} from "../../../../axios/client";

const authContext = '/user';
const urls = {
    auth: authContext + '/public/auth',
    currentUser: authContext + '/current'
};

export const authClient = {
    login(auth: IAuth): AxiosPromise<IAuth> {
        return client.post(urls.auth, auth);
    },

    currentUser(): AxiosPromise<void> {
        return client.get(urls.currentUser);
    }
};
