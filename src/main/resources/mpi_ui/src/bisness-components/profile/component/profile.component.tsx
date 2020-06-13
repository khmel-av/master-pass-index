import * as React from 'react';
import {RouteComponentProps, withRouter} from "react-router";
import {IAuthState, IState} from "../../../reducers";
import {connect} from "react-redux";

export interface IProps extends RouteComponentProps<{}>{
    auth: IAuthState;
}

export class ProfileComponent extends React.Component<IProps, {}> {
    public render() {
        const { auth } = this.props;

        return(
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        Профиль
                    </h3>
                </header>
                <p>
                    <strong>Имя:</strong>{" "}
                    {auth.currentUser.firstName}
                </p>
                <p>
                    <strong>Фамилия:</strong>{" "}
                    {auth.currentUser.lastName}
                </p>
                <p>
                    <strong>Отчество:</strong>{" "}
                    {auth.currentUser.middleName}
                </p>
                <p>
                    <strong>Дата рождения:</strong>{" "}
                    {auth.currentUser.birth}
                </p>
                <p>
                    <strong>Email:</strong>{" "}
                    {auth.currentUser.email}
                </p>
                <p>
                    <strong>Дата регистрации:</strong>{" "}
                    {auth.currentUser.createDate}
                </p>
            </div>
        );
    }
}

const mapStateToProps = (state: IState) => ({
    auth: state.auth,
});

export default withRouter(connect(mapStateToProps)(ProfileComponent)) as any;