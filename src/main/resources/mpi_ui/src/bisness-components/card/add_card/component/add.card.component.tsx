import * as React from 'react';
import {RouteComponentProps} from "react-router";
import {connect} from "react-redux";
// import {IReasonCard} from "../../card/model/reason.model";
// import {IScopeCard} from "../../card/model/scope.model";
// import {ITypeCard} from "../../card/model/type.model";
import {Combobox} from "react-widgets";
import Button from "reactstrap/lib/Button";
import {IAuthState, IState} from "../../../../reducers";
import {IPassCard} from "../../all_card/model/card.model";
import {saveCard} from "../../all_card/actions/card.actions";

interface IComponentState {
    // reasons: IReasonCard[],
    // scopes: IScopeCard[],
    // types: ITypeCard[],
    startDate: string,
    expirationDate: string,
    // userId: number
}

export interface IComponentProps extends RouteComponentProps<{}>{
    saveCard,
    auth: IAuthState
}

export class AddCardComponent extends React.Component<IComponentProps, IComponentState> {
    constructor(props: any) {
        super(props);
        this.state = {
            // reasons: [],
            // scopes: [],
            // types: [],
            startDate: '',
            expirationDate: '',
            // userId: 0
        }
    }

    public updateStartDate = (e: any) => {
        const startDate = e.target.value;
        this.setState({
            ...this.state,
            startDate

        })
    };

    public updateExpirationDate = (e: any) => {
        const expirationDate = e.target.value;
        this.setState({
            ...this.state,
            expirationDate

        })
    };

    public submitSaveCard = async (e: any) => {
        e.preventDefault();
        const { startDate, expirationDate } = this.state;
        try {
            const userId = this.props.auth.currentUser.id;
            const card: IPassCard = {
                id: 0,
                number: '',
                reasons: [
                    {
                        id: 1,
                        name: ''
                    }
                ],
                scopes: [
                    {
                        id: 1,
                        country: {
                            id: 0,
                            name: ''
                        },
                        region: {
                            id: 0,
                            name: ''
                        },
                        district: {
                            id: 0,
                            name: ''
                        },
                        locality: {
                            id: 0,
                            name: '',
                            typeLocality: {
                                name: ''
                            }
                        }
                    }
                ],
                types: [
                    {
                        id: 1,
                        name: ''
                    }
                ],
                status: {
                    name: ''
                },
                startDate: startDate + 'T00:00:00',
                expirationDate: expirationDate + 'T00:00:00',
                user: {
                    id: userId,
                    firstName: '',
                    lastName: '',
                    middleName: '',
                    birth: '',
                    email: '',
                    address: null,
                    login: '',
                    password: '',
                    roles: []
                },
                createDate: ''
            };

            this.props.saveCard(card);
            this.props.history.push('/all/card');
        } catch (err) {
            console.log(err);
        }
    };

    public render() {
        const reasonData = [
            { id: 1, name: ""},
            { id: 2, name: ""},
            { id: 3, name: "" }
        ];

        const scopeData = [
            { id: 1, name: ""},
            { id: 2, name: ""},
            { id: 3, name: "" }
        ];

        const typesData = [
            { id: 1, name: ""},
            { id: 2, name: ""},
            { id: 3, name: "" }
        ];

        const chooseReasons = (
            <Combobox
                data={reasonData}
                valueField="id"
                textField="name"
                placeholder="Выберите причину"
            />
        );

        const chooseScopes = (
            <Combobox
                data={scopeData}
                valueField="id"
                textField="name"
                placeholder="Выберите область"
            />
        );

        const chooseTypes = (
            <Combobox
                data={typesData}
                valueField="id"
                textField="name"
                placeholder="Выберите вид передвижения"
            />
        );

        return(
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        Создать пропуск
                    </h3>
                </header>
                <div>
                    <div>
                        <form id="registration-form" onSubmit={this.submitSaveCard}>
                            <fieldset>
                                <table>
                                    <tbody>
                                    <tr>
                                        <td className="regTdWidth">
                                            <p>
                                                <br />
                                                <label>Причины для выдачи пропуска: </label>
                                            </p>
                                        </td>
                                        <td  className="regTdWidth">
                                            {chooseReasons}
                                        </td>
                                        <td className="regTdWidth"></td>
                                        <td className="regTdWidth"></td>
                                        <td className="regTdWidth"></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <p>
                                                <br />
                                                <label>Область действия пропуска: </label>
                                            </p>
                                        </td>
                                        <td>
                                            {chooseScopes}
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <p>
                                                <br />
                                                <label>Вид передвижения: </label>
                                            </p>
                                        </td>
                                        <td>
                                            {chooseTypes}
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <p>
                                                <br />
                                                <label htmlFor="startDate">Дата начала действия: </label>
                                            </p>
                                        </td>
                                        <td>
                                            <input name="startDate" type="date" className="txt-bx"
                                                   onChange={this.updateStartDate} value={this.state.startDate}
                                                   placeholder="Выберите дату рождения"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <p>
                                                <br />
                                                <label htmlFor="birth">Дата окончания действия: </label>
                                            </p>
                                        </td>
                                        <td>
                                            <input name="expirationDate" type="date" className="txt-bx"
                                                   onChange={this.updateExpirationDate} value={this.state.expirationDate}
                                                   placeholder="Выберите дату рождения"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <p>
                                                <br />
                                                <br />
                                            </p>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <Button className="btn btn-primary">Сохранить</Button>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </fieldset>
                        </form>
                    </div>
                    <br />
                    <br />
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state: IState) => ({
    auth: state.auth,
});
const mapDispatchToProps = {
    saveCard
};

export default connect(mapStateToProps, mapDispatchToProps)(AddCardComponent);