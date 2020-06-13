import * as React from 'react';
import {IPassCard} from "../../model/card.model";

export interface IPassCardInternalComponentProps {
    cards: IPassCard[];
}

export class PassCardInternalComponenet extends React.Component<IPassCardInternalComponentProps, any> {
    render() {
        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        Все пропуска
                    </h3>
                </header>
                {
                    this.props.cards !== null && this.props.cards.length > 0
                        ?
                        <>
                            <div>
                                {
                                    this.props.cards.map((card) =>
                                        <div key={card.id} className="container">
                                            <p>
                                                <strong>Номер:</strong>{" "}
                                                {card.number}
                                            </p>
                                            <p>
                                                <strong>Причина для выдачи пропуска:</strong>{" "}
                                                <ul>
                                                    {
                                                        card.reasons.map((reason) =>
                                                            <li key={reason.id}>
                                                                {reason.name}
                                                            </li>)
                                                    }
                                                </ul>
                                            </p>
                                            <p>
                                                <strong>Область передвижения:</strong>{" "}
                                                <ul>
                                                    {
                                                        card.scopes.map((scope) =>
                                                            <li key={scope.id}>
                                                                <div>
                                                                <span>
                                                                    <strong>Страна:</strong>{" "}
                                                                    {scope.country.name}
                                                                </span>
                                                                    <br/>
                                                                    <span>
                                                                    <strong>Область:</strong>{" "}
                                                                        {scope.region.name}
                                                                </span>
                                                                    <br/>
                                                                    {
                                                                        scope.district !== null
                                                                            ?
                                                                            <div>
                                                                            <span>
                                                                                <strong>Район:</strong>{" "}
                                                                                {scope.district.name}
                                                                            </span>
                                                                                <br/>
                                                                            </div>
                                                                            :
                                                                            <span></span>
                                                                    }
                                                                    <span>
                                                                    <strong>Населенный пункт:</strong>{" "}
                                                                        {scope.locality.typeLocality.name}
                                                                        {" "}
                                                                        {scope.locality.name}
                                                                </span>
                                                                </div>
                                                            </li>)
                                                    }
                                                </ul>
                                            </p>
                                            <p>
                                                <strong>Вид передвижения:</strong>{" "}
                                                <ul>
                                                    {
                                                        card.types.map((type) =>
                                                            <li key={type.id}>
                                                                {type.name}
                                                            </li>)
                                                    }
                                                </ul>
                                            </p>
                                            <p>
                                                <strong>Статус:</strong>{" "}
                                                {card.status.name}
                                            </p>
                                            <p>
                                                <strong>Дата и время начала действия:</strong>{" "}
                                                {card.startDate}
                                            </p>
                                            <p>
                                                <strong>Дата и время окончания действия:</strong>{" "}
                                                {card.expirationDate}
                                            </p>
                                            <p>
                                                <strong>Дата и время оформления:</strong>{" "}
                                                {card.createDate}
                                            </p>
                                            <hr/>
                                            <br/>
                                        </div>
                                    )
                                }
                            </div>
                        </>
                        :
                        <>
                            <div className="container">
                                <strong>Пропуска не найдены</strong>
                            </div>
                        </>
                }
            </div>
        )
    }
}