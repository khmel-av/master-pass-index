import {ITypeLocality} from "./type.locality.model";

export interface ILocality {
    id: number;
    name: string;
    typeLocality: ITypeLocality
    description?: string;
    regionId?: number;
    districtId?: number;
}