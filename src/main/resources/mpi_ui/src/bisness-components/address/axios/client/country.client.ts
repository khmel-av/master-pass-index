import {AxiosPromise} from "axios";
import {client} from "../../../../axios/client";
import {ICountry} from "../../model/country.model";

// const countryContext = 'country';
const urls = {
    countryList: '/country/public/list',
};

export const countryClient = {
    getCountryList(countryName:string): AxiosPromise<ICountry[]> {
        return client.get(urls.countryList + `/${countryName}/`);
    }
};
