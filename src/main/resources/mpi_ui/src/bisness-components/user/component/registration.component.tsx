import * as React from 'react';
import Button from "reactstrap/lib/Button";
import {Combobox} from "react-widgets";
import 'react-widgets/dist/css/react-widgets.css';
import {IUser} from "../model/user.model";
import {IComponentProps, IComponentState} from "./registration.container";
import {ComboboxMessages} from "react-widgets/lib/Combobox";

export class RegistrationComponent extends React.Component<IComponentProps, IComponentState> {
    constructor(props: any) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            middleName: '',
            birth: '',
            email: '',
            username: '',
            password: '',
            countryId: 0,
            regionId: 2,
            districtId: 0,
            localityId: 1,
            streetId: 1,
            home: '',
            build: '',
            flat: '',
            room: '',
            zipcode: '',
            countryError: {
                open: 'Ошибка открытия',
                emptyList: 'Ошибка загрузки списка стран',
                emptyFilter: 'По данному фильтру стран не найдено'
            }
        }
    }

    componentDidMount() {
        this.props.getCountryList(" ");
    }

    public updateFirstName = (e: any) => {
        const firstName = e.target.value;
        this.setState({
            ...this.state,
            firstName

        })
    };

    public updateLastName = (e: any) => {
        const lastName = e.target.value;
        this.setState({
            ...this.state,
            lastName

        })
    };

    public updateMiddleName = (e: any) => {
        const middleName = e.target.value;
        this.setState({
            ...this.state,
            middleName

        })
    };

    public updateBirth = (e: any) => {
        const birth = e.target.value;
        this.setState({
            ...this.state,
            birth

        })
    };

    public updateEmail = (e: any) => {
        const email = e.target.value;
        this.setState({
            ...this.state,
            email

        })
    };

    public updateUsername = (e: any) => {
        const username = e.target.value;
        this.setState({
            ...this.state,
            username

        })
    };

    public updatePassword = (e: any) => {
        const password = e.target.value;
        this.setState({
            ...this.state,
            password

        })
    };

    public updateHome = (e: any) => {
        const home = e.target.value;
        this.setState({
            ...this.state,
            home

        })
    };

    public updateBuild = (e: any) => {
        const build = e.target.value;
        this.setState({
            ...this.state,
            build

        })
    };

    public updateFlat = (e: any) => {
        const flat = e.target.value;
        this.setState({
            ...this.state,
            flat

        })
    };

    public updateRoom = (e: any) => {
        const room = e.target.value;
        this.setState({
            ...this.state,
            room

        })
    };

    public updateZipcode = (e: any) => {
        const zipcode = e.target.value;
        this.setState({
            ...this.state,
            zipcode

        })
    };

    public updateCountry = (e: any) => {
        const countryId = e.id;
        this.setState({
            ...this.state,
            countryId

        })
    };

    public filterCountryName(country, value) {
        const countryNames = country.name.toLowerCase()
        const countryName  = value.toLowerCase();

        return countryNames.indexOf(countryName) === 0
    }

    public submitRegistration = async (e: any) => {
        e.preventDefault();
        const { firstName, lastName, middleName, birth,
            email, countryId, regionId,
            localityId, streetId, home, build, flat,
            room, zipcode, username, password } = this.state;
        try {
            const user: IUser = {
                id: 0,
                firstName: firstName,
                lastName: lastName,
                middleName: middleName,
                birth: birth,
                email: email,
                address: {
                    id: 0,
                    country: {
                        id: countryId,
                        name: ''
                    },
                    region: {
                        id: regionId,
                        name: ''
                    },
                    district: null,
                    locality: {
                        id: localityId,
                        name: '',
                        typeLocality: {
                            name: ''
                        }
                    },
                    street: {
                        id: streetId,
                        name: ''
                    },
                    home: home,
                    build: build,
                    flat: flat,
                    room: room,
                    zipcode: zipcode,
                },
                login: username,
                password: password,
                roles: []
            };

            this.props.saveUser(user);
            this.props.history.push('/profile');
        } catch (err) {
            console.log(err);
        }
    };

    public render() {
        const countryData = this.props.countries.countries;

        const regions = [
            { id: 1, name: ""},
            { id: 2, name: ""},
            { id: 3, name: "" }
        ];

        const districts = [
            { id: 1, name: ""},
            { id: 2, name: ""},
            { id: 3, name: "" }
        ];

        const localityis = [
            { id: 1, name: ""},
            { id: 2, name: ""},
            { id: 3, name: "" }
        ];

        const streets = [
            { id: 1, name: ""},
            { id: 2, name: ""},
            { id: 3, name: "" }
        ];

        const chooseCountry = (
            <Combobox
                data={countryData}
                valueField="id"
                onChange={this.updateCountry}
                filter={this.filterCountryName}
                textField="name"
                placeholder="Выберите страну"
                messages={this.state.countryError}
            />
        );

        const chooseRegion = (
            <Combobox
                data={regions}
                valueField="id"
                textField="name"
                placeholder="Выберите область"
            />
        );

        const chooseDistrict = (
            <Combobox
                data={districts}
                valueField="id"
                textField="name"
                placeholder="Выберите район"
            />
        );

        const chooseLocality = (
            <Combobox
                data={localityis}
                valueField="id"
                textField="name"
                placeholder="Выберите населенный пункт"
            />
        );

        const chooseStreet = (
            <Combobox
                data={streets}
                valueField="id"
                textField="name"
                placeholder="Выберите улицу"
            />
        );

        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        Регистрация
                    </h3>
                </header>
                <div>
                    <div>
                        <form id="registration-form" onSubmit={this.submitRegistration}>
                            <fieldset>
                                <table>
                                    <tbody>
                                        <tr>
                                            <td className="regTdWidth">
                                                <p>
                                                    <br />
                                                    <label htmlFor="firstName">Имя: </label>
                                                </p>
                                            </td>
                                            <td  className="regTdWidth">
                                                <input name="firstName" type="text" className="txt-bx"
                                                       onChange={this.updateFirstName} value={this.state.firstName}
                                                       placeholder="Введите имя"/>
                                            </td>
                                            <td className="regTdWidth">
                                                <p>
                                                    <br />
                                                    <label>Страна: </label>
                                                </p>
                                            </td>
                                            <td className="regTdWidth">
                                                {chooseCountry}
                                            </td>
                                            <td className="regTdWidth"></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="lastName">Фамилия: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="lastName" type="text" className="txt-bx"
                                                       onChange={this.updateLastName} value={this.state.lastName}
                                                       placeholder="Введите фамилию"/>
                                            </td>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label>Область: </label>
                                                </p>
                                            </td>
                                            <td>
                                                {chooseRegion}
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="middleName">Отчество: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="middleName" type="text" className="txt-bx"
                                                       onChange={this.updateMiddleName} value={this.state.middleName}
                                                       placeholder="Введите отчество"/>
                                            </td>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label>Район: </label>
                                                </p>
                                            </td>
                                            <td>
                                                {chooseDistrict}
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="birth">Дата рождения: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="birth" type="date" className="txt-bx"
                                                       onChange={this.updateBirth} value={this.state.birth}
                                                       placeholder="Выберите дату рождения"/>
                                            </td>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label>Населенный пункт: </label>
                                                </p>
                                            </td>
                                            <td>
                                                {chooseLocality}
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="email">Email: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="email" type="text" className="txt-bx"
                                                       onChange={this.updateEmail} value={this.state.email}
                                                       placeholder="Введите email"/>
                                            </td>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label>Улица: </label>
                                                </p>
                                            </td>
                                            <td>
                                                {chooseStreet}
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="photoPath">Фотография: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="photoPath" type="file" className="txt-bx"/>
                                            </td>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="home">Дом: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="home" type="text" className="txt-bx"
                                                       onChange={this.updateHome} value={this.state.home}
                                                       placeholder="Введите номер дома"/>
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="username">Логин: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="username" type="text" className="txt-bx"
                                                       onChange={this.updateUsername} value={this.state.username}
                                                       placeholder="Введите логин"/>
                                            </td>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="bild">Корпус/строение: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="bild" type="text" className="txt-bx"
                                                       onChange={this.updateBuild} value={this.state.build}
                                                       placeholder="Введите номер корпуса"/>
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="password">Пароль: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="password" type="password" className="txt-bx"
                                                       onChange={this.updatePassword} value={this.state.password}
                                                       placeholder="Введите пароль"/>
                                            </td>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="flat">Квартира: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="flat" type="text" className="txt-bx"
                                                       onChange={this.updateFlat} value={this.state.flat}
                                                       placeholder="Введите номер квартиры"/>
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="room">Комната: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="room" type="text" className="txt-bx"
                                                       onChange={this.updateRoom} value={this.state.room}
                                                       placeholder="Введите номер комнаты"/>
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>
                                                <p>
                                                    <br />
                                                    <label htmlFor="zipcode">Индекс: </label>
                                                </p>
                                            </td>
                                            <td>
                                                <input name="zipcode" type="text" className="txt-bx"
                                                       onChange={this.updateZipcode} value={this.state.zipcode}
                                                       placeholder="Введите индекс"/>
                                            </td>
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