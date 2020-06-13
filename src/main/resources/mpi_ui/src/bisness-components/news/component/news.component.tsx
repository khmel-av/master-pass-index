import * as React from 'react';
import {RouteComponentProps, withRouter} from "react-router";
import {IState} from "../../../reducers";
import {connect} from "react-redux";

export interface IProps extends RouteComponentProps<{}>{
}

export class NewsComponent extends React.Component<IProps, {}> {
    public render() {
        return(
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        Новости
                    </h3>
                </header>
                <div>

                </div>
            </div>
        );
    }
}

const mapStateToProps = (state: IState) => ({
});

export default withRouter(connect(mapStateToProps)(NewsComponent)) as any;