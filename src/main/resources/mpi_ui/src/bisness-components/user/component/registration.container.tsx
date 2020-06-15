import { connect } from 'react-redux';
import {RouteComponentProps} from "react-router";
import {ICountryState, IState} from "../../../reducers";
import {RegistrationComponent} from "./registration.component";
import {getCountryList} from "../../address/action/country.action";
import {saveUser} from "../actions/user.actions";
import {ComboboxMessages} from "react-widgets/lib/Combobox";

export interface IComponentState {
    firstName: string,
    lastName: string,
    middleName: string,
    birth: string,
    email: string,
    username: string,
    password: string,
    countryId: number,
    regionId: number,
    districtId: number,
    localityId: number,
    streetId: number,
    home: string,
    build: string,
    flat: string,
    room: string,
    zipcode: string,
    countryError: ComboboxMessages
}

export interface IComponentProps extends RouteComponentProps<{}> {
    countries: ICountryState,
    getCountryList(countryName:string),
    saveUser
}

const mapStateToProps = (state: IState) => ({
    countries: state.countries,
    user: state.user
});
const mapDispatchToProps = {
    getCountryList,
    saveUser
};

export default connect(mapStateToProps, mapDispatchToProps)(RegistrationComponent);