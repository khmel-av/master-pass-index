import {countryClient} from "../axios/client/country.client";

export const countryTypes = {
    COUNTRY_LIST: '[COUNTRY] COUNTRY_LIST',
    ERROR: '[COUNTRY] ERROR'
};

export const getCountryList = (countryName:string) => async (dispatch: any) => {
    try {
        const response = await countryClient.getCountryList(countryName);
        dispatch({
            payload: {
                countries: response.data
            },
            type: countryTypes.COUNTRY_LIST
        })
    } catch (err) {
        console.log('error getting list type feedback');
        dispatch({
            payload: {
                countryError: err.data
            },
            type: countryTypes.ERROR
        })
    }
};