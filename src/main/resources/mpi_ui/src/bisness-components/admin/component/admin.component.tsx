import * as React from 'react';
import {RouteComponentProps, withRouter} from "react-router";
import {IState} from "../../../reducers";
import {connect} from "react-redux";

export interface IProps extends RouteComponentProps<{}>{
}

export class AdminComponent extends React.Component<IProps, {}> {
    public render() {
        return(
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        Администрирование
                    </h3>
                </header>
            </div>
        );
    }
}

const mapStateToProps = (state: IState) => ({
});

export default withRouter(connect(mapStateToProps)(AdminComponent)) as any;