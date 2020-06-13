import * as React from 'react';
import {IPassCardComponentProps} from "./card.container";
import {PassCardInternalComponenet} from "./internal/internal.component";

export class PassCardListComponent extends React.Component<IPassCardComponentProps, any> {
    componentDidMount() {
        this.props.cardList(this.props.auth.currentUser.id);
    }

    public render() {
        return (
            <div>
                {
                    <PassCardInternalComponenet cards={this.props.cards.cards}/>
                }
            </div>
        );
    }
}