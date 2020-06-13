import * as React from 'react';
import {IComponentProps} from "./check.card.container";
import {CheckCardInternalComponenet} from "./internal/internal.component";

export class CheckCardComponent extends React.PureComponent<IComponentProps, {}, {}> {
    public render() {
        return (
            <div>
                {
                    <CheckCardInternalComponenet home ={this.props.home} auth={this.props.auth}/>
                }
            </div>
        );
    }
}