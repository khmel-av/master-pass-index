import {IAddress} from "../../address/model/address.model";

export interface IUser {
    id: number;
    firstName: string;
    lastName: string;
    middleName?: string;
    shortName?: string;
    birth: string;
    email: string;
    photoPath?: any;
    address: IAddress|null;
    login?: string;
    password?: string;
    roles: string[];
    createDate?: string;
}
