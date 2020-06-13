import {ICountry} from "../../../address/model/country.model";
import {IRegion} from "../../../address/model/region.model";
import {IDistrict} from "../../../address/model/district.model";
import {ILocality} from "../../../address/model/locality.model";

export interface IScopeCard {
    id: number;
    country: ICountry;
    region: IRegion;
    district: IDistrict;
    locality: ILocality;
    description?: string;
}