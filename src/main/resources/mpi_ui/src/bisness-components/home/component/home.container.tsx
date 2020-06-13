import { connect } from "react-redux";
import {IState} from "../../../reducers";
import { HomeComponent } from "./home.component";
import {getCardByNumber} from "../../card/all_card/actions/card.actions";
import {RouteComponentProps} from "react-router";

export interface IHomeComponentState {
    number: string,
}

export interface IHomeComponentProps extends RouteComponentProps<{}>{
    getCardByNumber,
}

const mapStateToProps = (state: IState) => (state.home);

const mapDispatchToProps = {
    getCardByNumber
};

export const HomeContainer = connect(mapStateToProps, mapDispatchToProps)(HomeComponent);