import {AxiosPromise} from "axios";
import {client} from "../../../../axios/client";
import {IUser} from "../../model/user.model";

const userContext = '/user';
const urls = {
    current: userContext + '/current',
    saveUser: userContext + '/add'
};

export const userClient = {
    current(token: string): AxiosPromise<string> {
        return client.get(urls.current);
    },
    saveUser(user: IUser): AxiosPromise<IUser> {
        return client.post(urls.saveUser, user);
    }
};
