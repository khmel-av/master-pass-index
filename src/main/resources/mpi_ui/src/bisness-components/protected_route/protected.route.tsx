import * as React from 'react';

import { Route } from 'react-router';
import { connect } from 'react-redux';
import {IState} from "../../reducers";
import LoginComponent from "../auth/component/login.component";

const mapStateToProps = (state: IState) => ({ auth:state.auth});

export const ProtectedRoute = ({component: Component, auth, allowedRoles, ...rest }: any) => {
    return (
        <Route
            {...rest}
            render={props => {
                if (allowedRoles.some(allowedRole => auth.currentUser.roles.includes(allowedRole))) {
                    return <Component {...props} />;
                } else {
                    return (
                        <LoginComponent {...props}/>
                    );
                }
            }}
        />
    );
}

export default connect(mapStateToProps)(ProtectedRoute);