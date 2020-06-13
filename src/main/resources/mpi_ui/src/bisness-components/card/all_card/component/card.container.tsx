import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router';
import { PassCardListComponent } from "./card.component";
import {IAuthState, IPassCardState, IState} from "../../../../reducers";
import {cardList} from "../actions/card.actions";

export interface IPassCardComponentProps extends RouteComponentProps<{}>{
    cardList(userId:number),
    cards: IPassCardState,
    auth: IAuthState
}

const mapStateToProps = (state:IState) => ({
    auth: state.auth,
    cards: state.cards
});

const mapDispatchToProps = {
    cardList
}
export default connect(mapStateToProps, mapDispatchToProps)(PassCardListComponent);