import * as React from 'react';

import {IAuthState, IHomeState} from "../../../../../reducers";

interface IPassCardInternalComponentProps {
    home: IHomeState;
    auth: IAuthState;
}

export class CheckCardInternalComponenet extends React.Component<IPassCardInternalComponentProps, {}> {
    public render() {
        const props = this.props;
        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        Пропуск №{" "}{props.home.cardByNumber.number}
                    </h3>
                </header>
                {
                    this.props.auth.currentUser.roles.some(role => role === 'admin') &&
                    <>
                        <p>
                            <strong>Владелец:</strong>{" "}
                            <ul>
                                <li>
                                    <div>
                                 <span>
                                     <strong>Имя:</strong>{" "}
                                     {props.home.cardByNumber.user.firstName}
                                </span>
                                        <br />
                                        <span>
                                    <strong>Фамилия:</strong>{" "}
                                            {props.home.cardByNumber.user.lastName}
                                </span>
                                        <br />
                                        <span>
                                    <strong>Отчество:</strong>{" "}
                                            {props.home.cardByNumber.user.middleName}
                                </span>
                                        <br />
                                        <span>
                                    <strong>Дата рождения:</strong>{" "}
                                            {props.home.cardByNumber.user.birth}
                                </span>
                                        <br />
                                        <span>
                                    <strong>Email:</strong>{" "}
                                            {props.home.cardByNumber.user.email}
                                </span>
                                        <br/>
                                        <span>
                                    <strong>Зарегистрирован:</strong>{" "}
                                            <ul>
                                            <li>
                                                <div>
                                                <span>
                                                    <strong>Страна:</strong>{" "}
                                                    {props.home.cardByNumber.user.address?.country.name}
                                                </span>
                                                <br />
                                                <span>
                                                    <strong>Область:</strong>{" "}
                                                    {props.home.cardByNumber.user.address?.region.name}
                                                </span>
                                                <br />
                                                    {
                                                        props.home.cardByNumber.user.address?.district !== null &&
                                                        <>
                                                        <span>
                                                            <strong>Район:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.district.name}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                                    <span>
                                                    <strong>Населенный пункт:</strong>{" "}
                                                        {props.home.cardByNumber.user.address?.locality.name}
                                                </span>
                                                <br />
                                                <span>
                                                    <strong>Улица:</strong>{" "}
                                                    {props.home.cardByNumber.user.address?.street.name}
                                                </span>
                                                <br />
                                                <span>
                                                    <strong>Дом:</strong>{" "}
                                                    {props.home.cardByNumber.user.address?.home}
                                                </span>
                                                <br />
                                                    {
                                                        props.home.cardByNumber.user.address?.build &&
                                                        <>
                                                        <span>
                                                            <strong>Корпус/строение:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.build}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                                    {
                                                        props.home.cardByNumber.user.address?.flat &&
                                                        <>
                                                        <span>
                                                            <strong>Квартира:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.flat}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                                    {
                                                        props.home.cardByNumber.user.address?.room &&
                                                        <>
                                                        <span>
                                                            <strong>Комната:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.room}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                                    {
                                                        props.home.cardByNumber.user.address?.zipcode &&
                                                        <>
                                                        <span>
                                                            <strong>Индекс:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.zipcode}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                            </div>
                                        </li></ul>
                                </span>
                                        <span>
                                    <strong>Проживает:</strong>{" "}
                                            <ul>
                                            <li>
                                                <div>
                                                <span>
                                                    <strong>Страна:</strong>{" "}
                                                    {props.home.cardByNumber.user.address?.country.name}
                                                </span>
                                                <br />
                                                <span>
                                                    <strong>Область:</strong>{" "}
                                                    {props.home.cardByNumber.user.address?.region.name}
                                                </span>
                                                <br />
                                                    {
                                                        props.home.cardByNumber.user.address?.district !== null &&
                                                        <>
                                                        <span>
                                                            <strong>Район:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.district.name}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                                    <span>
                                                    <strong>Населенный пункт:</strong>{" "}
                                                        {props.home.cardByNumber.user.address?.locality.name}
                                                </span>
                                                <br />
                                                <span>
                                                    <strong>Улица:</strong>{" "}
                                                    {props.home.cardByNumber.user.address?.street.name}
                                                </span>
                                                <br />
                                                <span>
                                                    <strong>Дом:</strong>{" "}
                                                    {props.home.cardByNumber.user.address?.home}
                                                </span>
                                                <br />
                                                    {
                                                        props.home.cardByNumber.user.address?.build &&
                                                        <>
                                                        <span>
                                                            <strong>Корпус/строение:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.build}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                                    {
                                                        props.home.cardByNumber.user.address?.flat &&
                                                        <>
                                                        <span>
                                                            <strong>Квартира:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.flat}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                                    {
                                                        props.home.cardByNumber.user.address?.room &&
                                                        <>
                                                        <span>
                                                            <strong>Комната:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.room}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                                    {
                                                        props.home.cardByNumber.user.address?.zipcode &&
                                                        <>
                                                        <span>
                                                            <strong>Индекс:</strong>{" "}
                                                            {props.home.cardByNumber.user.address?.zipcode}
                                                        </span>
                                                            <br />
                                                        </>
                                                    }
                                            </div>
                                        </li></ul>
                                </span>
                                    </div>
                                </li>
                            </ul>
                        </p>
                    </>
                }
                <p>
                    <strong>Причина для выдачи пропуска:</strong>{" "}
                    <ul>
                        {
                            props.home.cardByNumber.reasons.map((reason) =>
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
                            props.home.cardByNumber.scopes.map((scope) =>
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
                            props.home.cardByNumber.types.map((type) =>
                                <li key={type.id}>
                                    {type.name}
                                </li>)
                        }
                    </ul>
                </p>
                <p>
                    <strong>Статус:</strong>{" "}
                    {props.home.cardByNumber.status.name}
                </p>
                <p>
                    <strong>Дата и время начала действия:</strong>{" "}
                    {props.home.cardByNumber.startDate}
                </p>
                <p>
                    <strong>Дата и время окончания действия:</strong>{" "}
                    {props.home.cardByNumber.expirationDate}
                </p>
                <br />
                <br />
            </div>
        );
    }
}