import { connect } from 'react-redux';
import {RouteComponentProps} from "react-router";
import {CheckCardComponent} from "./check.card.component";
import {IAuthState, IHomeState, IState} from "../../../../reducers";

export interface IComponentProps extends RouteComponentProps<{}>{
    home: IHomeState;
    auth: IAuthState;
}

const mapStateToProps = (state: IState) => ({
    home: state.home,
    auth: state.auth
});
const mapDispatchToProps = {};

export default connect(mapStateToProps, mapDispatchToProps)(CheckCardComponent);