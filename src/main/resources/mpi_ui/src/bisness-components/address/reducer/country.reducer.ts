import {ICountryState} from "../../../reducers";
import {countryTypes} from "../action/country.action";

const initialState: ICountryState = {
    countries: []
};

export const countryReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case countryTypes.COUNTRY_LIST:
            return {
                ...state,
                countries: action.payload.countries
            };
        case countryTypes.ERROR:
            return {
                ...state,
                countryError: action.payload.countryError
            };
    }
    return state;
};
