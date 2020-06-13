import * as React from 'react';

import Button from "reactstrap/lib/Button";
import {IHomeComponentProps, IHomeComponentState} from "./home.container";

export class HomeComponent extends React.Component<IHomeComponentProps, IHomeComponentState> {
    constructor(props: any) {
        super(props);
        this.updateNumber = this.updateNumber.bind(this);
        this.state = {
            number: ''
        }
    }

    public updateNumber = (e: any) => {
        const number = e.target.value;
        this.setState({
            ...this.state,
            number

        })
    };

    public submitCardByNumber = async (e: any) => {
        e.preventDefault();
        const { number } = this.state;
        try {
            this.props.getCardByNumber(number);
            this.props.history.push('/check/card');
        } catch (err) {
            console.log(err);
        }
    };

    public render() {
        return (
            <div>
                <div className="checkNumber">
                    <div className="row background-row">
                        <div className="col-md-7">
                            <h3>Проверить пропуск по номеру</h3>
                            <form id="contactForm" onSubmit={this.submitCardByNumber}>
                                <div className="control-group form-group">
                                    <div className="controls">
                                        <input type="text" className="form-control" id="numberCard" name="number"
                                               value={this.state.number} onChange={this.updateNumber}
                                               required placeholder="Введите номер"
                                               data-validation-required-message="Пожалуйста введите номер пропуска"/>
                                        <p className="help-block"></p>
                                    </div>
                                </div>
                                <div id="success"></div>
                                <Button className="btn btn-primary">Отправить</Button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
