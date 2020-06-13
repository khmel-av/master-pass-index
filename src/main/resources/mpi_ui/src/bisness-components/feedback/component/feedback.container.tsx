import { connect } from 'react-redux';
import {RouteComponentProps} from "react-router";
import {IAuthState, IState, ITypeFeedbackState} from "../../../reducers";
import {IFeedback} from "../model/feedback.modal";
import {sendMessage} from "../action/feedback.action";
import {FeedbackComponent} from "./feedback.component";
import {getTypes} from "../action/type.feedback.action";

export interface IComponentState {
    email: string,
    typeId: number,
    message: string,
}

export interface IComponentProps extends RouteComponentProps<{}>{
    getTypes(),
    typeFeedbacks: ITypeFeedbackState
    auth: IAuthState,
    sendMessage(feedback: IFeedback);
}

const mapStateToProps = (state: IState) => ({
    typeFeedbacks: state.typeFeedbacks,
    auth: state.auth
});
const mapDispatchToProps = {
    getTypes,
    sendMessage
};

export default connect(mapStateToProps, mapDispatchToProps)(FeedbackComponent);