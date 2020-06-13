import * as React from 'react';
import {IComponentProps, IComponentState} from "./feedback.container";
import {Combobox} from "react-widgets";
import {IFeedback} from "../model/feedback.modal";

export class FeedbackComponent extends React.PureComponent<IComponentProps, IComponentState, {}> {
    constructor(props: any) {
        super(props);
        this.state = {
            email: '',
            typeId: 1,
            message: '',
        }
    }

    componentDidMount() {
        this.props.getTypes();
    }

    public updateEmail = (e: any) => {
        const email = e.target.value;
        this.setState({
            ...this.state,
            email

        })
    };

    public updateType = (e: any) => {
        const typeId = e.id;
        this.setState({
            ...this.state,
            typeId

        })
    };

    public updateMessage = (e: any) => {
        const message = e.target.value;
        this.setState({
            ...this.state,
            message

        })
    };

    public submitFeedBack = async (e: any) => {
        e.preventDefault();
        const { email, typeId, message } = this.state;
        try {
            const feedback: IFeedback = {
                email: email,
                typeId: typeId,
                message: message,
                userId: this.props.auth.currentUser.id

            };

            this.props.sendMessage(feedback);
            this.props.history.push('/home');
        } catch (err) {
            console.log(err);
        }
    };

    public render() {
        const typeData = this.props.typeFeedbacks.typeFeedbacks;

        const chooseType = (
            <Combobox
                data={typeData}
                valueField="id"
                onChange={this.updateType}
                value={this.state.typeId}
                textField="name"
                placeholder="Выберите причину"
            />
        );

        return(
            <div className="container">
                <div className="row">
                    <div className="col-md-8">
                        <h3>Отправить нам сообщение</h3>
                        <form id="contact-form" onSubmit={this.submitFeedBack}>
                            <div className="control-group form-group">
                                <div className="controls">
                                    <label htmlFor="email">Электронный адрес:</label>
                                    <input type="email" className="form-control" id="email" name="email"
                                           onChange={this.updateEmail} value={this.state.email}
                                           required data-validation-required-message="Пожалуйста введите ваш Email адрес." />
                                </div>
                            </div>
                            <div className="control-group form-group">
                                <div className="controls">
                                    <label>Причина:</label>
                                    {chooseType}
                                </div>
                            </div>
                            <div className="control-group form-group">
                                <div className="controls">
                                    <label htmlFor="message">Сообщение:</label>
                                    <textarea rows={10} cols={100} className="form-control" id="message" name="message"
                                              onChange={this.updateMessage} value={this.state.message}
                                              required data-validation-required-message="Пожалуйста введите ваше сообщение"
                                              minLength={10} maxLength={999}></textarea>
                                </div>
                            </div>
                            <div id="success"></div>
                            <button type="submit" className="btn btn-primary">Отправить</button>
                        </form>
                    </div>

                </div>

                <hr />
            </div>
        );
    }
}