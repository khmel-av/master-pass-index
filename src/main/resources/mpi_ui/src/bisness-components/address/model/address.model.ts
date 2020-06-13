import {ICountry} from "./country.model";
import {IRegion} from "./region.model";
import {IDistrict} from "./district.model";
import {ILocality} from "./locality.model";
import {IStreet} from "./street.model";

export interface IAddress {
    id: number;
    country: ICountry;
    region: IRegion;
    district: IDistrict|null;
    locality: ILocality;
    street: IStreet;
    home: string;
    build: string;
    flat: string;
    room: string;
    zipcode: string;
    description?: string;
}